package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "banks")
@Schema(description = "發卡銀行實體類")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bankId")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "發卡銀行編號")
    private long bankId;

    @Schema(description = "發卡銀行名稱")
    private String bankName;

    @OneToMany(mappedBy = "bank")
    @JsonManagedReference
    @Schema(description = "發行的信用卡")
    private Set<Card> cards;

    public Bank() {
    }

    public Bank(long bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public Bank(String bankName) {
        this.bankName = bankName;
    }
}
