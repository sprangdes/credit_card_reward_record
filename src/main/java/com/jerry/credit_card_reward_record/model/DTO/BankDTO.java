package com.jerry.credit_card_reward_record.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "發卡銀行更新實體類")
public class BankDTO {

    @Schema(description = "欲更新發卡銀行編號")
    private long bankId;

    @Schema(description = "新發卡銀行名稱")
    private String bankName;
}
