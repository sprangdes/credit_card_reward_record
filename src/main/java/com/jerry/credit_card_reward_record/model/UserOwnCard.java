package com.jerry.credit_card_reward_record.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_own_card")
public class UserOwnCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userOwnCardId;

    private long userId;

    private long cardId;

    private float accumulatedReward;

    public UserOwnCard() {
    }

    public UserOwnCard(long userId, long cardId, float accumulatedReward) {
        this.userId = userId;
        this.cardId = cardId;
        this.accumulatedReward = accumulatedReward;
    }
}
