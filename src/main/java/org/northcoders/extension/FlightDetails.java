package org.northcoders.extension;
//Api key: 2dcbc6246a010ea83a8cfca55d4af3c9
//API ID: 2fec819a

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDetails {
    private String flightName;
    private String id;
    private Date scheduledDate;
    private String terminal;
    private LocalDate actualLandingTime;
    private String aircraftType;


    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public LocalDate getActualLandingTime() {
        return actualLandingTime;
    }

    public void setActualLandingTime(LocalDate actualLandingTime) {
        this.actualLandingTime = actualLandingTime;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }
}
