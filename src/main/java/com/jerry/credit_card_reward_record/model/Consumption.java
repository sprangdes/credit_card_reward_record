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
    private long consumption_id;

    private String consumption_name;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "consumption_id"), inverseJoinColumns = @JoinColumn(name = "reward_way_id"))
    @JsonIgnoreProperties("consumptions")
    private Set<RewardWay> reward_ways;

    public Consumption() {
    }

    public Consumption(long consumption_id, String consumption_name) {
        this.consumption_id = consumption_id;
        this.consumption_name = consumption_name;
    }

    public Consumption(String consumption_name) {
        this.consumption_name = consumption_name;
    }
}
