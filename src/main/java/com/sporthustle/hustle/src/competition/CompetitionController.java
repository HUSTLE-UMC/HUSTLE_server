package com.sporthustle.hustle.src.competition;

import com.sporthustle.hustle.src.competition.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "Competition")
@RequiredArgsConstructor
@RestController

public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping("/competition") //대회등록 (주최자)
    public ResponseEntity<HostRes> createCompetition(@RequestBody HostReq hostReq){ //신청자 id 어떻게?
        HostRes hostRes = competitionService.createCompetition(hostReq);
        return ResponseEntity.ok(hostRes);
    }
    @GetMapping("/competition/{year}/{month}") //월별대회조회(종목상관없이)
    public ResponseEntity<List<ReadCompetitionRes>> readCompetitionByMonth(@PathVariable("month")int month, @PathVariable("year")int year, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        List<ReadCompetitionRes> data = competitionService.readCompetitionByMonth(month, year, pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/competition/{year}/{month}/{event}") // 종목별 대회조회 (월별)
    public ResponseEntity<List<ReadCompetitionRes>> readCompetitionByEvent(@PathVariable("month")int month, @PathVariable("year")int year, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("event")String event){
        List<ReadCompetitionRes> data = competitionService.readCompetitionByEvent(month,year,pageable,event);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/competition/{competitionId}") // 해당 대회에 참가하고있는 팀, 참가현황,  대회상세정보 조회
    public ResponseEntity<ReadCompetitionInfoRes> readCompetitionInfo(@PathVariable("competitionId") Long competitionId){
        ReadCompetitionInfoRes data = competitionService.readCompetitionInfo(competitionId);
        return ResponseEntity.ok(data);
    }



    @PostMapping("/competition/{competitionId}") //대회신청 (참여자)
    public ResponseEntity<EntryRes> entryCompetition(@RequestBody EntryReq entryReq, @PathVariable("competitionId") Long competitionId){
        EntryRes entityRes = competitionService.entryCompetition(entryReq, competitionId);
        return ResponseEntity.ok(entityRes);
    }



}
