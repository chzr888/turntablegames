package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityMapper {

    @Insert("INSERT INTO activities(activityName,act_status,act_key) VALUES(#{activityName},#{act_status},#{act_key})")
    public void addAct(Activity activity);

    @Update("UPDATE activities SET act_status = 2 WHERE id = #{id}")
    public void closeAct(Integer id);

    @Update("UPDATE activities SET act_status = 1 WHERE id = #{id}")
    public void openAct(Integer id);

    @Select("Select id,activityName,act_status,act_key FROM activities WHERE act_key != 'admin'")
    public List<Activity> findAll();

    @Select("SELECT activityName FROM activities WHERE id= #{id}")
    public String findById(Integer id);

    @Select("SELECT act_key FROM activities WHERE id= #{id}")
    public String getKey(Integer id);

    @Select("SELECT act_key FROM activities WHERE act_key = #{act_key}")
    public String keyVaildate(String act_key);

    @Select("SELECT act_key FROM activities WHERE id = #{act_id}")
    public String getKeyByActId(Integer act_id);

    @Select("SELECT act_status FROM activities WHERE id= #{id}")
    public Integer getStatusById(Integer id);
}
