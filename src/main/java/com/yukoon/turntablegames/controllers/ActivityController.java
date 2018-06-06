package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Activity;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.services.ActivityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/act")
    public String actAdd(Activity activity) {
        activityService.addAct(activity);
        return "redirect:/acts";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/act")
    public String actToAdd() {
        return "background/act_input";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/acts")
    public String list(Map<String,Object> map) {
        List<Activity> list  = activityService.findAll();
        map.put("acts",list);
        return "background/act_list";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/actclose/{id}")
    public  String actClose(@PathVariable("id") Integer id) {
        activityService.closeAct(id);
        return "redirect:/acts";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/actopen/{id}")
    public  String actOpen(@PathVariable("id") Integer id) {
        activityService.openAct(id);
        return "redirect:/acts";
    }
}
