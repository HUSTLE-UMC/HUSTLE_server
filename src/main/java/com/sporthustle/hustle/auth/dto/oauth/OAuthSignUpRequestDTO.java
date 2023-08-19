package com.sporthustle.hustle.auth.dto.oauth;

import com.sporthustle.hustle.user.entity.SnsType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
public class OAuthSignUpRequestDTO {

    @NotBlank(message = "SNS ID는 필수 입력 값입니다.")
    private String snsId;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-z0-9]{6,20}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "생일은 필수 입력 값입니다.")
    private LocalDate birthday;

    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String gender;

    @NotNull(message = "대학교는 필수 입력 값입니다.")
    private Long universityId;

    private String snsId;

    private SnsType snsType;

    private Boolean isMailing = true;

}
