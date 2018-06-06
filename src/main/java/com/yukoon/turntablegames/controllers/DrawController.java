package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes(value = {"user"},types = {User.class})
public class DrawController {
    @Autowired
    private AwardInfoService awardInfoService;
    @Autowired
    private UserService userService;

    //随机抽取奖品并返回结果用以回显
    @ResponseBody
    @PostMapping("/draw")
    public Reward getDrawResult(User user, Map<String,Object> map,ModelMap modelMap) {
        map.put("act_id",user.getAct_id());
        Reward reward = awardInfoService.addAwardInfo(user);
        User u_temp = userService.findById(user.getId());
        System.out.println(u_temp);
        modelMap.addAttribute("user",u_temp);
        return reward;
    }

    //前台获取某一用户的信息
    @ResponseBody
    @GetMapping("/getVT/{user_id}")
    public User getAvailableDrawTimes(@PathVariable("user_id")Integer user_id) {
        return userService.findById(user_id);
    }
}
