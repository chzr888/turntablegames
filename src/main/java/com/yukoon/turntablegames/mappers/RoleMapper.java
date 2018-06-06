package com.yukoon.turntablegames.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("SELECT roleName from roles WHERE id =#{id}")
    public String getRole(Integer id);
}
