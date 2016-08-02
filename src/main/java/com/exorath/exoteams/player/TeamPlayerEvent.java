package com.exorath.exoteams.player;

import com.exorath.exoteams.player.TeamPlayer;

/**
 * Created by toonsev on 7/23/2016.
 */
public class TeamPlayerEvent {
    private TeamPlayer player;

    public TeamPlayerEvent(TeamPlayer player){
        this.player = player;
    }

    public TeamPlayer getPlayer() {
        return player;
    }
}
