package com.sporthustle.hustle.user.dto;

import com.sporthustle.hustle.user.entity.Gender;
import com.sporthustle.hustle.user.entity.User;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class JoinClubUserResponseDTO {
    private Long id;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    public static JoinClubUserResponseDTO from(User user) {
        return JoinClubUserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .build();
    }
}
