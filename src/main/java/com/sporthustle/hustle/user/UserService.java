package com.sporthustle.hustle.user;

import com.sporthustle.hustle.club.ClubService;
import com.sporthustle.hustle.club.dto.MyClubsResponseDTO;
import com.sporthustle.hustle.university.UniversityUtils;
import com.sporthustle.hustle.university.entity.University;
import com.sporthustle.hustle.university.repository.UniversityRepository;
import com.sporthustle.hustle.user.dto.*;
import com.sporthustle.hustle.user.entity.User;
import com.sporthustle.hustle.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ClubService clubService;
  private final UniversityRepository universityRepository;

  @Transactional(readOnly = true)
  public UserResponseDTO getMyProfile(Long userId) {
    User user = UserUtils.getUserById(userId, userRepository);

    List<MyClubsResponseDTO> myClubsResponseDTO = clubService.getMyClubs(userId);

    return UserResponseDTO.from(user, myClubsResponseDTO);
  }

  @Transactional
  public UpdateMyProfileResponseDTO updateMyProfile(
      Long userId, UpdateMyProfileRequestDTO updateMyProfileRequestDTO) {
    User user = UserUtils.getUserById(userId, userRepository);

    String password = updateMyProfileRequestDTO.getPassword();
    if (password != null) {
      String hashedPassword = passwordEncoder.encode(updateMyProfileRequestDTO.getPassword());
      user.updateNewPassword(hashedPassword);
    }

    Long universityId = updateMyProfileRequestDTO.getUniversityId();
    if (universityId != null) {
      University university = UniversityUtils.getUniversityById(universityId, universityRepository);
      user.updateUniversity(university);
    }

    user.updateMyProfilePartial(
        updateMyProfileRequestDTO.getName(),
        updateMyProfileRequestDTO.getBirthday(),
        updateMyProfileRequestDTO.getGender());
    userRepository.save(user);

    return UpdateMyProfileResponseDTO.builder().message("내 정보를 수정했습니다.").build();
  }

  @Transactional
  public DeleteUserResponseDTO deleteUser(Long userId) {
    User user = UserUtils.getUserById(userId, userRepository);

    user.delete();
    userRepository.save(user);

    return DeleteUserResponseDTO.builder().message("성공적으로 탈퇴했습니다.").build();
  }
}
