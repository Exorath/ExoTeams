package com.exorath.exoteams.player;

import com.exorath.exoteams.Team;

import java.util.Collection;

/**
 * Created by toonsev on 7/23/2016.
 */
public class TeamPlayerEvent {
    private Collection<Team> teams;
    private Team team;
    private TeamPlayer player;

    public TeamPlayerEvent(Collection<Team> teams, Team team, TeamPlayer player){
        this.teams = teams;
        this.team = team;
        this.player = player;
    }

    public Collection<Team> getTeams() {
        return teams;
    }

    public Team getTeam() {
        return team;
    }

    public TeamPlayer getPlayer() {
        return player;
    }
}
