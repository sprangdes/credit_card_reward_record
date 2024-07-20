package com.jerry.credit_card_reward_record.model;

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
@Table(name = "consumptions")
@Schema(description = "消費通路實體類")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "consumptionId")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "消費通路編號")
    private long consumptionId;

    @Schema(description = "消費通路名稱")
    private String consumptionName;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "consumption_id"), inverseJoinColumns = @JoinColumn(name = "reward_way_id"))
    @JsonIgnoreProperties("consumptions")
    @Schema(description = "所屬回饋方式")
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
