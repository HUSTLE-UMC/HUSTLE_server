package com.sporthustle.hustle.competitions.match.dto;

import com.sporthustle.hustle.competitions.entryteam.dto.EntryTeamResponseDTO;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPost;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPostCategory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchResultPostResponseDTO {

  private Long id;

  private String title;

  private MatchResultPostCategory category;

  private String groupCategory;

  private Integer postOrder;

  private LocalDateTime matchTime;

  private String mediaUrl;

  private EntryTeamResponseDTO homeEntryTeam;

  private Integer homeScore;

  private boolean isHomeWin;

  private List<MatchResultPostScoreLogResponseDTO> homeMatchResultPostScoreLogs;

  private EntryTeamResponseDTO awayEntryTeam;

  private Integer awayScore;

  private boolean isAwayWin;

  private List<MatchResultPostScoreLogResponseDTO> awayMatchResultPostScoreLogs;

  public static MatchResultPostResponseDTO from(MatchResultPost matchResultPost) {
    return MatchResultPostResponseDTO.builder()
        .id(matchResultPost.getId())
        .title(matchResultPost.getTitle())
        .category(matchResultPost.getCategory())
        .groupCategory(matchResultPost.getGroupCategory())
        .postOrder(matchResultPost.getPostOrder())
        .matchTime(matchResultPost.getMatchTime())
        .mediaUrl(matchResultPost.getMediaUrl())
        .homeEntryTeam(EntryTeamResponseDTO.from(matchResultPost.getHomeEntryTeam()))
        .homeScore(matchResultPost.getHomeScore())
        .isHomeWin(matchResultPost.isHomeWin())
        .awayEntryTeam(EntryTeamResponseDTO.from(matchResultPost.getAwayEntryTeam()))
        .awayScore(matchResultPost.getAwayScore())
        .isAwayWin(matchResultPost.isAwayWin())
        .build();
  }

  public static MatchResultPostResponseDTO fromWithScoreLogs(MatchResultPost matchResultPost) {
    return MatchResultPostResponseDTO.builder()
        .id(matchResultPost.getId())
        .title(matchResultPost.getTitle())
        .category(matchResultPost.getCategory())
        .groupCategory(matchResultPost.getGroupCategory())
        .postOrder(matchResultPost.getPostOrder())
        .matchTime(matchResultPost.getMatchTime())
        .mediaUrl(matchResultPost.getMediaUrl())
        .homeEntryTeam(EntryTeamResponseDTO.from(matchResultPost.getHomeEntryTeam()))
        .homeScore(matchResultPost.getHomeScore())
        .isHomeWin(matchResultPost.isHomeWin())
        .awayEntryTeam(EntryTeamResponseDTO.from(matchResultPost.getAwayEntryTeam()))
        .awayScore(matchResultPost.getAwayScore())
        .isAwayWin(matchResultPost.isAwayWin())
        .build();
  }
}
