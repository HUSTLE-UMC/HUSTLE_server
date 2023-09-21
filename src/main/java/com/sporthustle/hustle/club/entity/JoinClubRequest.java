package com.sporthustle.hustle.club.entity;

import com.sporthustle.hustle.common.entity.BaseStatus;
import com.sporthustle.hustle.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "JoinClubRequest")
@Entity
@Setter
public class JoinClubRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'WAIT'")
    @Enumerated(EnumType.STRING)
    private JoinClubRequestType type = JoinClubRequestType.WAIT;

    @Column(nullable = false, length = 10, columnDefinition = "CHAR(10) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    protected BaseStatus status = BaseStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;


    @Builder
    JoinClubRequest(
            Club club,
            User user,
            JoinClubRequestType type){
        this.user = user;
        this.club = club;
        this.type = type;
    }


    public void updateType(JoinClubRequestType type) {
        this.type = type;
    }

}
