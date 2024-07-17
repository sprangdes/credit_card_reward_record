package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long consumptionId;

    private String consumptionName;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "consumption_id"), inverseJoinColumns = @JoinColumn(name = "reward_way_id"))
    @JsonIgnoreProperties("consumptions")
    private Set<RewardWay> rewardWays;

    public Consumption() {
    }

    public Consumption(long consumptionId, String consumptionName) {
        this.consumptionId = consumptionId;
        this.consumptionName = consumptionName;
    }

    public Consumption(String consumptionName) {
        this.consumptionName = consumptionName;
    }
}
