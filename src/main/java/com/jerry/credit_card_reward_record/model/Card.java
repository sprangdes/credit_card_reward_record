package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;

    @ManyToOne
    @JsonBackReference
    private Bank bank;

    private String cardName;

    @OneToMany(mappedBy = "card")
    @JsonManagedReference
    private Set<RewardWay> rewardWays;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("cards")
    private Set<User> users;

    public Card() {
    }

    public Card(Bank bank, String cardName) {
        this.bank = bank;
        this.cardName = cardName;
    }

    public Card(long cardId, Bank bank, String cardName) {
        this.cardId = cardId;
        this.bank = bank;
        this.cardName = cardName;
    }
}
