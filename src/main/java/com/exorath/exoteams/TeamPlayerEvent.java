package com.exorath.exoteams;

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
