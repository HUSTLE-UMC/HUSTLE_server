package com.sporthustle.hustle.competitions.entryteam;

import com.sporthustle.hustle.club.ClubUtils;
import com.sporthustle.hustle.club.entity.Club;
import com.sporthustle.hustle.club.repository.ClubRepository;
import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.CompetitionUtils;
import com.sporthustle.hustle.competitions.competition.dto.CompetitionState;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.entryteam.dto.*;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.competitions.entryteam.repository.EntryTeamRepository;
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
public class EntryTeamService {

  private final EntryTeamRepository entryTeamRepository;
  private final UserRepository userRepository;
  private final CompetitionRepository competitionRepository;
  private final ClubRepository clubRepository;

  @Transactional(readOnly = true)
  public EntryTeamsResponseDTO getEntryTeams(Long competitionId) {
    List<EntryTeam> entryTeams = entryTeamRepository.findAllByCompetition_Id(competitionId);

    List<EntryTeamResponseDTO> entryTeamDTOs =
        entryTeams.stream()
            .map(entryTeam -> EntryTeamResponseDTO.from(entryTeam))
            .collect(Collectors.toList());

    return EntryTeamsResponseDTO.builder().entryTeams(entryTeamDTOs).build();
  }

  @Transactional
  public CreateEntryTeamResponseDTO createEntryTeam(
      Long userId, Long competitionId, CreateEntryTeamRequestDTO createEntryTeamRequestDTO) {
    User user = UserUtils.getUserById(userId, userRepository);
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);
    Club club = ClubUtils.getClubById(createEntryTeamRequestDTO.getClubId(), clubRepository);

    EntryTeam entryTeam =
        EntryTeam.builder()
            .name(createEntryTeamRequestDTO.getName())
            .phoneNumber(createEntryTeamRequestDTO.getPhoneNumber())
            .build();

    entryTeam.updateUser(user);
    entryTeam.updateCompetition(competition);
    entryTeam.updateClub(club);

    entryTeamRepository.save(entryTeam);

    EntryTeamResponseDTO entryTeamResponseDTO = EntryTeamResponseDTO.from(entryTeam);

    return CreateEntryTeamResponseDTO.builder()
        .message("대회 참가에 성공하였습니다.")
        .data(entryTeamResponseDTO)
        .build();
  }

  @Transactional
  public DeleteEntryTeamResponseDTO deleteEntryTeam(Long userId, Long competitionId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionInRecruiting(competition);

    EntryTeam entryTeam =
        entryTeamRepository
            .findByUser_IdAndCompetition_id(userId, competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.ENTRY_TEAM_NOT_FOUND));

    entryTeamRepository.delete(entryTeam);

    EntryTeamResponseDTO entryTeamResponseDTO = EntryTeamResponseDTO.from(entryTeam);

    return DeleteEntryTeamResponseDTO.builder()
        .message("대회 참가를 취소했습니다.")
        .data(entryTeamResponseDTO)
        .build();
  }

  private void validateCompetitionInRecruiting(Competition competition) {
    if (competition == null) {
      throw new IllegalArgumentException("대회 값이 null 입니다.");
    }

    CompetitionState competitionState =
        CompetitionState.from(
            competition.getRecruitmentStartDate(),
            competition.getRecruitmentEndDate(),
            competition.getStartDate(),
            competition.getEndDate());

    if (competitionState != CompetitionState.RECRUITING) {
      throw new IllegalArgumentException("대회 참가를 변경할 수 있는 기간이 아닙니다.");
    }
  }
}
