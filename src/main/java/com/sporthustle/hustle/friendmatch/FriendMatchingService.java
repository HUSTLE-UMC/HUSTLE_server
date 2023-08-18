package com.sporthustle.hustle.friendmatch;

import com.sporthustle.hustle.competition.dto.CompetitionContactRequestDTO;
import com.sporthustle.hustle.competition.dto.CompetitionResponseDTO;
import com.sporthustle.hustle.competition.dto.CreateCompetitionResponseDTO;
import com.sporthustle.hustle.competition.entity.competition.Competition;
import com.sporthustle.hustle.competition.entity.competition.CompetitionContact;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostRequestDTO;
import com.sporthustle.hustle.friendmatch.dto.CreateFriendMatchingPostResponseDTO;
import com.sporthustle.hustle.friendmatch.repository.FriendMatchingRepository;
import com.sporthustle.hustle.sport.SportUtils;
import com.sporthustle.hustle.sport.entity.SportEvent;
import com.sporthustle.hustle.user.UserUtils;
import com.sporthustle.hustle.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendMatchingService {
    private final FriendMatchingRepository friendMatchingRepository;
}
