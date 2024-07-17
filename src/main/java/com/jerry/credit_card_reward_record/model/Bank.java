package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankId;

    private String bankName;

    @OneToMany(mappedBy = "bank")
    @JsonManagedReference
    private Set<Card> cards;

    public Bank() {
    }

    public Bank(long bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }
}
