package com.jerry.credit_card_reward_record.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "消費通路更新實體類")
public class ConsumptionDTO {

    @Schema(description = "欲更新消費通路編號")
    private long consumptionId;

    @Schema(description = "新消費通路名稱")
    private String consumptionName;
}
