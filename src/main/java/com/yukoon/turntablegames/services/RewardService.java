package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewardService {
    @Autowired
    private RewardMapper rewardMapper;
    @Autowired
    private ActivityMapper activityMapper;
    private final static Integer MAX_REWARD_NUMBER = 7;

    @Transactional
    public boolean addReward(Reward reward) {
        boolean flag = false;
        int size = rewardMapper.findByActid(reward.getAct_id()).size();
        boolean isNull = (activityMapper.findById(reward.getAct_id()) == null);
        if (size <MAX_REWARD_NUMBER && isNull == false && reward.getRewardName().equals("thanks") ==false) {
            rewardMapper.addReward(reward);
            flag = true;
        }
        return flag;
    }

    @Transactional
    public void delReward(Integer id) {
        rewardMapper.delReward(id);
    }

    @Transactional
    public void updateReward(Reward reward) {
        rewardMapper.updateReward(reward);
    }

    @Transactional
    public List<Reward> findByActid(Integer id) {
        return rewardMapper.findByActid(id);
    }

    @Transactional
    public Reward findById(Integer id) {
        return rewardMapper.findById(id);
    }
}
