package com.sporthustle.hustle.competitions.competition;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.dto.*;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.entity.CompetitionContact;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepositoryCustom;
import com.sporthustle.hustle.competitions.competition.repository.condition.CompetitionsBeforeCompleteCondition;
import com.sporthustle.hustle.competitions.competition.repository.condition.RecentCompleteCompetitionsCondition;
import com.sporthustle.hustle.sport.SportUtils;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.sport.repository.SportEventRepository;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompetitionService {

  private final CompetitionRepository competitionRepository;
  private final CompetitionRepositoryCustom competitionRepositoryCustom;
  private final SportEventRepository sportEventRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public CompetitionsResponseDTO getCompetitionsByListType(
          Long sportEventId, CompetitionStateRequest competitionStateRequest, Pageable pageable) {
    Page<Competition> competitions = Page.empty();

    switch (competitionStateRequest) {
      case ACTIVE:
        competitions = this.getCompetitionsBeforeComplete(sportEventId, pageable);
        break;
      case COMPLETE:
        competitions = this.getCompleteCompetitions(sportEventId, pageable);
        break;
      default:
        throw new IllegalArgumentException("CompetitionStateRequest 해당되는 값이 없습니다.");
    }

    Page<CompetitionResponseDTO> competitionResponseDTOs =
        competitions.map(competition -> CompetitionResponseDTO.from(competition));

    return CompetitionsResponseDTO.builder()
        .count(competitionResponseDTOs.getNumberOfElements())
        .totalPage(competitionResponseDTOs.getTotalPages())
        .totalCount(competitionResponseDTOs.getTotalElements())
        .data(competitionResponseDTOs.getContent())
        .build();
  }

  private Page<Competition> getCompetitionsBeforeComplete(Long sportEventId, Pageable pageable) {
    Page<Competition> competitions =
        competitionRepositoryCustom.getCompetitionsBeforeComplete(
            CompetitionsBeforeCompleteCondition.builder().sportEventId(sportEventId).build(),
            pageable);
    return competitions;
  }

  private Page<Competition> getCompleteCompetitions(Long sportEventId, Pageable pageable) {
    Page<Competition> competitions =
        competitionRepositoryCustom.getRecentCompleteCompetitions(
            RecentCompleteCompetitionsCondition.builder().sportEventId(sportEventId).build(),
            pageable);
    return competitions;
  }

  @Transactional(readOnly = true)
  public CompetitionResponseDTO getCompetition(Long competitionId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    return CompetitionResponseDTO.from(competition);
  }

  @Transactional
  public CreateCompetitionResponseDTO createCompetition(
      Long userId, CreateCompetitionRequestDTO createCompetitionRequestDTO) {

    Competition competition =
        Competition.builder()
            .title(createCompetitionRequestDTO.getTitle())
            .host(createCompetitionRequestDTO.getHost())
            .place(createCompetitionRequestDTO.getPlace())
            .startDate(createCompetitionRequestDTO.getStartDate())
            .endDate(createCompetitionRequestDTO.getEndDate())
            .recruitmentStartDate(createCompetitionRequestDTO.getRecruitmentStartDate())
            .recruitmentEndDate(createCompetitionRequestDTO.getRecruitmentEndDate())
            .entryFee(createCompetitionRequestDTO.getEntryFee())
            .maxEntryCount(createCompetitionRequestDTO.getMaxEntryCount())
            .sponsor(createCompetitionRequestDTO.getSponsor())
            .posterUrl(createCompetitionRequestDTO.getPosterUrl())
            .preRoundGroupCount(createCompetitionRequestDTO.getPreRoundGroupCount())
            .finalRoundTeamCount(createCompetitionRequestDTO.getFinalRoundTeamCount())
            .build();

    User user = UserUtils.getUserById(userId, userRepository);
    competition.updateUser(user);

    List<CompetitionContactRequestDTO> contactResponseDTOs =
        createCompetitionRequestDTO.getContacts();
    Set<CompetitionContact> contacts =
        contactResponseDTOs.stream()
            .map(
                competitionContactRequestDTO -> {
                  CompetitionContact competitionContact =
                      CompetitionContact.builder()
                          .name(competitionContactRequestDTO.getName())
                          .phoneNumber(competitionContactRequestDTO.getPhoneNumber())
                          .build();
                  competitionContact.updateCompetition(competition);
                  return competitionContact;
                })
            .collect(Collectors.toSet());
    competition.updateContacts(contacts);

    Long sportEventId = createCompetitionRequestDTO.getSportEventId();
    SportEvent sportEvent = SportUtils.getSportEventById(sportEventId, sportEventRepository);
    competition.updateSportEvent(sportEvent);

    competitionRepository.save(competition);

    CompetitionResponseDTO competitionResponseDTO = CompetitionResponseDTO.from(competition);

    return CreateCompetitionResponseDTO.builder()
        .message("대회를 개설했습니다.")
        .data(competitionResponseDTO)
        .build();
  }

  @Transactional
  public UpdateCompetitionResponseDTO updateCompetition(
      Long userId, Long competitionId, UpdateCompetitionRequestDTO updateCompetitionRequestDTO) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionOwner(competition, userId);

    competition.update(
        updateCompetitionRequestDTO.getTitle(),
        updateCompetitionRequestDTO.getHost(),
        updateCompetitionRequestDTO.getPlace(),
        updateCompetitionRequestDTO.getStartDate(),
        updateCompetitionRequestDTO.getEndDate(),
        updateCompetitionRequestDTO.getRecruitmentStartDate(),
        updateCompetitionRequestDTO.getRecruitmentEndDate(),
        updateCompetitionRequestDTO.getEntryFee(),
        updateCompetitionRequestDTO.getMaxEntryCount(),
        updateCompetitionRequestDTO.getSponsor(),
        updateCompetitionRequestDTO.getPosterUrl(),
        updateCompetitionRequestDTO.getPreRoundGroupCount(),
        updateCompetitionRequestDTO.getFinalRoundTeamCount());

    Long sportEventId = updateCompetitionRequestDTO.getSportEventId();
    SportEvent sportEvent = SportUtils.getSportEventById(sportEventId, sportEventRepository);
    competition.updateSportEvent(sportEvent);

    List<CompetitionContactRequestDTO> contactResponseDTOs =
        updateCompetitionRequestDTO.getContacts();
    Set<CompetitionContact> contacts =
        contactResponseDTOs.stream()
            .map(
                competitionContactRequestDTO -> {
                  CompetitionContact competitionContact =
                      CompetitionContact.builder()
                          .name(competitionContactRequestDTO.getName())
                          .phoneNumber(competitionContactRequestDTO.getPhoneNumber())
                          .build();
                  competitionContact.updateCompetition(competition);
                  return competitionContact;
                })
            .collect(Collectors.toSet());
    competition.updateContacts(contacts);

    competitionRepository.save(competition);

    CompetitionResponseDTO competitionResponseDTO = CompetitionResponseDTO.from(competition);

    return UpdateCompetitionResponseDTO.builder()
        .message("대회를 성공적으로 수정했습니다.")
        .data(competitionResponseDTO)
        .build();
  }

  private void validateCompetitionOwner(Competition competition, Long userId) {
    User user = competition.getUser();
    if (user.getId() != userId) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }

  @Transactional
  public DeleteCompetitionResponseDTO deleteCompetition(Long userId, Long competitionId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionOwner(competition, userId);

    competition.delete();
    competitionRepository.save(competition);

    return DeleteCompetitionResponseDTO.builder().message("대회를 성공적으로 삭제했습니다.").build();
  }

  @Transactional(readOnly = true)
  public CompetitionsResponseDTO getPopularCompetitions(Pageable pageable) {
    Page<Competition> competitions = competitionRepositoryCustom.getPopularCompetitions(pageable);

    Page<CompetitionResponseDTO> competitionResponseDTOs =
        competitions.map(competition -> CompetitionResponseDTO.from(competition));

    return CompetitionsResponseDTO.builder()
        .count(competitionResponseDTOs.getNumberOfElements())
        .totalPage(competitionResponseDTOs.getTotalPages())
        .totalCount(competitionResponseDTOs.getTotalElements())
        .data(competitionResponseDTOs.getContent())
        .build();
  }
}
