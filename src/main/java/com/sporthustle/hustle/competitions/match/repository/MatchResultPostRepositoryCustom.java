package com.sporthustle.hustle.competitions.match.repository;

import static com.sporthustle.hustle.competitions.entryteam.entity.QEntryTeam.entryTeam;
import static com.sporthustle.hustle.competitions.match.entity.QMatchResultPost.matchResultPost;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPost;
import com.sporthustle.hustle.competitions.match.entity.MatchResultPostCategory;
import com.sporthustle.hustle.competitions.match.repository.condition.GetMatchResultPostsCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MatchResultPostRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public List<MatchResultPost> getMatchResultPosts(GetMatchResultPostsCondition condition) {
    List<MatchResultPost> query =
        queryFactory
            .selectFrom(matchResultPost)
            .leftJoin(matchResultPost.homeEntryTeam, entryTeam)
            .leftJoin(matchResultPost.awayEntryTeam, entryTeam)
            .where(
                matchResultPost.competition.id.eq(condition.getCompetitionId()),
                matchResultPost.category.eq(MatchResultPostCategory.from(condition.getCategory())),
                matchResultPost.groupCategory.eq(condition.getGroupCategory()))
            .orderBy(matchResultPost.postOrder.asc())
            .fetch();

    return query;
  }
}
