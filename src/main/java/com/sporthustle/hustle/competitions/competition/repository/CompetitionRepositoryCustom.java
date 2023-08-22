package com.sporthustle.hustle.competitions.competition.repository;

import static com.sporthustle.hustle.competitions.competition.entity.QCompetition.competition;
import static com.sporthustle.hustle.sport.entity.QSportEvent.sportEvent;
import static com.sporthustle.hustle.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sporthustle.hustle.competitions.competition.entity.Competition;
import com.sporthustle.hustle.competitions.competition.repository.condition.CompetitionsBeforeCompleteCondition;
import com.sporthustle.hustle.competitions.competition.repository.condition.RecentCompleteCompetitionsCondition;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompetitionRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public Page<Competition> getCompetitionsBeforeComplete(
      CompetitionsBeforeCompleteCondition condition, Pageable pageable) {
    JPAQuery<Competition> countQuery =
        queryFactory
            .selectFrom(competition)
            .leftJoin(competition.sportEvent, sportEvent)
            .leftJoin(competition.user, user)
            .where(sportEventIdEq(condition.getSportEventId()), endDateGoe())
            .orderBy(competition.startDate.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());

    List<Competition> content = countQuery.fetch();

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  public Page<Competition> getRecentCompleteCompetitions(
      RecentCompleteCompetitionsCondition condition, Pageable pageable) {

    JPAQuery<Competition> countQuery =
        queryFactory
            .selectFrom(competition)
            .leftJoin(competition.sportEvent, sportEvent)
            .leftJoin(competition.user, user)
            .where(sportEventIdEq(condition.getSportEventId()), endDateBeforeNow())
            .orderBy(competition.startDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());

    List<Competition> content = countQuery.fetch();

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  private boolean hasSportEventId(Long sportEventId) {
    return sportEventId != null;
  }

  private BooleanExpression sportEventIdEq(Long sportEventId) {
    return hasSportEventId(sportEventId) ? competition.sportEvent.id.eq(sportEventId) : null;
  }

  private BooleanExpression endDateBeforeNow() {
    LocalDateTime now = LocalDateTime.now();
    return competition.endDate.before(now);
  }

  private BooleanExpression endDateGoe() {
    LocalDateTime now = LocalDateTime.now();
    return competition.endDate.goe(now);
  }

  public Page<Competition> getPopularCompetitions(Pageable pageable) {
    JPAQuery<Competition> countQuery =
        queryFactory
            .selectFrom(competition)
            .leftJoin(competition.sportEvent, sportEvent)
            .leftJoin(competition.user, user)
            .where(nowBetweenRecruitingDate(), notMaxEntryCount())
            .orderBy(competition.entryCount.desc(), competition.recruitmentEndDate.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());

    List<Competition> content = countQuery.fetch();

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  private BooleanExpression nowBetweenRecruitingDate() {
    LocalDateTime now = LocalDateTime.now();
    return competition.recruitmentStartDate.loe(now).and(competition.recruitmentEndDate.goe(now));
  }

  private BooleanExpression notMaxEntryCount() {
    return competition.entryCount.lt(competition.maxEntryCount);
  }
}
