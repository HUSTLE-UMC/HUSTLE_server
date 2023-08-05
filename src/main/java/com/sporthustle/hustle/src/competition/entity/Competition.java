package com.sporthustle.hustle.src.competition.entity;


import com.sporthustle.hustle.src.university.entity.University;
import com.sporthustle.hustle.src.user.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Competition")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_id", nullable = false)
    private Long id;

    @Column(name = "title", length = 60)
    private String title;

    @Column(name = "host", length = 45)
    private String host;

    @Column(name = "poster_url", length = 255)
    private String posterUrl;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "recruitment_start_date")
    private Timestamp recruitmentStartDate;

    @Column(name = "recruitment_end_date")
    private Timestamp recruitmentEndDate;

    @Column(name = "entry_fee")
    private int entryFee; //unsigned

    @Column(name = "max_entry_count")
    private short maxEntryCount;

    @Column(name = "pre_round_group_count")
    private int preRoundGroupCount; //@추가

    @Column(name = "final_round_group_count")
    private int finalRoundTeamCount; //@추가

    @Column(name = "sponsor", length = 60)
    private String sponsor;

    @Column(name = "place", length = 60)
    private String place;

    @Column(name = "event", length = 20)
    private String event;  //@추가

    @Column(name = "status", length = 10)
    private String status; //모집완료/모집중

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
