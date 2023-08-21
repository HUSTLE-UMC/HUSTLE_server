package com.sporthustle.hustle.competitions.entryteam.repository;

import static com.sporthustle.hustle.club.entity.QClub.club;
import static com.sporthustle.hustle.competitions.entryteam.entity.QEntryTeam.entryTeam;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sporthustle.hustle.competitions.entryteam.entity.EntryTeam;
import com.sporthustle.hustle.competitions.entryteam.repository.condition.EntryTeamCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntryTeamRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public List<EntryTeam> getEntryTeamWithClubNameStartsWith(EntryTeamCondition condition) {
    List<EntryTeam> query =
        queryFactory
            .selectFrom(entryTeam)
            .leftJoin(entryTeam.club, club)
            .where(
                entryTeam.competition.id.eq(condition.getCompetitionId()),
                entryTeam.club.name.startsWith(condition.getName()))
            .fetch();

    return query;
  }
}
