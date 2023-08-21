package com.sporthustle.hustle.friendmatch.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class ApplyRequestDTO {
    @NotBlank(message = "클럽 선택은 필수입력 값입니다.")
    String clubName;
    @NotBlank(message = "대표자 이름은 필수입력 값입니다.")
    String name;
    @NotBlank(message = "대표자 연락처는 필수입력 값입니다.")
    String phoneNumber;
    String location;
    String category;
}
