package com.sporthustle.hustle.src.club;

import com.sporthustle.hustle.src.club.entity.Club;
import com.sporthustle.hustle.src.club.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    public Club registerClub(Club club) {
        return clubRepository.save(club);
    }
    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}