package com.sporthustle.hustle.src.sportevent;

import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.sportevent.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;

    @Autowired
    public SportEventService(SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
    }

    public List<SportEvent> getAllSportEvents() {
        return sportEventRepository.findAll();
    }

    public SportEvent getSportEventById(Long id) {
        Optional<SportEvent> optionalSportEvent = sportEventRepository.findById(id);
        return optionalSportEvent.orElse(null);
    }

    public SportEvent createSportEvent(SportEvent sportEvent) {
        return sportEventRepository.save(sportEvent);
    }

    public void deleteSportEvent(Long id) {
        sportEventRepository.deleteById(id);
    }
}
