package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "reward_ways")
@Schema(description = "回饋方式實體類")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rewardWayId")
public class RewardWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "回饋方式編號")
    private long rewardWayId;

    @Schema(description = "回饋方式名稱")
    private String rewardWayName;

    @Schema(description = "回饋上限")
    private int rewardLimit;

    @Schema(description = "回饋％數")
    private float rewardRate;

    @ManyToOne
    @JsonBackReference
    @Schema(description = "所屬信用卡")
    private Card card;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "reward_way_id"), inverseJoinColumns = @JoinColumn(name = "consumption_id"))
    @JsonIgnoreProperties("reward_ways")
    @Schema(description = "可使用的消費通路")
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
