package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.AwardInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface AwardInfoMapper {
    @Select("SELECT * FROM AwardInfo WHERE act_id = #{act_id}")
    public List<AwardInfo> findAllByActid(Integer act_id);

    @Select("SELECT * FROM AwardInfo WHERE user_id = #{user_id}")
    public List<AwardInfo> findAllByUserid(Integer user_id);

    @Select("SELECT * FROM AwardInfo WHERE id=#{id}")
    public AwardInfo findById(Integer id);

    @Update("UPDATE AwardInfo SET is_Cash = 1,cashing_date = #{cashing_date} WHERE id=#{id}")
    public void cashAward(AwardInfo awardInfo);

    @Insert("INSERT INTO AwardInfo(user_id,act_id,reward_id,is_cash,winning_date) VALUES (#{user_id},#{act_id},#{reward_id},#{is_Cash},#{winning_date})")
    public void addAwardInfo(AwardInfo awardInfo);
}
