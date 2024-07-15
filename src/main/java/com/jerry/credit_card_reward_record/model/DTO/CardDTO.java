package com.jerry.credit_card_reward_record.model.DTO;

import com.jerry.credit_card_reward_record.model.Bank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardDTO {

    private long card_id;
    private Bank bank;
    private String card_name;
}
