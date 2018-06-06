package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Recommender {
    private Integer id;
    private String recommender_id;
    private Integer act_id;
}
