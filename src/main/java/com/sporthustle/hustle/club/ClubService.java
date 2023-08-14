package com.sporthustle.hustle.club;

import com.sporthustle.hustle.club.dto.*;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.entity.ClubMember;
import com.sporthustle.hustle.club.repository.ClubMemberRepository;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.university.repository.UniversityRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClubService {

  private final ClubRepository clubRepository;
  private final ClubMemberRepository clubMemberRepository;
  private final UniversityRepository universityRepository;
  private final SportEventRepository sportEventRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<MyClubsResponseDTO> getMyClubs(Long userId) {
    List<ClubMember> clubMembers = clubMemberRepository.findAllByUser_id(userId);

    List<Long> clubIds =
        clubMembers.stream()
            .map(clubMember -> clubMember.getClub().getId())
            .collect(Collectors.toList());

    List<Club> clubs = clubRepository.findAllByIdIn(clubIds);
    List<MyClubsResponseDTO> myClubsResponseDTOs =
        clubs.stream()
            .map(club -> MyClubsResponseDTO.builder().id(club.getId()).name(club.getName()).build())
            .collect(Collectors.toList());

    return myClubsResponseDTOs;
  }

  @Transactional(readOnly = true)
  public ClubsResponseDTO findClubsInUniversity(Long universityId, String keyword) {
    List<Club> clubs =
        clubRepository.findAllByUniversity_idAndNameStartsWith(universityId, keyword);

    List<ClubResponseDTO> clubResponseDTOs =
        clubs.stream().map(club -> ClubResponseDTO.from(club)).collect(Collectors.toList());

    ClubsResponseDTO clubsResponseDTO = ClubsResponseDTO.builder().clubs(clubResponseDTOs).build();
    return clubsResponseDTO;
  }

  @Transactional
  public CreateClubResponseDTO createClub(CreateClubRequestDTO createClubRequestDTO) {
    Club club =
        Club.builder()
            .name(createClubRequestDTO.getName())
            .instagram(createClubRequestDTO.getInstagram())
            .youtubeUrl(createClubRequestDTO.getYoutubeUrl())
            .mainArea(createClubRequestDTO.getMainArea())
            .profileImageUrl(createClubRequestDTO.getProfileImageUrl())
            .build();

    University university =
        universityRepository
            .findById(createClubRequestDTO.getUniversityId())
            .orElseThrow(() -> BaseException.from(ErrorCode.UNIVERSITY_NOT_FOUND));
    club.updateUniversity(university);

    SportEvent sportEvent =
        sportEventRepository
            .findById(createClubRequestDTO.getSportEventId())
            .orElseThrow(() -> BaseException.from(ErrorCode.SPORT_EVENT_NOT_FOUND));
    club.updateSportEvent(sportEvent);

    clubRepository.save(club);

    ClubResponseDTO clubResponseDTO = ClubResponseDTO.from(club);

    return CreateClubResponseDTO.builder().message("동아리를 생성했습니다.").data(clubResponseDTO).build();
  }

  @Transactional
  public UpdateClubResponseDTO updateClub(Long clubId, UpdateClubRequestDTO updateClubRequestDTO) {
    Club club = ClubUtils.getClubById(clubId, clubRepository);

    if (updateClubRequestDTO.getUniversityId() != null) {
      University university =
          universityRepository
              .findById(updateClubRequestDTO.getUniversityId())
              .orElseThrow(() -> BaseException.from(ErrorCode.UNIVERSITY_NOT_FOUND));
      club.updateUniversity(university);
    }

    if (updateClubRequestDTO.getSportEventId() != null) {
      SportEvent sportEvent =
          sportEventRepository
              .findById(updateClubRequestDTO.getSportEventId())
              .orElseThrow(() -> BaseException.from(ErrorCode.SPORT_EVENT_NOT_FOUND));
      club.updateSportEvent(sportEvent);
    }

    club.update(
        updateClubRequestDTO.getName(),
        updateClubRequestDTO.getInstagram(),
        updateClubRequestDTO.getYoutubeUrl(),
        updateClubRequestDTO.getMainArea(),
        updateClubRequestDTO.getProfileImageUrl());

    clubRepository.save(club);

    return UpdateClubResponseDTO.builder().message("동아리를 성공적으로 수정했습니다.").build();
  }

  @Transactional
  public DeleteClubResponseDTO deleteClub(Long clubId) {
    Club club = ClubUtils.getClubById(clubId, clubRepository);

    club.delete();
    clubRepository.save(club);

    return DeleteClubResponseDTO.builder().message("동아리를 성공적으로 수정했습니다.").build();
  }

  @Transactional
  public JoinClubResponseDTO joinClub(Long userId, Long clubId) {
    User user = UserUtils.getUserById(userId, userRepository);

    Club club = ClubUtils.getClubById(clubId, clubRepository);

    ClubMember clubMember = ClubMember.builder().user(user).club(club).build();
    clubMemberRepository.save(clubMember);

    return JoinClubResponseDTO.builder().message("동아리에 성공적으로 등록했습니다.").build();
  }

  @Transactional(readOnly = true)
  public GetClubMembersResponseDTO getClubMembers(Long userId, Long clubId) {
    User user = UserUtils.getUserById(userId, userRepository);
    validateUserInClub(user, clubId);

    List<ClubMember> clubMembers = clubMemberRepository.findAllByClub_id(clubId);
    List<ClubMemberResponseDTO> clubMembersResponseDTO =
        clubMembers.stream()
            .map(clubMember -> ClubMemberResponseDTO.from(clubMember))
            .collect(Collectors.toList());

    return GetClubMembersResponseDTO.builder().clubMembers(clubMembersResponseDTO).build();
  }

  private void validateUserInClub(User user, Long clubId) {
    clubMemberRepository
        .findByUser_idAndClub_id(user.getId(), clubId)
        .orElseThrow(() -> BaseException.from(ErrorCode.MEMBER_NOT_IN_CLUB));
  }
}
