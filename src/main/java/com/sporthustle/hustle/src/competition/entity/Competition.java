package com.sporthustle.hustle.src.competition.entity;


import com.sporthustle.hustle.common.entity.BaseEntity;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.university.entity.University;
import com.sporthustle.hustle.src.user.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Competition")
public class Competition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_id", nullable = false)
    private Long id;

    @Column(name = "title", length = 60, nullable = false)
    private String title;

    @Column(name = "host", length = 45, nullable = false)
    private String host;

    @Column(name = "place", length = 60, nullable = false)
    private String place;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "recruitment_start_date", nullable = false)
    private LocalDateTime recruitmentStartDate;

    @Column(name = "recruitment_end_date", nullable = false)
    private LocalDateTime recruitmentEndDate;

    @Column(name = "entry_fee", nullable = false)
    private Long entryFee;

    @Column(name = "max_entry_count", nullable = false)
    private int maxEntryCount;

    @Column(name = "sponsor", length = 60)
    private String sponsor;

    @Column(name = "poster_url", length = 255, nullable = false)
    private String posterUrl;

    @Column(name = "pre_round_group_count", nullable = false)
    private int preRoundGroupCount;

    @Column(name = "final_round_team_count", nullable = false)
    private int finalRoundTeamCount;

    @Column(name = "type", length = 10, nullable = false)
    private String type; // RECRUITING / COMPLETE / BEFORE

    @Column(name="state",length=20,nullable = false)
    private String state;//RECRUITING / COMPLETE / DRAFT

    @Column(name = "status", length = 10, nullable = false)
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sportEvent_id", nullable = false)
    private SportEvent sportEvent;



}
