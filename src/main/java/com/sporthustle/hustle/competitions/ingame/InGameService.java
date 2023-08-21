package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.competitions.competition.CompetitionUtils;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.entryteam.EntryTeamUtils;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.competitions.entryteam.repository.EntryTeamRepository;
import com.sporthustle.hustle.competitions.ingame.dto.*;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import com.sporthustle.hustle.competitions.ingame.repository.PreRoundGroupRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InGameService {

  private final PreRoundGroupRepository preRoundGroupRepository;
  private final CompetitionRepository competitionRepository;
  private final EntryTeamRepository entryTeamRepository;

  @Transactional(readOnly = true)
  public PreRoundGroupsResponseDTO getPreRoundGroups(Long competitionId) {
    List<PreRoundGroup> preRoundGroups =
        preRoundGroupRepository.findAllByCompetition_Id(competitionId);

    List<PreRoundGroupResponseDTO> preRoundGroupResponseDTOs =
        preRoundGroups.stream().map(PreRoundGroupResponseDTO::from).collect(Collectors.toList());

    Map<String, List<PreRoundGroupResponseDTO>> preRoundGroupMappedByGroupCategory =
        preRoundGroupResponseDTOs.stream()
            .collect(Collectors.groupingBy(PreRoundGroupResponseDTO::getGroupCategory));

    return PreRoundGroupsResponseDTO.builder()
        .preRoundGroups(preRoundGroupMappedByGroupCategory)
        .build();
  }

  @Transactional
  public void createPreRoundGroup(Long competitionId, Long entryTeamId, String groupCategory) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);
    EntryTeam entryTeam =
        EntryTeamUtils.getEntryTeamByIdAndCompetitionId(
            entryTeamId, competitionId, entryTeamRepository);

    PreRoundGroup preRoundGroup = PreRoundGroup.builder().groupCategory(groupCategory).build();

    preRoundGroup.updateEntryTeam(entryTeam);
    preRoundGroup.updateCompetition(competition);

    preRoundGroupRepository.save(preRoundGroup);
  }

  public CategoriesResponseDTO getCategories(Long competitionId, InGameType inGameType) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    List<String> categories = null;

    switch (inGameType) {
      case PREROUND:
        categories = getPreRoundCategories(competition);
        break;
      case FINALROUND:
        categories = getFinalRoundCategories(competition);
        break;
      default:
        throw new IllegalArgumentException("InGameType 해당되는 값이 없습니다.");
    }

    return CategoriesResponseDTO.builder().categories(categories).build();
  }

  private List<String> getPreRoundCategories(Competition competition) {
    List<String> preRoundCategories = new ArrayList<>();

    final char FIRST_GROUP_NAME = 'A';
    final String SUFFIX_GROUP_NAME = "조";
    for (int i = 0; i < competition.getPreRoundGroupCount(); i++) {

      String category = "";

      int nowCount = i;
      final int NUM_OF_ALPHABET = 'Z' - 'A' + 1;
      while (nowCount + 1 > NUM_OF_ALPHABET) {
        category += FIRST_GROUP_NAME;
        nowCount -= NUM_OF_ALPHABET;
      }

      char nextGroupName = (char) ('A' + nowCount);
      category += nextGroupName + SUFFIX_GROUP_NAME;
      preRoundCategories.add(category);
    }
    return preRoundCategories;
  }

  private List<String> getFinalRoundCategories(Competition competition) {
    List<String> finalRoundCategories = new ArrayList<>();

    int finalRoundTeamCount = competition.getFinalRoundTeamCount();

    final int SEMIFINAL_TEAM_COUNT = 4;
    final String SUFFIX_EQUAL_AND_UNDER_SEMIFINAL = "강";
    while (finalRoundTeamCount >= SEMIFINAL_TEAM_COUNT) {
      String category = finalRoundTeamCount + SUFFIX_EQUAL_AND_UNDER_SEMIFINAL;
      finalRoundCategories.add(category);
      finalRoundTeamCount = finalRoundTeamCount / 2;
    }

    final String STRING_PLACE_MATCH_3RD_4TH = "3,4위전";
    final String STRING_FINAL_ROUND = "결승전";
    finalRoundCategories.add(STRING_PLACE_MATCH_3RD_4TH);
    finalRoundCategories.add(STRING_FINAL_ROUND);

    return finalRoundCategories;
  }

  public PreRoundMatchCountResponseDTO getPreRoundMatchCount(Long competitionId) {
    Competition competition =
            CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    int entryTeamCountByPreRoundGroup = competition.getMaxEntryCount() / competition.getPreRoundGroupCount();

    int preRoundMatchCount = entryTeamCountByPreRoundGroup * (entryTeamCountByPreRoundGroup - 1) / 2;

    return PreRoundMatchCountResponseDTO.builder()
            .message("예선 경기 수를 조회했습니다.")
            .data(preRoundMatchCount)
            .build();
  }
}
