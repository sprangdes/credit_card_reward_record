package com.jerry.credit_card_reward_record.model.DTO;

import com.jerry.credit_card_reward_record.model.Bank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "信用卡更新實體類")
public class CardDTO {

    @Schema(description = "欲更新信用卡編號")
    private long cardId;

    @Schema(description = "新發卡銀行編號")
    private long bankId;

    @Schema(description = "新信用卡名稱")
    private String cardName;
}
