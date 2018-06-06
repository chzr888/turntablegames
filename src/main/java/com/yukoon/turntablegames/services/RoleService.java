package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public String getRole(Integer id) {
		return roleMapper.getRole(id);
	}
}
