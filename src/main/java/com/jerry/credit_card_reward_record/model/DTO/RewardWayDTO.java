package com.jerry.credit_card_reward_record.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "回饋方式更新實體類")
public class RewardWayDTO {

    @Schema(description = "欲更新回饋方式編號")
    private long rewardWayId;

    @Schema(description = "新回饋方式名稱")
    private String rewardWayName;

    @Schema(description = "新回饋上限")
    private int rewardLimit;

    @Schema(description = "新回饋％數")
    private float rewardRate;
}
