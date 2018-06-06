package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    //后台查看某一活动所有客户信息
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/users/{id}")
    public String getUsers(@PathVariable("id")Integer id,Map<String,Object> map) {
        List<User> list = userService.findAllByActID(id);
        map.put("users",list);
        map.put("act_id",id);
        map.put("act_status",activityService.getStatusById(id));
        return "background/user_list";
    }

    //后台根据条件搜索客户
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/finduser")
    public String finduser(User user,Map<String,Object> map) {
        List<User> list = userService.findByUsernameAndActid(user);
        map.put("users",list);
        map.put("act_id",user.getAct_id());
        return "background/user_list";
    }

    //后台删除某一个客户
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @DeleteMapping("/user/{id}")
    public String delUser(@PathVariable("id")Integer id,Integer act_id) {
        userService.delUser(id);
        return "redirect:/users/"+act_id;
    }

    //后台前往编辑某一活动下客户信息
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/user/{id}")
    public String toEditUser(@PathVariable("id")Integer id,Map<String,Object> map) {
        User user = userService.findById(id);
        map.put("user",user);
        return "background/user_input";
    }

    //后台编辑某一客户信息
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PutMapping("/user")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/users/"+user.getAct_id();
    }

    //后台前往添加某一活动下客户信息
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/useradd/{id}")
    public String toAddUser(@PathVariable("id")Integer id,Map<String,Object> map) {
        map.put("act_id",id);
        return "background/user_input";
    }

    //后台添加某一活动系啊客户信息
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/user")
    public String addUser(User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/users/"+user.getAct_id();
    }
}
