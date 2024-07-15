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
    private long card_id;

    @ManyToOne
    @JsonBackReference
    private Bank bank;

    private String card_name;

    @OneToMany(mappedBy = "card")
    @JsonManagedReference
    private Set<RewardWay> reward_ways;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("cards")
    private Set<User> users;

    public Card() {
    }

    public Card(Bank bank, String card_name) {
        this.bank = bank;
        this.card_name = card_name;
    }

    public Card(long card_id, Bank bank, String card_name) {
        this.card_id = card_id;
        this.bank = bank;
        this.card_name = card_name;
    }
}
