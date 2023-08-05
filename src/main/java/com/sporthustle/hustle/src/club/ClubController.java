package com.sporthustle.hustle.src.club;

import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.ClubRepository;
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
    public ResponseEntity<Club> registerClub(@RequestBody Club club) {
        Club savedClub = clubService.registerClub(club);
        return new ResponseEntity<>(savedClub, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        if (club == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(club, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
}
