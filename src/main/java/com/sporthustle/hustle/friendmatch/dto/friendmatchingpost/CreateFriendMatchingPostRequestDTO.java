package com.sporthustle.hustle.friendmatch.dto.friendmatchingpost;

import com.sporthustle.hustle.friendmatch.entity.FriendMatchingPostType;
import org.locationtech.jts.geom.Point;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class CreateFriendMatchingPostRequestDTO {
    @NotBlank(message = "교류전 제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "글 종류는 필수 입력 값입니다.")
    private FriendMatchingPostType category;

    @NotBlank(message = "대표자 이름은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "연락처는 필수 입력 값입니다.")
    private String phoneNumber;

    @NotNull(message = "일시는 필수 입력 값입니다.")
    private LocalDateTime startDate;

    @NotNull(message = "교류전 위치는 필수 입력 값입니다.(좌표)")
    private Point location;

    @NotNull(message = "교류전 주소는 필수 입력 값입니다.")
    private String locationAddress;

    @NotNull(message = "종목은 필수 입력 값입니다.")
    private Long sportEventId;

    @NotNull(message = "동아리는 필수 입력 값입니다.")
    private Long clubId;

}
