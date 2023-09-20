package com.sporthustle.hustle.club.entity;


import com.sporthustle.hustle.user.entity.User;

import javax.persistence.*;

public class ClubJoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'WAIT'")
    @Enumerated(EnumType.STRING)
    private ClubJoinRequestType type = ClubJoinRequestType.WAIT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

}
