package com.sporthustle.hustle.competitions.ingame;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.competitions.competition.CompetitionUtils;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.CompetitionRepository;
import com.sporthustle.hustle.competitions.ingame.dto.DetailResponseDTO;
import com.sporthustle.hustle.competitions.ingame.dto.InGameType;
import com.sporthustle.hustle.competitions.ingame.dto.UpdateDetailRequestDTO;
import com.sporthustle.hustle.competitions.ingame.dto.UpdateDetailResponseDTO;
import com.sporthustle.hustle.competitions.ingame.entity.FinalRoundDetail;
import com.sporthustle.hustle.competitions.ingame.entity.PreRoundDetail;
import com.sporthustle.hustle.competitions.ingame.repository.FinalRoundDetailRepository;
import com.sporthustle.hustle.competitions.ingame.repository.PreRoundDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompetitionDetailService {

  private final PreRoundDetailRepository preRoundDetailRepository;
  private final FinalRoundDetailRepository finalRoundDetailRepository;
  private final CompetitionRepository competitionRepository;

  @Transactional
  public void createDetails(Long competitionId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    createPreRoundDetail(competition);
    createFinalRoundDetail(competition);
  }

  private void createPreRoundDetail(Competition competition) {
    PreRoundDetail preRoundDetail =
        PreRoundDetail.builder().timeTableUrl(CompetitionDetailUtils.DEFAULT_TIMETABLE_URL).build();
    preRoundDetail.updateCompetition(competition);
    preRoundDetailRepository.save(preRoundDetail);
  }

  private void createFinalRoundDetail(Competition competition) {
    FinalRoundDetail finalRoundDetail =
        FinalRoundDetail.builder()
            .timeTableUrl(CompetitionDetailUtils.DEFAULT_TIMETABLE_URL)
            .build();
    finalRoundDetail.updateCompetition(competition);
    finalRoundDetailRepository.save(finalRoundDetail);
  }

  @Transactional
  public void deleteDetails(Long competitionId) {
    deletePreRoundDetail(competitionId);
    deleteFinalRoundDetail(competitionId);
  }

  private void deletePreRoundDetail(Long competitionId) {
    PreRoundDetail preRoundDetail =
        preRoundDetailRepository
            .findByCompetition_Id(competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.PRE_ROUND_DETAIL_NOT_FOUND));

    preRoundDetailRepository.delete(preRoundDetail);
  }

  private void deleteFinalRoundDetail(Long competitionId) {
    FinalRoundDetail finalRoundDetail =
        finalRoundDetailRepository
            .findByCompetition_Id(competitionId)
            .orElseThrow(() -> BaseException.from(ErrorCode.PRE_ROUND_DETAIL_NOT_FOUND));

    finalRoundDetailRepository.delete(finalRoundDetail);
  }

  @Transactional(readOnly = true)
  public DetailResponseDTO getDetail(Long competitionId, InGameType type) {
    DetailResponseDTO detailResponseDTO = null;

    switch (type) {
      case PREROUND:
        PreRoundDetail preRoundDetail =
            CompetitionDetailUtils.getPreRoundDetail(competitionId, preRoundDetailRepository);
        detailResponseDTO = DetailResponseDTO.from(preRoundDetail);
        break;
      case FINALROUND:
        FinalRoundDetail finalRoundDetail =
            CompetitionDetailUtils.getFinalRoundDetail(competitionId, finalRoundDetailRepository);
        detailResponseDTO = DetailResponseDTO.from(finalRoundDetail);
        break;
      default:
        throw new IllegalArgumentException("InGameType 해당하는 값이 없습니다.");
    }

    return detailResponseDTO;
  }

  @Transactional
  public UpdateDetailResponseDTO updateDetail(
      Long userId,
      Long competitionId,
      InGameType type,
      UpdateDetailRequestDTO updateDetailRequestDTO) {
    UpdateDetailResponseDTO updateDetailResponseDTO = null;

    validateCompetitionOwner(userId, competitionId);

    switch (type) {
      case PREROUND:
        updatePreRoundDetail(competitionId, updateDetailRequestDTO);
        break;
      case FINALROUND:
        updateFinalRoundDetail(competitionId, updateDetailRequestDTO);
        break;
      default:
        throw new IllegalArgumentException("InGameType 해당하는 값이 없습니다.");
    }

    DetailResponseDTO detailResponseDTO =
        DetailResponseDTO.builder().timeTableUrl(updateDetailRequestDTO.getTimeTableUrl()).build();

    updateDetailResponseDTO =
        UpdateDetailResponseDTO.builder()
            .message("대회 세부 정보를 성공적으로 수정했습니다.")
            .data(detailResponseDTO)
            .build();

    return updateDetailResponseDTO;
  }

  private void validateCompetitionOwner(Long userId, Long competitionId) {
    Competition competition =
        CompetitionUtils.getCompetitionById(competitionId, competitionRepository);

    if (competition.getUser().getId() != userId) {
      throw BaseException.from(ErrorCode.USER_NOT_OWNER);
    }
  }

  public void updatePreRoundDetail(
      Long competitionId, UpdateDetailRequestDTO updateDetailRequestDTO) {
    PreRoundDetail preRoundDetail =
        CompetitionDetailUtils.getPreRoundDetail(competitionId, preRoundDetailRepository);
    preRoundDetail.updateTimeTableUrl(updateDetailRequestDTO.getTimeTableUrl());
    preRoundDetailRepository.save(preRoundDetail);
  }

  public void updateFinalRoundDetail(
      Long competitionId, UpdateDetailRequestDTO updateDetailRequestDTO) {
    FinalRoundDetail finalRoundDetail =
        CompetitionDetailUtils.getFinalRoundDetail(competitionId, finalRoundDetailRepository);
    finalRoundDetail.updateTimeTableUrl(updateDetailRequestDTO.getTimeTableUrl());
    finalRoundDetailRepository.save(finalRoundDetail);
  }
}
