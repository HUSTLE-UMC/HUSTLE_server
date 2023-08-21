package com.sporthustle.hustle.competitions.match.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class UpdateMatchResultPostRequestDTO {

  private String title;

  private String groupCategory;

  private Integer postOrder;

  private LocalDateTime matchTime;

  private String mediaUrl;

  private Long homeEntryTeamId;

  private Integer homeScore;

  private Boolean isHomeWin;

  private List<UpdateMatchResultPostScoreLogRequestDTO> homeMatchResultPostScoreLogs;

  private Long awayEntryTeamId;

  private Integer awayScore;

  private Boolean isAwayWin;

  private List<UpdateMatchResultPostScoreLogRequestDTO> awayMatchResultPostScoreLogs;
}
