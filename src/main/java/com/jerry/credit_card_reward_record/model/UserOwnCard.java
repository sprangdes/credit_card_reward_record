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

    public UserOwnCard() {
    }

    public UserOwnCard(long userId, long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }
}
