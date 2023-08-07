package com.sporthustle.hustle.src.competition;

import com.sporthustle.hustle.src.competition.model.*;
import com.sporthustle.hustle.src.user.model.JoinRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "Competition")
@RequestMapping("/api/competition")
@RequiredArgsConstructor
@RestController

public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping //대회등록 (주최자)
    public ResponseEntity<HostRes> createCompetition(@RequestBody HostReq hostReq, @RequestParam("event") String event){
        HostRes hostRes = competitionService.createCompetition(hostReq, event);
        return ResponseEntity.ok(hostRes);
    }


    @GetMapping //대회조회(event==null일 경우 year,month로만 조회, event!=null일 경우 특정종목만 조회)
    public ResponseEntity<List<ReadCompetitionRes>> readCompetitionByMonth(@RequestParam(value = "month",required = false)int month
            , @RequestParam(value = "year", required = false)int year
            , @RequestParam(value = "event", required = false) String event
            , @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<ReadCompetitionRes> data = competitionService.readCompetition(month, year, pageable, event);
        return ResponseEntity.ok(data);
    }



    @GetMapping("/{competitionId}") // 해당 대회에 참가하고있는 팀, 참가현황,  대회상세정보 조회
    public ResponseEntity<ReadCompetitionRes> readCompetitionById(@PathVariable("competitionId") Long competitionId){
        ReadCompetitionRes data = competitionService.readCompetitionById(competitionId);
        return ResponseEntity.ok(data);
    }



    @PostMapping("/{competitionId}") //대회신청 (참여자)
    public ResponseEntity<EntryRes> entryCompetition(@RequestBody EntryReq entryReq
            , @PathVariable("competitionId") Long competitionId){
        EntryRes entityRes = competitionService.entryCompetition(entryReq, competitionId);
        return ResponseEntity.ok(entityRes);
    }



}
