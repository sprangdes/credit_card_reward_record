package com.jerry.credit_card_reward_record.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_accumulate_rewards")
public class UserAccumulatedReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userAccumulatedRewardId;

    private long userId;

    private long rewardWayId;

    private int accumulatedReward;

    public UserAccumulatedReward() {
    }

    public UserAccumulatedReward(long userId, long rewardWayId, int accumulatedReward) {
        this.userId = userId;
        this.rewardWayId = rewardWayId;
        this.accumulatedReward = accumulatedReward;
    }
}
