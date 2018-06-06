package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer role_id;
    private Integer act_id;
    private Integer draw_times;
    private Integer available_draw_times;
}
