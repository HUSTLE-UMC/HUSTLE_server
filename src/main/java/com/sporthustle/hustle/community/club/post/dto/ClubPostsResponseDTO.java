package com.sporthustle.hustle.community.club.post.dto;

import com.sporthustle.hustle.common.dto.BasePageable;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ClubPostsResponseDTO extends BasePageable<ClubPostResponseDTO> {}
