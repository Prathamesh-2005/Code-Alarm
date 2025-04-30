package com.Jadhav.Contest_tracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String contestName;
    private String platform;
    @Column(nullable = false, unique = true)
    private String contestId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date contestStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date contestEndDate;

    private long contestDuration;
    private String contestUrl;

    public Contest() {}

    public Contest(String contestName, String platform, String contestId,
                   Date contestStartDate, Date contestEndDate,
                   long contestDuration, String contestUrl) {
        this.contestName = contestName;
        this.platform = platform;
        this.contestId = contestId;
        this.contestStartDate = contestStartDate;
        this.contestEndDate = contestEndDate;
        this.contestDuration = contestDuration;
        this.contestUrl = contestUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public Date getContestStartDate() {
        return contestStartDate;
    }

    public void setContestStartDate(Date contestStartDate) {
        this.contestStartDate = contestStartDate;
    }

    public Date getContestEndDate() {
        return contestEndDate;
    }

    public void setContestEndDate(Date contestEndDate) {
        this.contestEndDate = contestEndDate;
    }

    public long getContestDuration() {
        return contestDuration;
    }

    public void setContestDuration(long contestDuration) {
        this.contestDuration = contestDuration;
    }

    public String getContestUrl() {
        return contestUrl;
    }

    public void setContestUrl(String contestUrl) {
        this.contestUrl = contestUrl;
    }
}