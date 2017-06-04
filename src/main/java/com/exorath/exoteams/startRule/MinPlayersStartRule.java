package com.exorath.exoteams.startRule;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerEvent;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Created by Toon on 8/2/2016.
 */
public class MinPlayersStartRule extends TeamStartRule {
    private boolean canStart = false;
    private int minPlayersAmount;

    public MinPlayersStartRule(int minPlayersAmount){
        this.minPlayersAmount = minPlayersAmount;
    }

    public int getMinPlayersAmount() {
        return minPlayersAmount;
    }

    @Override
    public boolean doEvaluate() {
        return canStart;
    }

    @Override
    public void onPlayerJoinTeam(Team team, TeamPlayer player) {
        updateCanStart(team);
    }

    @Override
    public void onPlayerLeaveTeam(Team team, TeamPlayer player) {
        updateCanStart(team);
    }

    protected void updateCanStart(Team team){
        boolean canStart = team.getPlayers().size() >= minPlayersAmount;
        if(this.canStart != canStart){
            this.canStart = canStart;
            evaluate();
        }
    }

    @Override
    public String getReason() {
        return "Insufficient players";
    }
}