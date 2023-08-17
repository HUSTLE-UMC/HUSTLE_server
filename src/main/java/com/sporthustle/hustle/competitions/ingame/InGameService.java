package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.CompetitionUtils;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.ingame.dto.PreRoundGroupResponseDTO;
import com.sporthustle.hustle.competitions.ingame.dto.PreRoundGroupsResponseDTO;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundGroup;
import com.sporthustle.hustle.competitions.ingame.repository.FinalRoundTeamRepository;
import com.sporthustle.hustle.competitions.ingame.repository.PreRoundGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InGameService {

  private final PreRoundGroupRepository preRoundGroupRepository;
  private final FinalRoundTeamRepository finalRoundTeamRepository;
  private final CompetitionRepository competitionRepository;

  @Transactional(readOnly = true)
  public PreRoundGroupsResponseDTO getPreRoundGroups(Long userId, Long competitionId) {
    Competition competition = CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    validateUserIsCompetitionOwner(userId, competition);

    List<PreRoundGroup> preRoundGroups = preRoundGroupRepository.findAllByCompetition_Id(competitionId);

    List<PreRoundGroupResponseDTO> preRoundGroupDTOs = preRoundGroups.stream()
            .map(preRoundGroup -> PreRoundGroupResponseDTO.from(preRoundGroup))
            .collect(Collectors.toList());

    return PreRoundGroupsResponseDTO.builder()
            .preRoundGroups(preRoundGroupDTOs)
            .build();
  }

  private void validateUserIsCompetitionOwner(Long userId, Competition competition) {
    if (competition.getUser().getId() != userId) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }
}
