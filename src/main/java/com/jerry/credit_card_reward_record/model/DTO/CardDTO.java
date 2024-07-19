package com.jerry.credit_card_reward_record.model.DTO;

import com.jerry.credit_card_reward_record.model.Bank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardDTO {

    private long cardId;
    private Bank bank;
    private String cardName;
}
