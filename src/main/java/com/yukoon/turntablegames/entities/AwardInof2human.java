package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor

@Accessors(chain = true)
public class AwardInof2human implements Serializable{
    private Integer id;
    private String username;
    private String act_name;
    private String reward_name;
    private Integer is_Cash;
    private Date winning_date;
    private Date cashing_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public Integer getIs_Cash() {
        return is_Cash;
    }

    public void setIs_Cash(Integer is_Cash) {
        this.is_Cash = is_Cash;
    }

    public Date getWinning_date() {
        return winning_date;
    }

    public void setWinning_date(Date winning_date) {
        this.winning_date = winning_date;
    }

    public Date getCashing_date() {
        return cashing_date;
    }

    public void setCashing_date(Date cashing_date) {
        this.cashing_date = cashing_date;
    }
}
