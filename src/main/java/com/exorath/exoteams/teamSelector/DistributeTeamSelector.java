package com.exorath.exoteams.teamSelector;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;

import java.util.Collection;

/**
 * This selector returns the team with the least amount of players in it.
 * Created by toonsev on 8/12/2016.
 */
public class DistributeTeamSelector implements TeamSelector {
    @Override
    public Team getTeam(Collection<Team> teams, TeamPlayer joiningPlayer) throws AlreadyInTeamException {
        if (teams.stream().filter(team -> team.getPlayers().contains(joiningPlayer)).findFirst().isPresent())
            throw new AlreadyInTeamException();
        Team lowest = null;
        for (Team team : teams)
            if (lowest == null || lowest.getPlayers().size() > team.getPlayers().size())
                if (!team.isFull())
                    lowest = team;
        return lowest;
    }
}
