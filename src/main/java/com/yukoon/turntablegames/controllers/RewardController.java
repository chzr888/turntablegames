package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.RewardService;
import com.yukoon.turntablegames.utils.FileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;
    @Autowired
    ActivityService activityService;

    //后台查询某一活动下的所有奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/rewards/{id}")
    public String actRewards(@PathVariable("id") Integer id, Map<String,Object> map) {
        List<Reward> list = rewardService.findByActid(id);
        map.put("rewards",list);
        map.put("act_id",id);
        map.put("act_status",activityService.getStatusById(id));
        return "background/reward_list";
    }

    //后台删除某一活动下的奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @DeleteMapping("/reward/{id}")
    public String delRewards(@PathVariable("id") Integer id,Map<String,Object> map,Integer act_id) {
        rewardService.delReward(id);
        List<Reward> list = rewardService.findByActid(act_id);
        map.put("rewards",list);
        return "background/reward_list";
    }

    //后台前往修改某一活动下的奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/reward/{id}")
    public String rewardToEdit(@PathVariable("id") Integer id, Map<String,Object> map) {
        Reward reward  = rewardService.findById(id);
        map.put("reward",reward);
        return "background/reward_input";
    }
    //后台修改某一活动下的奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PutMapping("/reward")
    public String rewardEdit(Reward reward) {
        rewardService.updateReward(reward);
        return "redirect:/rewards/"+reward.getAct_id();
    }

    //后台前往添加某一活动下的奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/rewardToAdd/{id}")
    public String rewardToAdd(@PathVariable("id") Integer id, Map<String,Object> map) {
        map.put("act_id",id);
        return "background/reward_input";
    }

    //后台添加某一活动下的奖品
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @PostMapping("/reward")
    public String rewardAdd(Reward reward) {
        System.out.println(reward);
        rewardService.addReward(reward);
        return "redirect:/rewards/"+reward.getAct_id();
    }


}
