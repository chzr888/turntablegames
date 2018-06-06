package com.yukoon.turntablegames.mappers;


import com.yukoon.turntablegames.entities.Reward;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RewardMapper {

    @Insert("INSERT INTO rewards(rewardName,total,surplus,probability,act_id) VALUES (#{rewardName},#{total},#{surplus},#{probability},#{act_id})")
    public void addReward(Reward reward);

    @Delete("DELETE FROM rewards WHERE id = #{id}")
    public void delReward(Integer id);

    @Update("UPDATE rewards SET rewardName=#{rewardName},total=#{total},surplus=#{surplus},probability=#{probability},act_id=#{act_id} WHERE id = #{id}")
    public void updateReward(Reward reward);

    @Update("UPDATE rewards SET surplus=#{surplus} WHERE id=#{id}")
    public void minusSurplus(Reward reward);

    @Select("SELECT id,rewardName,total,surplus,probability FROM rewards WHERE act_id = #{act_id}")
    public List<Reward> findByActid(Integer act_id);

    @Select("SELECT id,rewardName,probability,surplus,act_id FROM rewards WHERE act_id = #{act_id}")
    public List<Reward> getProbabilityByActid(Integer act_id);

    @Select("SELECT * from rewards WHERE id = #{id}")
    public Reward findById(Integer id);

    @Select("SELECT rewardName from rewards WHERE id = #{id}")
    public String findRewardnameById(Integer id);

    @Select("select * from rewards WHERE rewardName = 'thanks'")
    public Reward thanks();
}
