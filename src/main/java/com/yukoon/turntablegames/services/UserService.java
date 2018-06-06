package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.mappers.UsersMapper;
import com.yukoon.turntablegames.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    public User login(User user) {
        List<User> list = usersMapper.login(user);
        for (User user_temp :list) {
            if (user_temp != null && user.getPassword().equals(user_temp.getPassword())) {
                return user_temp;
            }
        }
        return null;
    }

    @Transactional
    public List<User> findAllByActID(Integer id) {
        return usersMapper.findAllByActId(id);
    }

    @Transactional
    public User findById(Integer id) {
        return usersMapper.findById(id);
    }

    @Transactional
    public void updateUser(User user) {
        usersMapper.updateUser(user);
    }
    @Transactional
    public void addUser(User user) {
        user.setPassword(EncodeUtil.encodePassword(activityMapper.getKeyByActId(user.getAct_id()),user.getUsername())).setRole_id(1);
        usersMapper.addUser(user);
    }

    public Integer getRoleidById(Integer id) {
    	return usersMapper.getRoleidById(id);
	}

    public List<User> findByUsernameAndActid(User user) {
        user.setUsername("%"+user.getUsername()+"%");
        return usersMapper.findByUsernameAndActid(user);
    }


    @Transactional
    public void delUser(Integer id) {
        usersMapper.delUser(id);
    }
}
