package com.sporthustle.hustle.src.club.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sporthustle.hustle.src.university.entity.University;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ClubDTO { //임시
    private String profileImageUrl;
    private String name;
    private String universityName;
}
