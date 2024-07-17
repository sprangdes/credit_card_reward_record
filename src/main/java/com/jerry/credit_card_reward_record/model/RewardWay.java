package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "reward_ways")
public class RewardWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rewardWayId;

    private String rewardWayName;

    private int rewardLimit;

    private float rewardRate;

    @ManyToOne
    @JsonBackReference
    private Card card;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "reward_way_id"), inverseJoinColumns = @JoinColumn(name = "consumption_id"))
    @JsonIgnoreProperties("reward_ways")
    private Set<Consumption> consumptions;

    public RewardWay() {
    }

    public RewardWay(long rewardWayId, String rewardWayName, int rewardLimit, float rewardRate) {
        this.rewardWayId = rewardWayId;
        this.rewardWayName = rewardWayName;
        this.rewardLimit = rewardLimit;
        this.rewardRate = rewardRate;
    }

    public RewardWay(String rewardWayName, int rewardLimit, float rewardRate) {
        this.rewardWayName = rewardWayName;
        this.rewardLimit = rewardLimit;
        this.rewardRate = rewardRate;
    }
}
