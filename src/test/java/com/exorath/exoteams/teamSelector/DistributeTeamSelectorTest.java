package com.exorath.exoteams.teamSelector;

import com.exorath.exoteams.Team;
import com.exorath.exoteams.player.TeamPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by toonsev on 8/13/2016.
 */
public class DistributeTeamSelectorTest {
    private DistributeTeamSelector distributeTeamSelector;
    private Team team1Player;
    private Team teamEmpty1, teamEmpty2;

    @Before
    public void setup(){
        distributeTeamSelector = new DistributeTeamSelector();
        team1Player = new Team();
        team1Player.add(mock(TeamPlayer.class));
        teamEmpty1 = new Team();
        teamEmpty2 = new Team();
    }

    @Test
    public void selectsTeamWithLeastPlayersTest(){
        List<Team> teams = new ArrayList<>();
        teams.add(teamEmpty1);
        teams.add(team1Player);
        assertEquals(distributeTeamSelector.getTeam(teams, mock(TeamPlayer.class)), teamEmpty1);
    }

    @Test
    public void returnsTeamIfItsTheOnlyOneTest(){
        List<Team> teams = new ArrayList<>();
        teams.add(teamEmpty1);
        assertEquals(distributeTeamSelector.getTeam(teams, mock(TeamPlayer.class)), teamEmpty1);
    }

    @Test
    public void returnsNullIfNoTeamsTest(){
        List<Team> teams = new ArrayList<>();
        assertEquals(distributeTeamSelector.getTeam(teams, mock(TeamPlayer.class)), null);
    }

}
