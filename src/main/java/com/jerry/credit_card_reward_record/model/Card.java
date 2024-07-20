package com.jerry.credit_card_reward_record.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "cards")
@Schema(description = "信用卡實體類")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cardId")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "信用卡編號")
    private long cardId;

    @ManyToOne
    @JsonBackReference
    @Schema(description = "發卡銀行")
    private Bank bank;

    @Schema(description = "信用卡名稱")
    private String cardName;

    @OneToMany(mappedBy = "card")
    @JsonManagedReference
    @Schema(description = "信用卡回饋方式")
    private Set<RewardWay> rewardWays;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("cards")
    @Schema(description = "持卡使用者")
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
