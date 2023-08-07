package com.sporthustle.hustle.src.competition;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.Repository.ClubRepository;
import com.sporthustle.hustle.src.club.model.ClubDTO;
import com.sporthustle.hustle.src.competition.Repository.CompetitionContactRepository;
import com.sporthustle.hustle.src.competition.Repository.CompetitionRepository;
import com.sporthustle.hustle.src.competition.Repository.EntryTeamRepository;
import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.entity.CompetitionContact;
import com.sporthustle.hustle.src.competition.entity.EntryTeam;
import com.sporthustle.hustle.src.competition.model.*;
import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.sportposition.entity.SportEventRepository;
import com.sporthustle.hustle.src.user.entity.User;
import com.sporthustle.hustle.src.user.model.JoinRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionContactRepository competitionContactRepository;
    private final EntryTeamRepository entryTeamRepository;
    private final ClubRepository clubRepository;
    private final SportEventRepository sportEventRepository;


    @Transactional
    public HostRes createCompetition(HostReq hostreq, String event){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        SportEvent sportEvent = sportEventRepository.findByName(event);

        Competition competition = Competition.builder()
                .title(hostreq.getTitle())
                .host(hostreq.getHost())
                .posterUrl(hostreq.getPosterUrl())
                .startDate(hostreq.getStartDate())
                .endDate(hostreq.getEndDate())
                .recruitmentStartDate(hostreq.getRecruitmentStartDate())
                .recruitmentEndDate(hostreq.getRecruitmentEndDate())
                .entryFee(hostreq.getEntryFee())
                .maxEntryCount(hostreq.getMaxEntryCount())
                .preRoundGroupCount(hostreq.getPreRoundGroupCount())
                .finalRoundTeamCount(hostreq.getFinalRoundTeamCount())
                .sponsor(hostreq.getSponsor())
                .place(hostreq.getPlace())
                .state("DRAFT")
                .type("BEFORE")
                .status("")
                .user(user)
                .sportEvent(sportEvent)
                .build();

        competitionRepository.save(competition);

        CompetitionContact mainCompetitionContact = CompetitionContact.builder()
                .competition(competition)
                .name(hostreq.getMainName())
                .phoneNumber(hostreq.getMainPhoneNumber())
                .build();
        competitionContactRepository.save(mainCompetitionContact);

        if(hostreq.getSubName()!=null) {
            CompetitionContact subCompetitionContact = CompetitionContact.builder()
                    .competition(competition)
                    .name(hostreq.getSubName())
                    .phoneNumber(hostreq.getSubPhoneNumber())
                    .build();

            competitionContactRepository.save(subCompetitionContact);
        }

        return HostRes.builder().message("대회 등록이 완료되었습니다.").build();
    }


    @Transactional(readOnly = true)
    public List<ReadCompetitionRes> readCompetition(int month, int year, Pageable pageable, String event){

        LocalDateTime standard = LocalDateTime.of(year, month,1, 0,0,0);
        LocalDateTime firstDate = standard.withDayOfMonth(1);
        LocalDateTime lastDate = standard.withDayOfMonth(standard.toLocalDate().lengthOfMonth());
        List<Competition> competitions =
                //competitionRepository.findAllByStartDateBetweenOrEndDateBetween(pageable,firstDate,lastDate,firstDate,lastDate).toList();
                competitionRepository.findByDateTime(firstDate, lastDate, pageable).toList();


        //모든종목 조회
        if(event == null || event.equals("")) {

            List<ReadCompetitionRes> result = competitions.stream()
                    .map(
                            o ->
                                    ReadCompetitionRes.builder()
                                            .competitionId(o.getId())
                                            .posterUrl(o.getPosterUrl())
                                            .title(o.getTitle())
                                            .startDate(o.getStartDate())
                                            .endDate(o.getEndDate())
                                            .build()
                    ).collect(Collectors.toList());

            return result;
            }
        //특정종목 조회
        else
        {
            List<ReadCompetitionRes> result = new ArrayList<>();

            for(Competition c : competitions){
                if(c.getSportEvent().getName().equals(event)){
                    ReadCompetitionRes readCompetitionRes = ReadCompetitionRes.builder()
                                    .competitionId(c.getId())
                                    .posterUrl(c.getPosterUrl())
                                    .title(c.getTitle())
                                    .startDate(c.getStartDate())
                                    .endDate(c.getEndDate())
                                    .build();

                    result.add(readCompetitionRes);
                }
            }

            return result;

        }
    }


    @Transactional(readOnly = true)
    public ReadCompetitionRes readCompetitionById(Long competitionId) {

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMPETITION_NOT_FOUND));

        List<CompetitionContact> competitionContact = competitionContactRepository.findByCompetition(competition);


        List<ContactDTO> contactDTOS = competitionContact.stream()
                .map(
                        o->
                                ContactDTO.builder()
                                        .Name(o.getName())
                                        .phoneNumber(o.getPhoneNumber())
                                        .build())
                .collect(Collectors.toList());

        List<EntryTeam> entryClubList = entryTeamRepository.findByCompetition(competition);

        List<ClubDTO>clubDTOS = entryClubList.stream()
                .map(
                        o->
                                ClubDTO.builder()
                                        .profileImageUrl(o.getClub().getProfileImageUrl())
                                        .name(o.getClub().getName())
                                        .universityName(o.getClub().getUniversity().getName())
                                        .build())
                .collect(Collectors.toList());


        ReadCompetitionRes readCompetitionRes = ReadCompetitionRes.builder()
                .startDate(competition.getStartDate())
                .endDate(competition.getEndDate())
                .recruitmentStartDate(competition.getRecruitmentStartDate())
                .recruitmentEndDate(competition.getRecruitmentEndDate())
                .entryFee(competition.getEntryFee())
                .maxEntryCount(competition.getMaxEntryCount())
                .sponsor(competition.getSponsor())
                .place(competition.getPlace())
                .contactDTO(contactDTOS)
                .numberOfTeam(entryClubList.size())
                .clubDTOS(clubDTOS)
                .build();

        return readCompetitionRes;
    }



    @Transactional
    public EntryRes entryCompetition(EntryReq entryReq, Long competitionId){
        Competition competition = competitionRepository.findById(competitionId)
                        .orElseThrow(() -> new BaseException(ErrorCode.COMPETITION_NOT_FOUND));

        if(entryTeamRepository.countByCompetition(competition) >= competition.getMaxEntryCount()){
            return EntryRes.builder().message("이미 참가신청이 완료된 대회입니다.").build();
        }

        Club club = clubRepository.findByName(entryReq.getClubName());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        EntryTeam entryTeam = EntryTeam.builder()
                .competition(competition)
                .user(user)
                .club(club)
                .name(entryReq.getMainName())
                .phoneNumber(entryReq.getMainPhoneNumber())
                .score(0L)
                .build();
        entryTeamRepository.save(entryTeam);

        return EntryRes.builder().message("대회 신청이 완료되었습니다.").build();
    }



}
