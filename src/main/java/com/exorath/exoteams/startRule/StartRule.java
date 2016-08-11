package com.exorath.exoteams.startRule;

import com.exorath.exorules.rule.EasyRule;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Call evaluate to emit to subscribers (epic documentation to be improved)
 * Created by Toon on 8/2/2016.
 */
public abstract class StartRule extends EasyRule {
    /**
     * Gets the reason why the game cannot start. This reason may display in chat.
     * @return the reason why the game cannot start
     */
   public abstract String getReason();
}
