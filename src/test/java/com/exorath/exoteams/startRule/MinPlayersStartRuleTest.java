package com.exorath.exoteams.startRule;

import com.exorath.exorules.rule.Rule;
import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Toon on 8/2/2016.
 */
public class MinPlayersStartRuleTest {

    private Team team;
    private StartRule startRule1player, startRule2players;
    private TeamPlayer player1, player2;

    @Before
    public void setup() {
        startRule1player = new MinPlayersStartRule(1);
        startRule2players = new MinPlayersStartRule(2);

        player1 = mock(TeamPlayer.class);
        player2 = mock(TeamPlayer.class);

        team = new Team();
    }

    @Test
    public void cannotStartByDefaultTest() {
        assertFalse(startRule1player.evaluate());
    }

    @Test
    public void canStartAfterJoinTest() {
        team.add(player1);
        startRule1player.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player1));
        assertTrue(startRule1player.evaluate());
    }

    @Test
    public void cannotStartAfterJoinTest() {
        team.add(player1);
        startRule2players.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player1));
        assertFalse(startRule2players.evaluate());
    }

    @Test
    public void canStartAfterLeaveTest() {
        Team team = new Team();
        team.add(player1);
        team.add(player2);
        startRule1player.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player1));
        startRule1player.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player2));
        team.remove(player1);
        startRule1player.onPlayerLeaveTeam(new TeamPlayerLeaveTeamEvent(mock(Collection.class), team, player1));
        assertTrue(startRule1player.evaluate());
    }

    @Test
    public void cannotStartAfterLeaveTest() {
        team.add(player1);
        startRule1player.onPlayerJoinTeam(new TeamPlayerJoinTeamEvent(mock(Collection.class), team, player1));
        team.remove(player1);
        startRule1player.onPlayerLeaveTeam(new TeamPlayerLeaveTeamEvent(mock(Collection.class), team, player1));
        assertFalse(startRule1player.evaluate());
    }
}
