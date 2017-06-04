package com.exorath.exoteams.startRule;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.TeamAPI;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Created by toonsev on 6/4/2017.
 */
public class GlobalMinPlayersStartRule extends GlobalStartRule {
    private boolean canStart = false;
    private TeamAPI teamAPI;
    private int minPlayers;

    public GlobalMinPlayersStartRule(TeamAPI teamAPI, int minPlayers) {
        this.teamAPI = teamAPI;
        this.minPlayers = minPlayers;
    }

    @Override
    public String getReason() {
        return "Insufficient players";
    }

    @Override
    public void onPlayerLeave(TeamPlayerLeaveTeamEvent event) {
    }

    @Override
    public void onPlayerJoin(TeamPlayerJoinTeamEvent event) {
        System.out.println("onplayerjoin called");
    }
    @Override
    public boolean doEvaluate() {
        int players = 0;
        for (Team team : teamAPI.getTeams())
            players += team.getPlayers().size();
        return players >= minPlayers;
    }
}
