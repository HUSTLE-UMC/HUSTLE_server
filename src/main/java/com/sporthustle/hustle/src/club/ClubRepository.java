package com.sporthustle.hustle.src.club;

import com.sporthustle.hustle.src.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    // 별도의 메소드를 추가할 필요가 있으면 여기에 정의합니다.
}
