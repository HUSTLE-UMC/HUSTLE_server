package com.sporthustle.hustle.src.competition.Repository;

import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.model.ReadCompetitionRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Page<Competition> findAllByStartDateBetweenOrEndDateBetween(Pageable pageable, Timestamp startDateT1, Timestamp startDateT2, Timestamp endDateT1, Timestamp endDateT2);

    Page<Competition> findAllByStartDateBetweenOrEndDateBetweenAndEvent(Pageable pageable, Timestamp startDateT1, Timestamp startDateT2, Timestamp endDateT1, Timestamp endDateT2, String event);
}
