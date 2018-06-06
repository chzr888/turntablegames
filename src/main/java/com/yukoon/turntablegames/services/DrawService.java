package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DrawService {
    //不含谢谢惠顾的最大礼品限值
    private final static int MAX_REWARD_NUMBER = 7;
    @Autowired
    private RewardMapper rewardMapper;

    public Reward randomChoice(Integer act_id){
        List<Reward> list = rewardMapper.getProbabilityByActid(act_id);
        //抽出数字
        int i = getRange(list);

        if (list.size() == MAX_REWARD_NUMBER) {
            int range = 0;
            int rotation = 0;
            for (Reward element:list) {
                int temp = (int)(element.getProbability()*100);
                range = range + temp;
                System.out.println(range);

                if (i < range) {
                    rotation = list.size() - rotation;
                    element.setRotation((rotation*45));
                    element.setListSize(MAX_REWARD_NUMBER);
                    System.out.println("full:"+element);
                    return  element;
                }
                rotation++;
            }
        }else {
            int range = 0;
            int rotation = 0;
            for (Reward element:list) {
                int temp = (int)(element.getProbability()*100);
                range = range + temp;
                System.out.println(range);
                if (i < range) {
                    int angel = 360/(list.size()+1);
                    element.setListSize(list.size());
                    rotation = list.size() - rotation;
                    element.setRotation((rotation*angel));
                    System.out.println("not full"+element);
                    return  element;
                }
                rotation++;
            }
        }
        return null;
    }

    //根据概率校正抽出数字
    public Integer getRange(List<Reward> list) {
        Random random  = new Random();
        int i = random.nextInt(100);
        System.out.println(i);
        int range = 0;
        for (Reward element:list) {
            int temp = (int) (element.getProbability() * 100);
            range = range + temp;
        }
        while (i>=range){
            i = random.nextInt(range);
            System.out.println("new i:" + i);
        }
        return i;
    }

    //对randomChoice()得到的reward进行检验保证能返回正确结果
    public Reward getRandomReward(Integer act_id) {
        Reward reward = randomChoice(act_id);
        while (reward == null ) {
            System.out.println("抽取发生问题，将重新抽取");
            reward = randomChoice(act_id);
        }
        if (reward.getSurplus() <1) {
            //没有库存，返回谢谢惠顾
            int size = reward.getListSize()+1;
            int rotation = (360/size);
            reward = rewardMapper.thanks();
            reward.setRotation(rotation);
            System.out.println(reward);
        }
        return reward;
    }
}
