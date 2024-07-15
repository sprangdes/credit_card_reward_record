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
    private long reward_way_id;

    private String reward_way_name;

    private int reward_limit;

    private float reward_rate;

    @ManyToOne
    @JsonBackReference
    private Card card;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "reward_way_id"), inverseJoinColumns = @JoinColumn(name = "consumption_id"))
    @JsonIgnoreProperties("reward_ways")
    private Set<Consumption> consumptions;

    public RewardWay() {
    }

    public RewardWay(long reward_way_id, String reward_way_name, int reward_limit, float reward_rate) {
        this.reward_way_id = reward_way_id;
        this.reward_way_name = reward_way_name;
        this.reward_limit = reward_limit;
        this.reward_rate = reward_rate;
    }

    public RewardWay(String reward_way_name, int reward_limit, float reward_rate) {
        this.reward_way_name = reward_way_name;
        this.reward_limit = reward_limit;
        this.reward_rate = reward_rate;
    }
}
