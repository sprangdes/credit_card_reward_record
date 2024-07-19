package com.jerry.credit_card_reward_record.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardWayDTO {

    private long rewardWayId;
    private String rewardWayName;
    private int rewardLimit;
    private float rewardRate;
}
