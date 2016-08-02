package com.exorath.exoteams.startRule;

import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Created by Toon on 8/2/2016.
 */
public interface StartRule {
    boolean canStart();

    void onPlayerJoinTeam(TeamPlayerJoinTeamEvent joinEvent);

    void onPlayerLeaveTeam(TeamPlayerLeaveTeamEvent leaveEvent);
}
