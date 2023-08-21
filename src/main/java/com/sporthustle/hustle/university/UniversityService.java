package com.sporthustle.hustle.university;

import com.sporthustle.hustle.university.dto.FindUniversitiesResponseDTO;
import com.sporthustle.hustle.university.dto.UniversityResponseDTO;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.university.repository.UniversityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UniversityService {

  private final UniversityRepository universityRepository;

  @Transactional(readOnly = true)
  public FindUniversitiesResponseDTO findUniversities(String keyword) {
    List<University> universities = universityRepository.findAllByNameStartsWith(keyword);

    List<UniversityResponseDTO> universityResponseDTOs =
        universities.stream().map(UniversityResponseDTO::from).collect(Collectors.toList());

    FindUniversitiesResponseDTO findUniversitiesResponseDTO =
        FindUniversitiesResponseDTO.builder().universities(universityResponseDTOs).build();
    return findUniversitiesResponseDTO;
  }
}
