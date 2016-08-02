package com.exorath.exoteams.startRule;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Toon on 8/2/2016.
 */
public class MinPlayersStartRuleTest {
    @Test
    public void cannotStartByDefaultTest(){
        MinPlayersStartRule startRule = new MinPlayersStartRule(1);
        assertFalse(startRule.canStart());
    }

    @Test
    public void canStartAfterJoinTest(){
        MinPlayersStartRule startRule = new MinPlayersStartRule(1);
        TeamPlayer player = mock(TeamPlayer.class);
        Team team = new Team();
        team.add(player);
        startRule.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player));
        assertTrue(startRule.canStart());
    }

    @Test
    public void cannotStartAfterJoinTest(){
        MinPlayersStartRule startRule = new MinPlayersStartRule(2);
        TeamPlayer player = mock(TeamPlayer.class);
        Team team = new Team();
        team.add(player);
        startRule.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player));
        assertFalse(startRule.canStart());
    }

    @Test
    public void canStartAfterLeaveTest(){
        MinPlayersStartRule startRule = new MinPlayersStartRule(1);
        TeamPlayer player = mock(TeamPlayer.class);
        TeamPlayer player2 = mock(TeamPlayer.class);
        Team team = new Team();
        team.add(player);
        team.add(mock(TeamPlayer.class));
        startRule.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player));
        startRule.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player2));
        startRule.onPlayerLeaveTeam(new TeamPlayerLeaveTeamEvent(mock(Collection.class), team, player));
        assertTrue(startRule.canStart());
    }

    @Test
    public void cannotStartAfterLeaveTest(){
        MinPlayersStartRule startRule = new MinPlayersStartRule(1);
        TeamPlayer player = mock(TeamPlayer.class);
        Team team = new Team();
        team.add(player);
        startRule.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player));
        team.remove(player);
        startRule.onPlayerLeaveTeam(new TeamPlayerLeaveTeamEvent(mock(Collection.class), team, player));
        assertFalse(startRule.canStart());
    }
}
