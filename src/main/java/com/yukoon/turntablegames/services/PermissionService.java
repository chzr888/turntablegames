package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.mappers.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<String> getPermNameByRoleid(Integer role_id) {
        return permissionMapper.getPermNameByRoleid(role_id);
    }
}
