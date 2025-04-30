package com.Jadhav.Contest_tracker.Repositery;

import com.Jadhav.Contest_tracker.Model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContestRepository extends JpaRepository<Contest, UUID> {
    boolean existsByContestId(String contestId);

    List<Contest> findAllByOrderByContestStartDateAsc();

    List<Contest> findByPlatform(String platform);

    List<Contest> findByContestStartDateBetween(Date start, Date end);

    List<Contest> findByPlatformAndContestStartDateBetween(String platform, Date start, Date end);

    Optional<Contest> findByContestId(String contestId);
}