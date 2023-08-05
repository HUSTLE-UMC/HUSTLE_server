package com.sporthustle.hustle.src.competition;

import com.sporthustle.hustle.common.exception.BaseException;
import com.sporthustle.hustle.common.exception.ErrorCode;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.model.ClubRepository;
import com.sporthustle.hustle.src.competition.Repository.CompetitionContactRepository;
import com.sporthustle.hustle.src.competition.Repository.CompetitionRepository;
import com.sporthustle.hustle.src.competition.Repository.EntryTeamRepository;
import com.sporthustle.hustle.src.competition.entity.Competition;
import com.sporthustle.hustle.src.competition.entity.CompetitionContact;
import com.sporthustle.hustle.src.competition.entity.EntryTeam;
import com.sporthustle.hustle.src.competition.model.*;
import com.sporthustle.hustle.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionContactRepository competitionContactRepository;
    private final EntryTeamRepository entryTeamRepository;
    private final ReadCompetitionInfoRes readCompetitionInfoRes;
    private final ClubRepository clubRepository;


    @Transactional
    public HostRes createCompetition(HostReq hostreq){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

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
                .status("모집중") //영어로
                .createdAt(timestamp)
                .updateAt(timestamp)
                .user(user)
                .build();

        competitionRepository.save(competition);


        CompetitionContact competitionContact1 = CompetitionContact.builder()
                .competition(competition)
                .name(hostreq.getMainName())
                .phoneNumber(hostreq.getMainPhoneNumber())
                .position("main")
                .createdAt(timestamp)
                .updateAt(timestamp)
                .build();

        competitionContactRepository.save(competitionContact1);

        CompetitionContact competitionContact2 = CompetitionContact.builder()
                .competition(competition)
                .name(hostreq.getSubName())
                .phoneNumber(hostreq.getSubPhoneNumber())
                .position("sub")
                .createdAt(timestamp)
                .updateAt(timestamp)
                .build();

            competitionContactRepository.save(competitionContact2);

        return HostRes.builder().message("대회 등록이 완료되었습니다.").build();
    }
    @Transactional
    public List<ReadCompetitionRes> readCompetitionByMonth(int month, int year, Pageable pageable){
        YearMonth yearMonth = YearMonth.of(year, month);
        int lastDay = yearMonth.lengthOfMonth();

        Timestamp searchStart = new Timestamp(year, month, 1, 00,00,00,000);
        Timestamp searchEnd = new Timestamp(year, month, lastDay,23,59,59,999);
        List<Competition> competitions = competitionRepository.findAllByStartDateBetweenOrEndDateBetween(pageable,searchStart,searchEnd,searchStart,searchEnd).toList();
        List<ReadCompetitionRes>result = new ArrayList<>();
        for(Competition c : competitions){
            ReadCompetitionRes readCompetitionRes = ReadCompetitionRes.builder()
                    .posterUrl(c.getPosterUrl())
                    .title(c.getTitle())
                    .startDate(c.getStartDate())
                    .endDate(c.getEndDate())
                    .build();
            result.add(readCompetitionRes);
        }
        return result;
    }
    @Transactional
    public List<ReadCompetitionRes> readCompetitionByEvent(int month,int year,Pageable pageable,String event){
        YearMonth yearMonth = YearMonth.of(year, month);
        int lastDay = yearMonth.lengthOfMonth();

        Timestamp searchStart = new Timestamp(year, month, 1, 00,00,00,000);
        Timestamp searchEnd = new Timestamp(year, month, lastDay,23,59,59,999);
        List<Competition> competitions = competitionRepository.findAllByStartDateBetweenOrEndDateBetweenAndEvent(pageable,searchStart,searchEnd,searchStart,searchEnd,event).toList();
        List<ReadCompetitionRes>result = new ArrayList<>();
        for(Competition c : competitions){
            ReadCompetitionRes readCompetitionRes = ReadCompetitionRes.builder()
                    .posterUrl(c.getPosterUrl())
                    .title(c.getTitle())
                    .startDate(c.getStartDate())
                    .endDate(c.getEndDate())
                    .build();
            result.add(readCompetitionRes);
        }
        return result;
    }

    @Transactional
    public ReadCompetitionInfoRes readCompetitionInfo(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMPETITION_NOT_FOUND));

        List<CompetitionContact> competitionContact = competitionContactRepository.findByCompetition(competition);
        String mainPhoneNumber = "";
        String subPhoneNumber = "";
        for(CompetitionContact c : competitionContact){
            if(c.getPosition().equals("main"))mainPhoneNumber = c.getPhoneNumber();
            if(c.getPosition().equals("sub"))subPhoneNumber = c.getPhoneNumber();
        }

        List<Club> entryClubList = clubRepository.findByCompetitionId(competitionId);

        ReadCompetitionInfoRes readCompetitionInfoRes = ReadCompetitionInfoRes.builder()
                .startDate(competition.getStartDate())
                .endDate(competition.getEndDate())
                .recruitmentStartDate(competition.getRecruitmentStartDate())
                .recruitmentEndDate(competition.getRecruitmentEndDate())
                .entryFee(competition.getEntryFee())
                .maxEntryCount(competition.getMaxEntryCount())
                .sponsor(competition.getSponsor())
                .place(competition.getPlace())
                .mainPhoneNumber(mainPhoneNumber)
                .subPhoneNumber(subPhoneNumber)
                .numberOfTeam(entryClubList.size())
                .clubList(entryClubList)
                .build();

        return readCompetitionInfoRes;
    }
    @Transactional
    public EntryRes entryCompetition(EntryReq entryReq, Long competitionId){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Competition competition = competitionRepository.findById(competitionId)
                        .orElseThrow(() -> new BaseException(ErrorCode.COMPETITION_NOT_FOUND));

        Club club = clubRepository.findByName(entryReq.getClubName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        EntryTeam entryTeam = EntryTeam.builder()
                .competition(competition)
                .userId(user)
                .club(club)
                .name(entryReq.getMainName())
                .phoneNumber(entryReq.getMainPhoneNumber())
                .score(0)
                .createdAt(timestamp)
                .updateAt(timestamp)
                .build();
        entryTeamRepository.save(entryTeam);

        //status를 모집중 -> 모집완료로 바꾸는 로직필요

        return EntryRes.builder().message("대회 신청이 완료되었습니다.").build();
    }



}
