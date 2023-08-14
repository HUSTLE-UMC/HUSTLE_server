package com.sporthustle.hustle.university;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.university.repository.UniversityRepository;

public class UniversityUtils {

  public static University getUniversityById(
      Long universityId, UniversityRepository universityRepository) {
    University university =
        universityRepository
            .findById(universityId)
            .orElseThrow(() -> BaseException.from(ErrorCode.UNIVERSITY_NOT_FOUND));
    return university;
  }
}
