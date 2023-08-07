package com.sporthustle.hustle.src.competition.Repository;

import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.model.ReadCompetitionRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    //Page<Competition> findAllByStartDateBetweenOrEndDateBetween(Pageable pageable, LocalDateTime startDateT1, LocalDateTime endDateT1,LocalDateTime startDateT2, LocalDateTime endDateT2);
    @Query(nativeQuery = true,
            value = "select * from competition c" + " where c.start_date between :startDate and :endDate " +
            "or c.end_date between :startDate and :endDate")
    Page<Competition> findByDateTime(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    //Page<Competition> findAllByStartDateBetweenOrEndDateBetweenAndSportEvent_Name(Pageable pageable, LocalDateTime startDateT1, LocalDateTime startDateT2, LocalDateTime endDateT1, LocalDateTime endDateT2, String event);
}
