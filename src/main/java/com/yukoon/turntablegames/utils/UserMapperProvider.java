package com.yukoon.turntablegames.utils;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.UsersMapper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperProvider {


    public String insertAll(Map map) {
        List<User> users = (List<User>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO users (username,password,role_id,act_id,draw_times,available_draw_times) VALUES");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].username'}',#'{'list[{0}].password'}',#'{'list[{0}].role_id'}'," +
                "#'{'list[{0}].act_id'}',#'{'list[{0}].draw_times'}',#'{'list[{0}].available_draw_times'}'");
        for (int i = 0;i< users.size();i++) {
            sb.append("(");
            //这里如果直接用数字，超过1000会格式化变成1,000，但是String类型就没问题
            sb.append(mf.format(new Object[]{String.valueOf(i)}));
            sb.append(")");

            if (i < users.size() - 1) {
                sb.append(",");
            }
        }
        return  sb.toString();
    }

    public static void main(String[] args) {
        User u1 = new User().setUsername("feili1").setPassword("123").setRole_id(1).setAct_id(2).setDraw_times(5).setAvailable_draw_times(5);
        User u2 = new User().setUsername("feili2").setPassword("123").setRole_id(1).setAct_id(2).setDraw_times(5).setAvailable_draw_times(5);
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        Map<String,Object> map = new HashMap<>();
        map.put("users",list);
        System.out.println(new UserMapperProvider().insertAll(map));
    }
}
