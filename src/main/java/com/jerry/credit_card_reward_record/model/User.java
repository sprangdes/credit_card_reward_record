package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String user_name;

    private String password;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "card_id"))
    @JsonIgnoreProperties("users")
    private Set<Card> cards;

    public User() {
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public User(long user_id, String user_name, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }
}
