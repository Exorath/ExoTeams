package com.exorath.exoteams.startRule;

import com.exorath.exoteams.player.TeamPlayerEvent;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Created by Toon on 8/2/2016.
 */
public class MinPlayersStartRule extends StartRule {
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
    public void onPlayerJoinTeam(TeamPlayerJoinTeamEvent joinEvent) {
        updateCanStart(joinEvent);
    }

    @Override
    public void onPlayerLeaveTeam(TeamPlayerLeaveTeamEvent leaveEvent) {
        updateCanStart(leaveEvent);
    }

    protected void updateCanStart(TeamPlayerEvent event){
        boolean canStart = event.getTeam().getPlayers().size() >= minPlayersAmount;
        if(this.canStart != canStart){
            this.canStart = canStart;
            evaluate();
        }
    }
}
