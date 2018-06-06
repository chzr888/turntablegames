package com.yukoon.turntablegames.realms;

import com.yukoon.turntablegames.entities.Permission;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.PermissionService;
import com.yukoon.turntablegames.services.RoleService;
import com.yukoon.turntablegames.services.UserService;
import com.yukoon.turntablegames.utils.EncodeUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
    private PermissionService permissionService;

	//认证的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		//2. 从 UsernamePasswordToken 中获取 username,password构建user
		String username = upToken.getUsername();
		User user = new User().setUsername(username).setPassword(EncodeUtil.encodePassword(String.valueOf(upToken.getPassword()),username));
		//3. 从数据库获取User准备进行比对
		User user_temp = userService.login(user);
		//4. 异常用户抛出异常
		if (user_temp == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		//5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回.
		Object principal = user_temp.getId();	//这里principal传入ID比较方便
		ByteSource credentialsSalt = ByteSource.Util.bytes(user_temp.getUsername());
		Object credentials = new SimpleHash("MD5", upToken.getCredentials(),credentialsSalt, 1024);
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
		return info;
	}

	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//1. 从 principalCollection 中来获取登录用户的信息
		Object principal = principalCollection.getPrimaryPrincipal();
		//2. 利用登录的用户的信息来当前用户的角色
		User user_temp = userService.findById(Integer.parseInt(principal.toString()));
        //3. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性并返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();
		String role = roleService.getRole(user_temp.getRole_id());
		roles.add(role);
		info.addRoles(roles);
		for (String perm: permissionService.getPermNameByRoleid(user_temp.getRole_id())) {
		    info.addStringPermission(perm);
        }
		return info;
	}



}
