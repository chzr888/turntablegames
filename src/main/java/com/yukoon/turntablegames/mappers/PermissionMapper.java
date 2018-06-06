package com.yukoon.turntablegames.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("SELECT permName FROM permissions WHERE role_id = #{role_id}")
    public List<String> getPermNameByRoleid(Integer role_id);
}
