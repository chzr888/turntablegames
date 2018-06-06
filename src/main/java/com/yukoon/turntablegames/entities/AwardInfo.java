package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class AwardInfo {
    private Integer id;
    private Integer user_id;
    private Integer act_id;
    private Integer reward_id;
    private Integer is_Cash;
    private Date winning_date;
    private Date cashing_date;
}
