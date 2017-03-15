package com.exorath.exoteams.teamSelector;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;

import java.util.Collection;

/**
 * Created by toonsev on 8/11/2016.
 */
public interface TeamSelector {
    Team getTeam(Collection<Team> teams, TeamPlayer joiningPlayer) throws AlreadyInTeamException;
}
