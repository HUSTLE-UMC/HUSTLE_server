package com.sporthustle.hustle.competitions.match;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.CompetitionUtils;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.entryteam.EntryTeamUtils;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.competitions.entryteam.repository.EntryTeamRepository;
import com.sporthustle.hustle.competitions.ingame.InGameService;
import com.sporthustle.hustle.competitions.ingame.dto.InGameCategory;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import com.sporthustle.hustle.competitions.match.dto.*;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPost;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPostCategory;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPostScoreLog;
import com.sporthustle.hustle.competitions.match.repository.MatchResultPostRepository;
import com.sporthustle.hustle.competitions.match.repository.MatchResultPostRepositoryCustom;
import com.sporthustle.hustle.competitions.match.repository.MatchResultPostScoreLogRepository;
import com.sporthustle.hustle.competitions.match.repository.condition.GetMatchResultPostsCondition;
import com.sporthustle.hustle.user.entity.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatchService {

  private final MatchResultPostRepository matchResultPostRepository;
  private final MatchResultPostRepositoryCustom matchResultPostRepositoryCustom;
  private final CompetitionRepository competitionRepository;
  private final EntryTeamRepository entryTeamRepository;
  private final MatchResultPostScoreLogRepository matchResultPostScoreLogRepository;
  private final InGameService inGameService;

  @Transactional(readOnly = true)
  public MatchResultPostsResponseDTO getMatchResultPosts(
      Long competitionId, InGameCategory inGameCategory, String groupCategory) {

    List<MatchResultPost> matchResultPosts =
        matchResultPostRepositoryCustom.getMatchResultPosts(
            GetMatchResultPostsCondition.builder()
                .competitionId(competitionId)
                .category(inGameCategory)
                .groupCategory(groupCategory)
                .build());

    List<MatchResultPostResponseDTO> matchResultPostResponseDTOs =
        matchResultPosts.stream()
            .map(MatchResultPostResponseDTO::from)
            .collect(Collectors.toList());

    return MatchResultPostsResponseDTO.builder()
        .matchResultPosts(matchResultPostResponseDTOs)
        .build();
  }

  @Transactional
  public CreateMatchResultPostResponseDTO createMatchResultPost(
      Long userId,
      Long competitionId,
      InGameCategory inGameCategory,
      CreateMatchRequestPostsRequestDTO createMatchRequestPostsRequestDTO) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionOwner(competition, userId);

    String groupCategory = createMatchRequestPostsRequestDTO.getGroupCategory();
    List<MatchResultPost> matchResultPosts =
        createMatchRequestPostsRequestDTO.getMatchResultPosts().stream()
            .map(
                createMatchResultPostRequestDTO -> {
                  String title =
                      groupCategory + " " + createMatchResultPostRequestDTO.getPostOrder() + "경기";

                  MatchResultPost matchResultPost =
                      MatchResultPost.builder()
                          .title(title)
                          .category(inGameCategory.name())
                          .groupCategory(groupCategory)
                          .postOrder(createMatchResultPostRequestDTO.getPostOrder())
                          .matchTime(createMatchResultPostRequestDTO.getMatchTime())
                          .mediaUrl(null)
                          .build();

                  matchResultPost.updateCompetition(competition);

                  EntryTeam homeEntryTeam =
                      EntryTeamUtils.getEntryTeamByIdAndCompetitionId(
                          createMatchResultPostRequestDTO.getHomeEntryTeamId(),
                          competitionId,
                          entryTeamRepository);

                  matchResultPost.updateHomeTeam(homeEntryTeam, 0, false, null);

                  EntryTeam awayEntryTeam =
                      EntryTeamUtils.getEntryTeamByIdAndCompetitionId(
                          createMatchResultPostRequestDTO.getAwayEntryTeamId(),
                          competitionId,
                          entryTeamRepository);

                  matchResultPost.updateAwayTeam(awayEntryTeam, 0, false, null);

                  return matchResultPost;
                })
            .collect(Collectors.toList());

    if (inGameCategory == InGameCategory.PREROUND) {
      inGameService.deleteAllPreRoundGroup(competitionId, groupCategory);
    }

    deleteAllMatchResult(competitionId, groupCategory);

    matchResultPostRepository.saveAll(matchResultPosts);

    if (inGameCategory == InGameCategory.PREROUND) {
      Set<EntryTeam> entryTeams =
              matchResultPosts.stream()
                      .flatMap(
                              matchResultPost ->
                                      List.of(matchResultPost.getHomeEntryTeam(), matchResultPost.getAwayEntryTeam())
                                              .stream())
                      .collect(Collectors.toSet());

      entryTeams.stream()
              .forEach(
                      entryTeam ->
                              inGameService.createPreRoundGroup(competitionId, entryTeam.getId(), groupCategory));
    }

    List<MatchResultPostResponseDTO> matchResultPostResponseDTOs =
        matchResultPosts.stream()
            .map(MatchResultPostResponseDTO::from)
            .collect(Collectors.toList());

    return CreateMatchResultPostResponseDTO.builder()
        .message("경기 결과를 작성했습니다.")
        .data(matchResultPostResponseDTOs)
        .build();
  }

  private void validateCompetitionOwner(Competition competition, Long userId) {
    User user = competition.getUser();
    if (user.getId() != userId) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }

  @Transactional(readOnly = true)
  public MatchResultPostResponseDTO getMatchResultPost(
      Long competitionId, InGameCategory inGameCategory, Long matchResultPostId) {
    MatchResultPost matchResultPost =
        MatchUtils.getMatchResultPostById(matchResultPostId, matchResultPostRepository);

    validateMatchResultPostWithParameter(matchResultPost, competitionId, inGameCategory);

    return MatchResultPostResponseDTO.from(matchResultPost);
  }

  private void validateMatchResultPostWithParameter(
      MatchResultPost matchResultPost, Long competitionId, InGameCategory inGameCategory) {
    if (matchResultPost.getCompetition().getId() != competitionId) {
      throw BaseException.from(ErrorCode.MATCH_RESULT_POST_NOT_FOUND);
    }

    if (matchResultPost.getCategory() != MatchResultPostCategory.from(inGameCategory)) {
      throw BaseException.from(ErrorCode.MATCH_RESULT_POST_NOT_FOUND);
    }
  }

  @Transactional
  public UpdateMatchResultPostResponseDTO updateMatchResultPost(
      Long userId,
      Long competitionId,
      InGameCategory inGameCategory,
      Long matchResultPostId,
      UpdateMatchResultPostRequestDTO updateMatchResultPostRequestDTO) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionOwner(competition, userId);

    MatchResultPost matchResultPost =
        MatchUtils.getMatchResultPostById(matchResultPostId, matchResultPostRepository);

    validateMatchResultPostWithParameter(matchResultPost, competitionId, inGameCategory);

    matchResultPost.update(
        updateMatchResultPostRequestDTO.getTitle(),
        inGameCategory.name(),
        updateMatchResultPostRequestDTO.getGroupCategory(),
        updateMatchResultPostRequestDTO.getPostOrder(),
        updateMatchResultPostRequestDTO.getMatchTime(),
        updateMatchResultPostRequestDTO.getMediaUrl());

    matchResultPostScoreLogRepository.deleteAllByMatchResultPost_Id(matchResultPostId);

    EntryTeam homeEntryTeam =
        EntryTeamUtils.getEntryTeamByIdAndCompetitionId(
            updateMatchResultPostRequestDTO.getHomeEntryTeamId(),
            competitionId,
            entryTeamRepository);

    List<MatchResultPostScoreLog> homeMatchResultPostScoreLogs =
        updateMatchResultPostRequestDTO.getHomeMatchResultPostScoreLogs().stream()
            .map(
                updateMatchResultPostScoreLogRequestDTO -> {
                  MatchResultPostScoreLog matchResultPostScoreLog =
                      MatchResultPostScoreLog.builder()
                          .name(updateMatchResultPostScoreLogRequestDTO.getName())
                          .score(updateMatchResultPostScoreLogRequestDTO.getScore())
                          .extra(updateMatchResultPostScoreLogRequestDTO.getExtra())
                          .build();

                  matchResultPostScoreLog.updateMatchResultPost(matchResultPost);
                  matchResultPostScoreLog.updateUserId(
                      updateMatchResultPostScoreLogRequestDTO.getUserId());

                  return matchResultPostScoreLog;
                })
            .collect(Collectors.toList());

    matchResultPost.updateHomeTeam(
        homeEntryTeam,
        updateMatchResultPostRequestDTO.getHomeScore(),
        updateMatchResultPostRequestDTO.getIsHomeWin(),
        homeMatchResultPostScoreLogs);

    EntryTeam awayEntryTeam =
        EntryTeamUtils.getEntryTeamByIdAndCompetitionId(
            updateMatchResultPostRequestDTO.getAwayEntryTeamId(),
            competitionId,
            entryTeamRepository);

    List<MatchResultPostScoreLog> awayMatchResultPostScoreLogs =
        updateMatchResultPostRequestDTO.getAwayMatchResultPostScoreLogs().stream()
            .map(
                updateMatchResultPostScoreLogRequestDTO -> {
                  MatchResultPostScoreLog matchResultPostScoreLog =
                      MatchResultPostScoreLog.builder()
                          .name(updateMatchResultPostScoreLogRequestDTO.getName())
                          .score(updateMatchResultPostScoreLogRequestDTO.getScore())
                          .extra(updateMatchResultPostScoreLogRequestDTO.getExtra())
                          .build();

                  matchResultPostScoreLog.updateMatchResultPost(matchResultPost);
                  matchResultPostScoreLog.updateUserId(
                      updateMatchResultPostScoreLogRequestDTO.getUserId());

                  return matchResultPostScoreLog;
                })
            .collect(Collectors.toList());

    matchResultPost.updateHomeTeam(
        awayEntryTeam,
        updateMatchResultPostRequestDTO.getAwayScore(),
        updateMatchResultPostRequestDTO.getIsAwayWin(),
        awayMatchResultPostScoreLogs);

    matchResultPostRepository.save(matchResultPost);

    return UpdateMatchResultPostResponseDTO.builder()
        .message("경기 결과를 수정했습니다.")
        .data(MatchResultPostResponseDTO.from(matchResultPost))
        .build();
  }

  @Transactional
  public DeleteMatchResultPostResponseDTO deleteMatchResultPost(
      Long userId, Long competitionId, InGameCategory inGameCategory, Long matchResultPostId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateCompetitionOwner(competition, userId);

    MatchResultPost matchResultPost =
        MatchUtils.getMatchResultPostById(matchResultPostId, matchResultPostRepository);

    validateMatchResultPostWithParameter(matchResultPost, competitionId, inGameCategory);

    MatchResultPostResponseDTO matchResultPostResponseDTO =
        MatchResultPostResponseDTO.from(matchResultPost);

    matchResultPostScoreLogRepository.deleteAllByMatchResultPost_Id(matchResultPostId);

    matchResultPostRepository.deleteById(matchResultPostId);

    return DeleteMatchResultPostResponseDTO.builder()
        .message("경기 결과를 삭제했습니다.")
        .data(matchResultPostResponseDTO)
        .build();
  }

  @Transactional
  public void deleteAllMatchResult(Long competitionId, String groupCategory) {
    List<MatchResultPost> matchResultPosts = matchResultPostRepository.findAllByCompetition_IdAndGroupCategory(competitionId, groupCategory);

    matchResultPostRepository.deleteAllInBatch(matchResultPosts);
  }
}
