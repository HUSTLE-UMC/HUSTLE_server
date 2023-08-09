package com.sporthustle.hustle.src.club;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.src.club.dto.ClubDto;
import com.sporthustle.hustle.src.club.dto.InputReq;
import com.sporthustle.hustle.src.club.dto.InputRes;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.sportevent.SportEventRepository;
import com.sporthustle.hustle.src.university.UniversityRepository;
import com.sporthustle.hustle.src.university.entity.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final UniversityRepository universityRepository;
    private final SportEventRepository sportEventRepository;

    public InputRes registerClub(InputReq inputReq) {
        University university =
                universityRepository
                        .findById(inputReq.getUniversityId())
                        .orElseThrow(() -> new BaseException(ErrorCode.UNIVERSITY_NOT_FOUND));
        SportEvent sportEvent =
                sportEventRepository
                        .findById(inputReq.getUniversityId())
                        .orElseThrow(() -> new BaseException(ErrorCode.SPORT_EVENT_NOT_FOUND));

        Club club =
                Club.builder()
                        .name(inputReq.getName())
                        .instagram(inputReq.getInstagram())
                        .youtubeUrl(inputReq.getYoutubeUrl())
                        .mainArea(String.valueOf(inputReq.getMainArea()))
                        .profileImageUrl(inputReq.getProfileImageUrl())
                        .university(university)
                        .state(inputReq.getState())
                        .sportEvent(sportEvent).build();
        clubRepository.save(club);
        return InputRes.builder().message("동아리 등록 완료하셨습니다.").build();
    }
    public ClubDto getClubById(Long id) {
        Club club = clubRepository.findById(id).orElse(null);
        if (club != null) {
            return convertToDTO(club);
        }
        return null;
    }
    public List<ClubDto> getAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private ClubDto convertToDTO(Club club) {
        ClubDto clubDTO = new ClubDto();
        clubDTO.setId(club.getId());
        clubDTO.setName(club.getName());
        clubDTO.setUniversityName(club.getUniversity().getName());
        clubDTO.setMainArea(club.getMainArea());
        clubDTO.setInstagram(club.getInstagram());
        clubDTO.setYoutubeUrl(club.getYoutubeUrl());
        clubDTO.setProfileImageUrl(club.getProfileImageUrl());
        clubDTO.setPoint(club.getPoint());
        return clubDTO;
    }
}