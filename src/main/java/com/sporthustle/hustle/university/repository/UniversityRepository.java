package com.sporthustle.hustle.university.repository;

import com.sporthustle.hustle.university.entity.University;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
  List<University> findAllByNameStartsWith(String name);

  Optional<University> findById(Long id);
}
