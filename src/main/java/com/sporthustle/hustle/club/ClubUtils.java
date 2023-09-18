package com.sporthustle.hustle.club;

import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.user.entity.User;

public class ClubUtils {

  public static Club getClubById(Long clubId, ClubRepository clubRepository) {
    Club club =
        clubRepository
            .findById(clubId)
            .orElseThrow(() -> BaseException.from(ErrorCode.CLUB_NOT_FOUND));
    return club;
  }

  public static void validateUserIsMemberInClub(User user, Club club) {
    boolean isUserInClubMembers =
        user.getClubMembers().stream()
            .anyMatch(clubMember -> clubMember.getClub().getId() == club.getId());
    if (!isUserInClubMembers) {
      throw BaseException.from(ErrorCode.MEMBER_NOT_IN_CLUB);
    }
  }
}
