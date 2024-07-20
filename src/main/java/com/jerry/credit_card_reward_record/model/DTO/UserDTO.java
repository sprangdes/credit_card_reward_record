package com.jerry.credit_card_reward_record.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "使用者更新實體類")
public class UserDTO {

    @Schema(description = "欲更新使用者編號")
    private long userId;

    @Schema(description = "新使用者名稱")
    private String userName;

    @Schema(description = "新使用者密碼")
    private String password;
}
