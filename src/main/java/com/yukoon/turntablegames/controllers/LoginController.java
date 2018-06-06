package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.config.PathConfig;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.UserService;
import com.yukoon.turntablegames.utils.EncodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.websocket.Session;
import java.util.Map;

@Controller
@SessionAttributes(value = {"user"},types = {User.class})
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private PathConfig pathConfig;

    @PostMapping("/login")
    public String login(Map<String,Object> map, User user,String flag){
        //获得subject
        Subject currentUser  = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            //把用户名密码封装为Token对象
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            //设置token的rememberme
            usernamePasswordToken.setRememberMe(true);
            try {
                //执行登录
                currentUser.login(usernamePasswordToken);
            }catch (AuthenticationException ae){
                System.out.println("登陆失败:"+ae.toString());
				//若从后台登录，则回后台登录界面
				if ("bg".equals(flag)){
					return "redirect:/background";
				}
				//前台登录则返回前台登录界面
				return "redirect:/index";
            }
        }
        user.setPassword(EncodeUtil.encodePassword(user.getPassword(),user.getUsername()));
        User user_temp = userService.login(user);
        Integer act_status = activityService.getStatusById(user_temp.getAct_id());
        if (user_temp.getRole_id() == 2) {
            //若为管理员，则进入后台
            return "redirect:/acts";
        }
        if (act_status == 2) {
            //若活动已经结束，则前往奖品查询页面
            return "redirect:/pbaward/" + user_temp.getId();
        }

        map.put("user",user_temp);
        map.put("act_status",act_status);
        return "public/pb_index";
    }

        //未添加Shiro前的登录方法
//    @PostMapping("/login1")
//    public String login1(Map<String,Object> map, User user,String flag){
//        User user_temp = userService.login(user);
//        Integer act_status = activityService.getStatusById(user_temp.getAct_id());
//        if (user_temp != null) {
//            //若不为空则代表验证正确
//            map.put("user",user_temp);
//        }else {
//            //若从后台登录，则回后台登录界面
//            if ("bg".equals(flag)){
//                return "redirect:/loginpage/login.html";
//            }
//            //前台登录则返回前台登录界面
//            return "redirect:/index.html";
//        }
//        if (user_temp.getRole_id() == 2) {
//            //若为管理员，则进入后台
//            return "redirect:/acts";
//        }
//        if (act_status == 2) {
//            //若活动已经结束，则前往奖品查询页面
//            return "redirect:/pbaward/" + user_temp.getId();
//        }
//        map.put("act_status",act_status);
//        return "public/pb_index";
//    }

    //前台刷新页面
    @GetMapping("/reflash")
    public String reflash(ModelMap modelMap, User user) {
        //更新session中user信息
        modelMap.addAttribute("user",userService.findById(user.getId()));
        return "public/pb_index";
    }

    //请求登录首页
    @GetMapping("/index")
    public String index() {
        return "commons/index";
    }

    //请求后台登录页面
    @GetMapping("/background")
    public String background() {
        return "commons/bg_login";
    }
}
