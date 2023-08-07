package com.sporthustle.hustle.src.competition.entity;


import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.competition.Repository.CompetitionRepository;
import com.sporthustle.hustle.src.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "EntryTeam")
public class EntryTeam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryTeam_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "score", nullable = false)
    private Long score;

    @Column(name = "match_count", nullable = false)
    private int matchCount;

    @Column(name = "win_count", nullable = false)
    private int winCount;

    @Column(name = "lose_count", nullable = false)
    private int loseCount;

    @Column(name = "draw_count", nullable = false)
    private int drawCount;

    @Column(name = "score_difference_count", nullable = false)
    private int scoreDifferenceCount;

}
