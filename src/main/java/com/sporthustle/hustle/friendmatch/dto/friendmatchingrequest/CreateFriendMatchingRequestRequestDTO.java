package com.sporthustle.hustle.friendmatch.dto.friendmatchingrequest;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingRequestType;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CreateFriendMatchingRequestRequestDTO {
    @NotBlank(message = "대표자 이름은 필수입력 값입니다.")
    String name;
    @NotBlank(message = "대표자 연락처는 필수입력 값입니다.")
    String phoneNumber;
    Point location;
    String locationAddress;
    FriendMatchingRequestType type;
}
