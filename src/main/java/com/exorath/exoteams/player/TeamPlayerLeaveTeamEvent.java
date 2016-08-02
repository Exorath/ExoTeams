package com.exorath.exoteams.player;

import com.exorath.exoteams.Team;

import java.util.Collection;

/**
 * Created by Toon on 8/2/2016.
 */
public class TeamPlayerLeaveTeamEvent extends TeamPlayerEvent {
    public TeamPlayerLeaveTeamEvent(Collection<Team> teams, Team team, TeamPlayer player) {
        super(teams, team, player);
    }
}
