package com.jerry.credit_card_reward_record.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardWayDTO {

    private long reward_way_id;
    private String reward_way_name;
    private int reward_limit;
    private float reward_rate;
}
