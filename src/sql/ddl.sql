-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- University Table Create SQL
-- 테이블 생성 SQL - University
CREATE TABLE University
(
    `id`          BIGINT         NOT NULL    AUTO_INCREMENT, 
    `name`        VARCHAR(45)    NOT NULL    COMMENT '대학 이름', 
    `address`     VARCHAR(60)    NOT NULL    COMMENT '대학 상세 주소', 
    `created_at`  TIMESTAMP      NOT NULL    DEFAULT NOW(), 
    `updated_at`  TIMESTAMP      NOT NULL    DEFAULT NOW(), 
     PRIMARY KEY (id)
);


-- User Table Create SQL
-- 테이블 생성 SQL - User
CREATE TABLE User
(
    `id`             BIGINT          NOT NULL    AUTO_INCREMENT, 
    `university_id`  BIGINT          NOT NULL, 
    `email`          VARCHAR(255)    NOT NULL    COMMENT '이메일 / 소셜로그인의 경우 소셜로그인 고유 ID 와 합침', 
    `password`       VARCHAR(128)    NOT NULL    COMMENT '비밀번호 / 소셜 로그인의 경우 이메일 기준으로 암호화', 
    `name`           VARCHAR(20)     NOT NULL    COMMENT '이름', 
    `sns_type`       VARCHAR(20)     NOT NULL    DEFAULT 'DEFAULT' COMMENT '소셜로그인 종류', 
    `sns_id`         VARCHAR(60)     NOT NULL    DEFAULT '' COMMENT '소셜로그인 고유 ID 키', 
    `birthday`       DATE            NOT NULL    COMMENT '생년월일', 
    `gender`         CHAR(6)         NOT NULL    DEFAULT 'MALE' COMMENT 'MALE / FEMALE', 
    `status`         CHAR(10)        NOT NULL    DEFAULT 'ACTIVE', 
    `is_mailing`     TINYINT         NOT NULL    DEFAULT 0 COMMENT '메일링 여부', 
    `role`           CHAR(10)        NOT NULL    DEFAULT 'USER' COMMENT 'USER / ADMIN', 
    `refresh_token`  VARCHAR(255)    NOT NULL    DEFAULT '' COMMENT '리프레쉬 토큰 저장',
    `created_at`     TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`     TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - User(university_id) -> University(id)
ALTER TABLE User
    ADD CONSTRAINT FK_User_university_id_University_id FOREIGN KEY (university_id)
        REFERENCES University (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - User(university_id)
-- ALTER TABLE User
-- DROP FOREIGN KEY FK_User_university_id_University_id;


-- SportEvent Table Create SQL
-- 테이블 생성 SQL - SportEvent
CREATE TABLE SportEvent
(
    `id`          BIGINT         NOT NULL    AUTO_INCREMENT,
    `name`        VARCHAR(10)    NOT NULL    COMMENT '종목 명 ex. 축구',
    `created_at`  TIMESTAMP      NOT NULL    DEFAULT NOW(),
    `updated_at`  TIMESTAMP      NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);


-- Competition Table Create SQL
-- 테이블 생성 SQL - Competition
CREATE TABLE Competition
(
    `id`                      BIGINT               NOT NULL    AUTO_INCREMENT,
    `user_id`                 BIGINT               NOT NULL    COMMENT '작성자',
    `sport_event_id`          BIGINT               NOT NULL    COMMENT '종목',
    `title`                   VARCHAR(60)          NOT NULL    COMMENT '대회 제목',
    `host`                    VARCHAR(45)          NOT NULL    COMMENT '주최사',
    `place`                   VARCHAR(60)          NOT NULL    COMMENT '장소 (수기)',
    `start_date`              TIMESTAMP            NOT NULL    COMMENT '대회 시작일',
    `end_date`                TIMESTAMP            NOT NULL    COMMENT '대회 종료일',
    `recruitment_start_date`  TIMESTAMP            NOT NULL    COMMENT '모집 시작일',
    `recruitment_end_date`    TIMESTAMP            NOT NULL    COMMENT '모집 종료일',
    `entry_fee`               INT UNSIGNED         NOT NULL    DEFAULT 0 COMMENT '참가비',
    `entry_count`             SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '현재 참가 팀 수',
    `max_entry_count`         SMALLINT UNSIGNED    NOT NULL    DEFAULT 1 COMMENT '최대 팀 수',
    `sponsor`                 VARCHAR(60)          NOT NULL    COMMENT '후원사',
    `poster_url`              VARCHAR(255)         NOT NULL    COMMENT '포스터 이미지 URL',
    `pre_round_group_count`   SMALLINT UNSIGNED    NOT NULL    DEFAULT 1 COMMENT '예선 조 수',
    `final_round_team_count`  SMALLINT UNSIGNED    NOT NULL    DEFAULT 1 COMMENT '본선 진출 팀 수',
    `status`                  CHAR(10)             NOT NULL    DEFAULT 'ACTIVE',
    `created_at`              TIMESTAMP            NOT NULL    DEFAULT NOW(),
    `updated_at`              TIMESTAMP            NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - Competition(user_id) -> User(id)
ALTER TABLE Competition
    ADD CONSTRAINT FK_Competition_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Competition(user_id)
-- ALTER TABLE Competition
-- DROP FOREIGN KEY FK_Competition_user_id_User_id;

-- Foreign Key 설정 SQL - Competition(sport_event_id) -> SportEvent(id)
ALTER TABLE Competition
    ADD CONSTRAINT FK_Competition_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Competition(sport_event_id)
-- ALTER TABLE Competition
-- DROP FOREIGN KEY FK_Competition_sport_event_id_SportEvent_id;


-- Club Table Create SQL
-- 테이블 생성 SQL - Club
CREATE TABLE Club
(
    `id`                 BIGINT          NOT NULL    AUTO_INCREMENT,
    `university_id`      BIGINT          NOT NULL    COMMENT '대학',
    `sport_event_id`     BIGINT          NOT NULL    COMMENT '종목',
    `name`               VARCHAR(60)     NOT NULL    COMMENT '동아리명',
    `instagram`          VARCHAR(40)     NULL        COMMENT '인스타그램 주소',
    `youtube_url`        VARCHAR(255)    NULL        COMMENT '유투브 주소',
    `main_area`          VARCHAR(45)     NOT NULL,
    `profile_image_url`  VARCHAR(255)    NULL,
    `point`              INT UNSIGNED    NOT NULL    DEFAULT 0,
    `status`             CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`         TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`         TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - Club(university_id) -> University(id)
ALTER TABLE Club
    ADD CONSTRAINT FK_Club_university_id_University_id FOREIGN KEY (university_id)
        REFERENCES University (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Club(university_id)
-- ALTER TABLE Club
-- DROP FOREIGN KEY FK_Club_university_id_University_id;

-- Foreign Key 설정 SQL - Club(sport_event_id) -> SportEvent(id)
ALTER TABLE Club
    ADD CONSTRAINT FK_Club_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Club(sport_event_id)
-- ALTER TABLE Club
-- DROP FOREIGN KEY FK_Club_sport_event_id_SportEvent_id;


-- MatchResultPost Table Create SQL
-- 테이블 생성 SQL - MatchResultPost
CREATE TABLE MatchResultPost
(
    `id`              BIGINT               NOT NULL    AUTO_INCREMENT,
    `competition_id`  BIGINT               NULL,
    `title`           VARCHAR(60)          NOT NULL    COMMENT '경기 제목',
    `category`        CHAR(10)             NOT NULL    DEFAULT 'PREROUND' COMMENT 'PREROUND(예선) / FINAL(본선) 종류',
    `group_category`  CHAR(20)             NOT NULL    COMMENT '예선 조 (A조) / 본선 조 (32강/16강/8강/4강/34위전/결승전)',
    `order`           SMALLINT UNSIGNED    NOT NULL    DEFAULT 1 COMMENT '경기 수',
    `media_url`       VARCHAR(255)         NULL,
    `created_at`      TIMESTAMP            NULL        DEFAULT NOW(),
    `updated_at`      TIMESTAMP            NULL        DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - MatchResultPost(competition_id) -> Competition(id)
ALTER TABLE MatchResultPost
    ADD CONSTRAINT FK_MatchResultPost_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MatchResultPost(competition_id)
-- ALTER TABLE MatchResultPost
-- DROP FOREIGN KEY FK_MatchResultPost_competition_id_Competition_id;


-- ClubPost Table Create SQL
-- 테이블 생성 SQL - ClubPost
CREATE TABLE ClubPost
(
    `id`          BIGINT          NOT NULL    AUTO_INCREMENT,
    `user_id`     BIGINT          NOT NULL    COMMENT '작성자',
    `club_id`     BIGINT          NOT NULL    COMMENT '동아리',
    `title`       VARCHAR(100)    NOT NULL,
    `content`     TEXT            NOT NULL,
    `status`      CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`  TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`  TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ClubPost(user_id) -> User(id)
ALTER TABLE ClubPost
    ADD CONSTRAINT FK_ClubPost_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPost(user_id)
-- ALTER TABLE ClubPost
-- DROP FOREIGN KEY FK_ClubPost_user_id_User_id;

-- Foreign Key 설정 SQL - ClubPost(club_id) -> Club(id)
ALTER TABLE ClubPost
    ADD CONSTRAINT FK_ClubPost_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPost(club_id)
-- ALTER TABLE ClubPost
-- DROP FOREIGN KEY FK_ClubPost_club_id_Club_id;


-- SportPosition Table Create SQL
-- 테이블 생성 SQL - SportPosition
CREATE TABLE SportPosition
(
    `id`              BIGINT         NOT NULL    AUTO_INCREMENT,
    `sport_event_id`  BIGINT         NOT NULL,
    `name`            VARCHAR(20)    NOT NULL    COMMENT '포지션명 ex. SG',
    `created_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - SportPosition(sport_event_id) -> SportEvent(id)
ALTER TABLE SportPosition
    ADD CONSTRAINT FK_SportPosition_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - SportPosition(sport_event_id)
-- ALTER TABLE SportPosition
-- DROP FOREIGN KEY FK_SportPosition_sport_event_id_SportEvent_id;


-- QuestionPost Table Create SQL
-- 테이블 생성 SQL - QuestionPost
CREATE TABLE QuestionPost
(
    `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
    `user_id`         BIGINT          NOT NULL    COMMENT '작성자',
    `sport_event_id`  BIGINT          NOT NULL    COMMENT '종목',
    `title`           VARCHAR(100)    NOT NULL    COMMENT '제목',
    `content`         TEXT            NOT NULL    COMMENT '내용',
    `status`          CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - QuestionPost(user_id) -> User(id)
ALTER TABLE QuestionPost
    ADD CONSTRAINT FK_QuestionPost_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPost(user_id)
-- ALTER TABLE QuestionPost
-- DROP FOREIGN KEY FK_QuestionPost_user_id_User_id;

-- Foreign Key 설정 SQL - QuestionPost(sport_event_id) -> SportEvent(id)
ALTER TABLE QuestionPost
    ADD CONSTRAINT FK_QuestionPost_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPost(sport_event_id)
-- ALTER TABLE QuestionPost
-- DROP FOREIGN KEY FK_QuestionPost_sport_event_id_SportEvent_id;


-- MatchResultPostClub Table Create SQL
-- 테이블 생성 SQL - MatchResultPostClub
CREATE TABLE MatchResultPostClub
(
    `id`                    BIGINT          NOT NULL    AUTO_INCREMENT,
    `match_result_post_id`  BIGINT          NOT NULL,
    `club_id`               BIGINT          NOT NULL,
    `score`                 INT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '득점 스코어',
    `is_win`                TINYINT         NOT NULL    DEFAULT 0,
    `created_at`            TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`            TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - MatchResultPostClub(match_result_post_id) -> MatchResultPost(id)
ALTER TABLE MatchResultPostClub
    ADD CONSTRAINT FK_MatchResultPostClub_match_result_post_id_MatchResultPost_id FOREIGN KEY (match_result_post_id)
        REFERENCES MatchResultPost (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MatchResultPostClub(match_result_post_id)
-- ALTER TABLE MatchResultPostClub
-- DROP FOREIGN KEY FK_MatchResultPostClub_match_result_post_id_MatchResultPost_id;

-- Foreign Key 설정 SQL - MatchResultPostClub(club_id) -> Club(id)
ALTER TABLE MatchResultPostClub
    ADD CONSTRAINT FK_MatchResultPostClub_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MatchResultPostClub(club_id)
-- ALTER TABLE MatchResultPostClub
-- DROP FOREIGN KEY FK_MatchResultPostClub_club_id_Club_id;


-- FriendMatchingPost Table Create SQL
-- 테이블 생성 SQL - FriendMatchingPost
CREATE TABLE FriendMatchingPost
(
    `id`                BIGINT          NOT NULL    AUTO_INCREMENT,
    `user_id`           BIGINT          NOT NULL    COMMENT '작성자',
    `club_id`           BIGINT          NOT NULL,
    `sport_event_id`    BIGINT          NOT NULL,
    `title`             VARCHAR(100)    NOT NULL,
    `category`          CHAR(20)        NOT NULL    DEFAULT 'INVITE' COMMENT 'INVITE / REQUEST',
    `name`              VARCHAR(20)     NOT NULL,
    `phone_number`      VARCHAR(20)     NOT NULL,
    `start_date`        TIMESTAMP       NOT NULL    COMMENT '경기 일시',
    `location`          POINT           NOT NULL    COMMENT '위치 정보',
    `location_address`  VARCHAR(60)     NOT NULL    DEFAULT '' COMMENT '상세 정보 주소',
    `status`            CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`        TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`        TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - FriendMatchingPost(club_id) -> Club(id)
ALTER TABLE FriendMatchingPost
    ADD CONSTRAINT FK_FriendMatchingPost_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingPost(club_id)
-- ALTER TABLE FriendMatchingPost
-- DROP FOREIGN KEY FK_FriendMatchingPost_club_id_Club_id;

-- Foreign Key 설정 SQL - FriendMatchingPost(user_id) -> User(id)
ALTER TABLE FriendMatchingPost
    ADD CONSTRAINT FK_FriendMatchingPost_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingPost(user_id)
-- ALTER TABLE FriendMatchingPost
-- DROP FOREIGN KEY FK_FriendMatchingPost_user_id_User_id;

-- Foreign Key 설정 SQL - FriendMatchingPost(sport_event_id) -> SportEvent(id)
ALTER TABLE FriendMatchingPost
    ADD CONSTRAINT FK_FriendMatchingPost_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingPost(sport_event_id)
-- ALTER TABLE FriendMatchingPost
-- DROP FOREIGN KEY FK_FriendMatchingPost_sport_event_id_SportEvent_id;


-- EntryTeam Table Create SQL
-- 테이블 생성 SQL - EntryTeam
CREATE TABLE EntryTeam
(
    `id`                      BIGINT               NOT NULL    AUTO_INCREMENT,
    `competition_id`          BIGINT               NOT NULL    COMMENT '대회',
    `club_id`                 BIGINT               NOT NULL    COMMENT '동아리',
    `user_id`                 BIGINT               NOT NULL    COMMENT '신청자',
    `name`                    VARCHAR(20)          NOT NULL    COMMENT '대표자 이름',
    `phone_number`            VARCHAR(20)          NOT NULL    COMMENT '대표자 번호',
    `score`                   INT UNSIGNED         NOT NULL    DEFAULT 0 COMMENT '최종 점수',
    `match_count`             SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '경기 수',
    `win_count`               SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '승리 수',
    `lose_count`              SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '패배 수',
    `draw_count`              SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '무승부 수',
    `score_difference_count`  SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '득실차',
    `created_at`              TIMESTAMP            NOT NULL    DEFAULT NOW(),
    `updated_at`              TIMESTAMP            NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - EntryTeam(club_id) -> Club(id)
ALTER TABLE EntryTeam
    ADD CONSTRAINT FK_EntryTeam_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - EntryTeam(club_id)
-- ALTER TABLE EntryTeam
-- DROP FOREIGN KEY FK_EntryTeam_club_id_Club_id;

-- Foreign Key 설정 SQL - EntryTeam(competition_id) -> Competition(id)
ALTER TABLE EntryTeam
    ADD CONSTRAINT FK_EntryTeam_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - EntryTeam(competition_id)
-- ALTER TABLE EntryTeam
-- DROP FOREIGN KEY FK_EntryTeam_competition_id_Competition_id;

-- Foreign Key 설정 SQL - EntryTeam(user_id) -> User(id)
ALTER TABLE EntryTeam
    ADD CONSTRAINT FK_EntryTeam_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - EntryTeam(user_id)
-- ALTER TABLE EntryTeam
-- DROP FOREIGN KEY FK_EntryTeam_user_id_User_id;


-- QuestionPostComment Table Create SQL
-- 테이블 생성 SQL - QuestionPostComment
CREATE TABLE QuestionPostComment
(
    `id`                BIGINT          NOT NULL    AUTO_INCREMENT,
    `question_post_id`  BIGINT          NOT NULL    COMMENT '게시글',
    `user_id`           BIGINT          NOT NULL    COMMENT '작성자',
    `content`           TEXT            NOT NULL    COMMENT '내용',
    `like_count`        INT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '총 좋아요 수',
    `status`            CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`        TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`        TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - QuestionPostComment(question_post_id) -> QuestionPost(id)
ALTER TABLE QuestionPostComment
    ADD CONSTRAINT FK_QuestionPostComment_question_post_id_QuestionPost_id FOREIGN KEY (question_post_id)
        REFERENCES QuestionPost (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPostComment(question_post_id)
-- ALTER TABLE QuestionPostComment
-- DROP FOREIGN KEY FK_QuestionPostComment_question_post_id_QuestionPost_id;

-- Foreign Key 설정 SQL - QuestionPostComment(user_id) -> User(id)
ALTER TABLE QuestionPostComment
    ADD CONSTRAINT FK_QuestionPostComment_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPostComment(user_id)
-- ALTER TABLE QuestionPostComment
-- DROP FOREIGN KEY FK_QuestionPostComment_user_id_User_id;


-- ClubPostComment Table Create SQL
-- 테이블 생성 SQL - ClubPostComment
CREATE TABLE ClubPostComment
(
    `id`            BIGINT          NOT NULL    AUTO_INCREMENT,
    `user_id`       BIGINT          NOT NULL    COMMENT '작성자',
    `club_post_id`  BIGINT          NOT NULL    COMMENT '게시글',
    `content`       TEXT            NOT NULL,
    `like_count`    INT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '좋아요 수',
    `status`        CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`    TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`    TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ClubPostComment(club_post_id) -> ClubPost(id)
ALTER TABLE ClubPostComment
    ADD CONSTRAINT FK_ClubPostComment_club_post_id_ClubPost_id FOREIGN KEY (club_post_id)
        REFERENCES ClubPost (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPostComment(club_post_id)
-- ALTER TABLE ClubPostComment
-- DROP FOREIGN KEY FK_ClubPostComment_club_post_id_ClubPost_id;

-- Foreign Key 설정 SQL - ClubPostComment(user_id) -> User(id)
ALTER TABLE ClubPostComment
    ADD CONSTRAINT FK_ClubPostComment_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPostComment(user_id)
-- ALTER TABLE ClubPostComment
-- DROP FOREIGN KEY FK_ClubPostComment_user_id_User_id;


-- ClubMember Table Create SQL
-- 테이블 생성 SQL - ClubMember
CREATE TABLE ClubMember
(
    `id`              BIGINT               NOT NULL    AUTO_INCREMENT,
    `user_id`         BIGINT               NOT NULL,
    `club_id`         BIGINT               NOT NULL,
    `position_id`     BIGINT               NOT NULL,
    `uniform_number`  SMALLINT UNSIGNED    NULL        DEFAULT 1 COMMENT '등번호',
    `role`            CHAR(16)             NOT NULL    DEFAULT 'MEMBER' COMMENT '회장 / 부회장 / 일반회원 / 매니저',
    `point`           INT UNSIGNED         NOT NULL    DEFAULT 0,
    `status`          CHAR(10)             NOT NULL    DEFAULT 'ACTIVE',
    `created_at`      TIMESTAMP            NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP            NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ClubMember(user_id) -> User(id)
ALTER TABLE ClubMember
    ADD CONSTRAINT FK_ClubMember_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubMember(user_id)
-- ALTER TABLE ClubMember
-- DROP FOREIGN KEY FK_ClubMember_user_id_User_id;

-- Foreign Key 설정 SQL - ClubMember(club_id) -> Club(id)
ALTER TABLE ClubMember
    ADD CONSTRAINT FK_ClubMember_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubMember(club_id)
-- ALTER TABLE ClubMember
-- DROP FOREIGN KEY FK_ClubMember_club_id_Club_id;

-- Foreign Key 설정 SQL - ClubMember(position_id) -> SportPosition(id)
ALTER TABLE ClubMember
    ADD CONSTRAINT FK_ClubMember_position_id_SportPosition_id FOREIGN KEY (position_id)
        REFERENCES SportPosition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubMember(position_id)
-- ALTER TABLE ClubMember
-- DROP FOREIGN KEY FK_ClubMember_position_id_SportPosition_id;


-- LecturePost Table Create SQL
-- 테이블 생성 SQL - LecturePost
CREATE TABLE LecturePost
(
    `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
    `user_id`         BIGINT          NOT NULL,
    `sport_event_id`  BIGINT          NOT NULL,
    `title`           VARCHAR(100)    NOT NULL    COMMENT '제목',
    `content`         TEXT            NOT NULL    COMMENT '내용',
    `thumbnail_url`   VARCHAR(255)    NOT NULL    COMMENT '섬네일 주소',
    `media_url`       VARCHAR(255)    NOT NULL    COMMENT '미디어 주소',
    `status`          CHAR(10)        NOT NULL    DEFAULT 'ACTIVE',
    `created_at`      TIMESTAMP       NOT NULL,
    `updated_at`      TIMESTAMP       NOT NULL,
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - LecturePost(user_id) -> User(id)
ALTER TABLE LecturePost
    ADD CONSTRAINT FK_LecturePost_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - LecturePost(user_id)
-- ALTER TABLE LecturePost
-- DROP FOREIGN KEY FK_LecturePost_user_id_User_id;

-- Foreign Key 설정 SQL - LecturePost(sport_event_id) -> SportEvent(id)
ALTER TABLE LecturePost
    ADD CONSTRAINT FK_LecturePost_sport_event_id_SportEvent_id FOREIGN KEY (sport_event_id)
        REFERENCES SportEvent (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - LecturePost(sport_event_id)
-- ALTER TABLE LecturePost
-- DROP FOREIGN KEY FK_LecturePost_sport_event_id_SportEvent_id;


-- QuestionPostCommentLike Table Create SQL
-- 테이블 생성 SQL - QuestionPostCommentLike
CREATE TABLE QuestionPostCommentLike
(
    `id`                        BIGINT       NOT NULL    AUTO_INCREMENT,
    `question_post_comment_id`  BIGINT       NOT NULL,
    `user_id`                   BIGINT       NOT NULL,
    `created_at`                TIMESTAMP    NOT NULL,
    `updated_at`                TIMESTAMP    NOT NULL,
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - QuestionPostCommentLike(user_id) -> User(id)
ALTER TABLE QuestionPostCommentLike
    ADD CONSTRAINT FK_QuestionPostCommentLike_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPostCommentLike(user_id)
-- ALTER TABLE QuestionPostCommentLike
-- DROP FOREIGN KEY FK_QuestionPostCommentLike_user_id_User_id;

-- Foreign Key 설정 SQL - QuestionPostCommentLike(question_post_comment_id) -> QuestionPostComment(id)
ALTER TABLE QuestionPostCommentLike
    ADD CONSTRAINT FK_QuestionPostCommentLike_question_post_comment_id_QuestionPost FOREIGN KEY (question_post_comment_id)
        REFERENCES QuestionPostComment (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - QuestionPostCommentLike(question_post_comment_id)
-- ALTER TABLE QuestionPostCommentLike
-- DROP FOREIGN KEY FK_QuestionPostCommentLike_question_post_comment_id_QuestionPost;


-- ClubPostCommentLike Table Create SQL
-- 테이블 생성 SQL - ClubPostCommentLike
CREATE TABLE ClubPostCommentLike
(
    `id`                    BIGINT       NOT NULL    AUTO_INCREMENT,
    `user_id`               BIGINT       NOT NULL    COMMENT '작성자',
    `club_post_comment_id`  BIGINT       NOT NULL,
    `created_at`            TIMESTAMP    NOT NULL    DEFAULT NOW(),
    `updated_at`            TIMESTAMP    NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ClubPostCommentLike(user_id) -> User(id)
ALTER TABLE ClubPostCommentLike
    ADD CONSTRAINT FK_ClubPostCommentLike_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPostCommentLike(user_id)
-- ALTER TABLE ClubPostCommentLike
-- DROP FOREIGN KEY FK_ClubPostCommentLike_user_id_User_id;

-- Foreign Key 설정 SQL - ClubPostCommentLike(club_post_comment_id) -> ClubPostComment(id)
ALTER TABLE ClubPostCommentLike
    ADD CONSTRAINT FK_ClubPostCommentLike_club_post_comment_id_ClubPostComment_id FOREIGN KEY (club_post_comment_id)
        REFERENCES ClubPostComment (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubPostCommentLike(club_post_comment_id)
-- ALTER TABLE ClubPostCommentLike
-- DROP FOREIGN KEY FK_ClubPostCommentLike_club_post_comment_id_ClubPostComment_id;


-- CompetitionContact Table Create SQL
-- 테이블 생성 SQL - CompetitionContact
CREATE TABLE CompetitionContact
(
    `id`              BIGINT         NOT NULL    AUTO_INCREMENT,
    `competition_id`  BIGINT         NOT NULL,
    `name`            VARCHAR(20)    NOT NULL    DEFAULT '' COMMENT '이름',
    `phone_number`    VARCHAR(20)    NOT NULL    COMMENT '전화번호',
    `created_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - CompetitionContact(competition_id) -> Competition(id)
ALTER TABLE CompetitionContact
    ADD CONSTRAINT FK_CompetitionContact_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE CASCADE ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CompetitionContact(competition_id)
-- ALTER TABLE CompetitionContact
-- DROP FOREIGN KEY FK_CompetitionContact_competition_id_Competition_id;


-- ClubMemberPointLog Table Create SQL
-- 테이블 생성 SQL - ClubMemberPointLog
CREATE TABLE ClubMemberPointLog
(
    `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
    `club_member_id`  BIGINT          NOT NULL,
    `point`           INT UNSIGNED    NOT NULL    DEFAULT 0,
    `plus`            TINYINT         NOT NULL    DEFAULT 1 COMMENT '1 : 증가, 0 : 감소',
    `type`            CHAR(20)        NOT NULL    DEFAULT 'COMPETITION' COMMENT '증감 이유 종류',
    `memo`            VARCHAR(60)     NOT NULL    DEFAULT '' COMMENT '메모',
    `created_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ClubMemberPointLog(club_member_id) -> ClubMember(id)
ALTER TABLE ClubMemberPointLog
    ADD CONSTRAINT FK_ClubMemberPointLog_club_member_id_ClubMember_id FOREIGN KEY (club_member_id)
        REFERENCES ClubMember (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ClubMemberPointLog(club_member_id)
-- ALTER TABLE ClubMemberPointLog
-- DROP FOREIGN KEY FK_ClubMemberPointLog_club_member_id_ClubMember_id;


-- EntryTeamScoreLog Table Create SQL
-- 테이블 생성 SQL - EntryTeamScoreLog
CREATE TABLE EntryTeamScoreLog
(
    `id`             BIGINT          NOT NULL    AUTO_INCREMENT,
    `entry_team_id`  BIGINT          NOT NULL,
    `score`          INT UNSIGNED    NOT NULL    DEFAULT 0,
    `plus`           TINYINT         NOT NULL    DEFAULT 1 COMMENT '1 : 증가, 0 : 감소',
    `type`           CHAR(20)        NOT NULL    DEFAULT 'MATCH' COMMENT '증감 이유 종류, MATCH, ADMIN, ETC',
    `memo`           VARCHAR(60)     NOT NULL    DEFAULT '' COMMENT '메모',
    `created_at`     TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`     TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - EntryTeamScoreLog(entry_team_id) -> EntryTeam(id)
ALTER TABLE EntryTeamScoreLog
    ADD CONSTRAINT FK_EntryTeamScoreLog_entry_team_id_EntryTeam_id FOREIGN KEY (entry_team_id)
        REFERENCES EntryTeam (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - EntryTeamScoreLog(entry_team_id)
-- ALTER TABLE EntryTeamScoreLog
-- DROP FOREIGN KEY FK_EntryTeamScoreLog_entry_team_id_EntryTeam_id;


-- PreRoundGroup Table Create SQL
-- 테이블 생성 SQL - PreRoundGroup
CREATE TABLE PreRoundGroup
(
    `id`              BIGINT         NOT NULL    AUTO_INCREMENT,
    `competition_id`  BIGINT         NOT NULL,
    `entry_team_id`   BIGINT         NOT NULL,
    `name`            VARCHAR(20)    NOT NULL    COMMENT '예선 조명 (A조)',
    `created_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP      NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - PreRoundGroup(competition_id) -> Competition(id)
ALTER TABLE PreRoundGroup
    ADD CONSTRAINT FK_PreRoundGroup_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PreRoundGroup(competition_id)
-- ALTER TABLE PreRoundGroup
-- DROP FOREIGN KEY FK_PreRoundGroup_competition_id_Competition_id;

-- Foreign Key 설정 SQL - PreRoundGroup(entry_team_id) -> EntryTeam(id)
ALTER TABLE PreRoundGroup
    ADD CONSTRAINT FK_PreRoundGroup_entry_team_id_EntryTeam_id FOREIGN KEY (entry_team_id)
        REFERENCES EntryTeam (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PreRoundGroup(entry_team_id)
-- ALTER TABLE PreRoundGroup
-- DROP FOREIGN KEY FK_PreRoundGroup_entry_team_id_EntryTeam_id;


-- FinalRoundTeam Table Create SQL
-- 테이블 생성 SQL - FinalRoundTeam
CREATE TABLE FinalRoundTeam
(
    `id`                        BIGINT               NOT NULL    AUTO_INCREMENT,
    `competition_id`            BIGINT               NOT NULL,
    `entry_team_id`             BIGINT               NOT NULL,
    `current_tournament_level`  SMALLINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '32, 16, 8, 4, 2, 1 : 현재 단계',
    `created_at`                TIMESTAMP            NOT NULL    DEFAULT NOW(),
    `updated_at`                TIMESTAMP            NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - FinalRoundTeam(competition_id) -> Competition(id)
ALTER TABLE FinalRoundTeam
    ADD CONSTRAINT FK_FinalRoundTeam_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FinalRoundTeam(competition_id)
-- ALTER TABLE FinalRoundTeam
-- DROP FOREIGN KEY FK_FinalRoundTeam_competition_id_Competition_id;

-- Foreign Key 설정 SQL - FinalRoundTeam(entry_team_id) -> EntryTeam(id)
ALTER TABLE FinalRoundTeam
    ADD CONSTRAINT FK_FinalRoundTeam_entry_team_id_EntryTeam_id FOREIGN KEY (entry_team_id)
        REFERENCES EntryTeam (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FinalRoundTeam(entry_team_id)
-- ALTER TABLE FinalRoundTeam
-- DROP FOREIGN KEY FK_FinalRoundTeam_entry_team_id_EntryTeam_id;


-- PreRoundDetail Table Create SQL
-- 테이블 생성 SQL - PreRoundDetail
CREATE TABLE PreRoundDetail
(
    `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
    `competition_id`  BIGINT          NULL,
    `time_table_url`  VARCHAR(255)    NULL        COMMENT '타임테이블 이미지 주소',
    `created_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - PreRoundDetail(competition_id) -> Competition(id)
ALTER TABLE PreRoundDetail
    ADD CONSTRAINT FK_PreRoundDetail_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PreRoundDetail(competition_id)
-- ALTER TABLE PreRoundDetail
-- DROP FOREIGN KEY FK_PreRoundDetail_competition_id_Competition_id;


-- FinalRoundDetail Table Create SQL
-- 테이블 생성 SQL - FinalRoundDetail
CREATE TABLE FinalRoundDetail
(
    `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
    `competition_id`  BIGINT          NULL,
    `time_table_url`  VARCHAR(255)    NULL        COMMENT '타임테이블 이미지 주소',
    `created_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`      TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - FinalRoundDetail(competition_id) -> Competition(id)
ALTER TABLE FinalRoundDetail
    ADD CONSTRAINT FK_FinalRoundDetail_competition_id_Competition_id FOREIGN KEY (competition_id)
        REFERENCES Competition (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FinalRoundDetail(competition_id)
-- ALTER TABLE FinalRoundDetail
-- DROP FOREIGN KEY FK_FinalRoundDetail_competition_id_Competition_id;


-- FriendMatchingRequest Table Create SQL
-- 테이블 생성 SQL - FriendMatchingRequest
CREATE TABLE FriendMatchingRequest
(
    `id`                       BIGINT         NOT NULL    AUTO_INCREMENT,
    `friend_matching_post_id`  BIGINT         NOT NULL,
    `club_id`                  BIGINT         NOT NULL,
    `user_id`                  BIGINT         NOT NULL,
    `type`                     CHAR(10)       NOT NULL    DEFAULT 'WAIT' COMMENT '보류 / 수락 / 거절',
    `location`                 POINT          NULL        COMMENT '위치 정보',
    `location_address`         VARCHAR(60)    NOT NULL    DEFAULT '' COMMENT '초청 시 위치값 받는 필드',
    `name`                     VARCHAR(20)    NOT NULL,
    `phone_number`             VARCHAR(20)    NOT NULL,
    `created_at`               TIMESTAMP      NOT NULL    DEFAULT NOW(),
    `updated_at`               TIMESTAMP      NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - FriendMatchingRequest(friend_matching_post_id) -> FriendMatchingPost(id)
ALTER TABLE FriendMatchingRequest
    ADD CONSTRAINT FK_FriendMatchingRequest_friend_matching_post_id_FriendMatchingP FOREIGN KEY (friend_matching_post_id)
        REFERENCES FriendMatchingPost (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingRequest(friend_matching_post_id)
-- ALTER TABLE FriendMatchingRequest
-- DROP FOREIGN KEY FK_FriendMatchingRequest_friend_matching_post_id_FriendMatchingP;

-- Foreign Key 설정 SQL - FriendMatchingRequest(club_id) -> Club(id)
ALTER TABLE FriendMatchingRequest
    ADD CONSTRAINT FK_FriendMatchingRequest_club_id_Club_id FOREIGN KEY (club_id)
        REFERENCES Club (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingRequest(club_id)
-- ALTER TABLE FriendMatchingRequest
-- DROP FOREIGN KEY FK_FriendMatchingRequest_club_id_Club_id;

-- Foreign Key 설정 SQL - FriendMatchingRequest(user_id) -> User(id)
ALTER TABLE FriendMatchingRequest
    ADD CONSTRAINT FK_FriendMatchingRequest_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FriendMatchingRequest(user_id)
-- ALTER TABLE FriendMatchingRequest
-- DROP FOREIGN KEY FK_FriendMatchingRequest_user_id_User_id;


-- MatchResultPostScoreLog Table Create SQL
-- 테이블 생성 SQL - MatchResultPostScoreLog
CREATE TABLE MatchResultPostScoreLog
(
    `id`                         BIGINT          NOT NULL    AUTO_INCREMENT,
    `match_result_post_club_id`  BIGINT          NOT NULL,
    `user_id`                    BIGINT          NULL        COMMENT 'NULL 가능 / 사이트에 없는 사람도 입력은 가능하게끔',
    `name`                       VARCHAR(20)     NOT NULL    DEFAULT '' COMMENT '이름 중복 저장',
    `score`                      INT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '득점',
    `extra`                      JSON            NOT NULL    COMMENT '종목별로 작성 (농구의 경우 리바운드, 어시스트 등)',
    `created_at`                 TIMESTAMP       NOT NULL    DEFAULT NOW(),
    `updated_at`                 TIMESTAMP       NOT NULL    DEFAULT NOW(),
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - MatchResultPostScoreLog(match_result_post_club_id) -> MatchResultPostClub(id)
ALTER TABLE MatchResultPostScoreLog
    ADD CONSTRAINT FK_MatchResultPostScoreLog_match_result_post_club_id_MatchResult FOREIGN KEY (match_result_post_club_id)
        REFERENCES MatchResultPostClub (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MatchResultPostScoreLog(match_result_post_club_id)
-- ALTER TABLE MatchResultPostScoreLog
-- DROP FOREIGN KEY FK_MatchResultPostScoreLog_match_result_post_club_id_MatchResult;

-- Foreign Key 설정 SQL - MatchResultPostScoreLog(user_id) -> User(id)
ALTER TABLE MatchResultPostScoreLog
    ADD CONSTRAINT FK_MatchResultPostScoreLog_user_id_User_id FOREIGN KEY (user_id)
        REFERENCES User (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MatchResultPostScoreLog(user_id)
-- ALTER TABLE MatchResultPostScoreLog
-- DROP FOREIGN KEY FK_MatchResultPostScoreLog_user_id_User_id;


