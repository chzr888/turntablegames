package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.utils.UserMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {
    @Insert("INSERT INTO users(username,password,role_id,act_id,draw_times,available_draw_times) VALUES(#{username}," +
            "#{password},#{role_id},#{act_id},#{draw_times},#{draw_times})")
    public void addUser(User user);

    @Select("SELECT id,username,password,role_id,act_id,draw_times,available_draw_times FROM users WHERE username = #{username}")
    public List<User> login(User user);

    @Select("SELECT id,username,draw_times,available_draw_times FROM users WHERE act_id =#{act_id}")
    public List<User> findAllByActId(Integer act_id);

    @Select("SELECT id,username,act_id,role_id,draw_times,available_draw_times FROM users WHERE id=#{id}")
    public User findById(Integer id);

    @Select("SELECT id,username,act_id,draw_times,available_draw_times FROM users WHERE username LIKE #{username} AND act_id =#{act_id}")
    public List<User> findByUsernameAndActid(User user);

    @Select("SELECT username FROM users WHERE id=#{id}")
    public String findUsernameById(Integer id);

    @Select("SELECT act_id FROM users WHERE id=#{id}")
    public Integer findActidById(Integer id);

    @Select("SELECT role_id FROM users WHERE id=#{id}")
	public Integer getRoleidById(Integer id);

    @Update("UPDATE users SET username = #{username},draw_times=#{draw_times},available_draw_times = #{available_draw_times} WHERE id = #{id}")
    public void updateUser(User user);

    @Update("UPDATE users SET available_draw_times = #{available_draw_times} WHERE id = #{id}")
    public void minusAvailableDrawTimes(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public void delUser(Integer id);

    //批量插入
    @InsertProvider(type = UserMapperProvider.class,method = "insertAll")
    public void insertAll(@Param("list")List<User> users);
}
