package com.sporthustle.hustle.src.club;

import com.sporthustle.hustle.src.club.dto.ClubDto;
import com.sporthustle.hustle.src.club.dto.InputReq;
import com.sporthustle.hustle.src.club.dto.InputRes;
import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.ClubRepository;
import com.sporthustle.hustle.src.user.model.JoinReq;
import com.sporthustle.hustle.src.user.model.JoinRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(name = "Clubs", description = "동아리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clubs")
public class ClubController {
    private final ClubService clubService;
    @PostMapping
    public ResponseEntity<InputRes> registerClub(@RequestBody InputReq inputReq) {
        InputRes savedClub = clubService.registerClub(inputReq);
        return new ResponseEntity<>(savedClub, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClubDto> getClubById(@PathVariable Long id) {
        ClubDto clubDto = clubService.getClubById(id);
        if (clubDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clubDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
}