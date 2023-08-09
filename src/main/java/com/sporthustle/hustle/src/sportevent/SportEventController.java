package com.sporthustle.hustle.src.sportevent;

import com.sporthustle.hustle.src.sportevent.entity.SportEvent;
import com.sporthustle.hustle.src.sportevent.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport-events")
public class SportEventController {

    private final SportEventService sportEventService;

    @Autowired
    public SportEventController(SportEventService sportEventService) {
        this.sportEventService = sportEventService;
    }

    @GetMapping
    public List<SportEvent> getAllSportEvents() {
        return sportEventService.getAllSportEvents();
    }

    @GetMapping("/{id}")
    public SportEvent getSportEventById(@PathVariable Long id) {
        return sportEventService.getSportEventById(id);
    }

    @PostMapping
    public SportEvent createSportEvent(@RequestBody SportEvent sportEvent) {
        return sportEventService.createSportEvent(sportEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteSportEvent(@PathVariable Long id) {
        sportEventService.deleteSportEvent(id);
    }
}
