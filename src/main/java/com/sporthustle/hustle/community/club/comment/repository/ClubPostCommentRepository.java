package com.sporthustle.hustle.community.club.comment.repository;

import com.sporthustle.hustle.community.club.comment.entity.ClubPostComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPostCommentRepository extends JpaRepository<ClubPostComment, Long> {

  List<ClubPostComment> findAllByClubPost_IdOrderByIdDesc(Long clubPostId);

  List<ClubPostComment> findAllByClubPost_IdOrderByLikeCountDesc(Long clubPostId);
}
