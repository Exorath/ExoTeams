package com.exorath.exoteams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by toonsev on 7/24/2016.
 */
public class TeamAPITest {

    @Test
    public void defaultTeamSizeTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        assertTrue(teamAPI.getTeams().isEmpty());
    }

    @Test
    public void addTeamContainsTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        Team team = mock(Team.class);
        teamAPI.addTeam(team);
        assertTrue(teamAPI.getTeams().contains(team));
    }

    @Test
    public void addTeamContainsTest1() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        Team team1 = mock(Team.class);
        Team team2 = mock(Team.class);
        teamAPI.addTeam(team1);
        teamAPI.addTeam(team2);
        assertTrue(teamAPI.getTeams().contains(team1) && teamAPI.getTeams().contains(team2));
    }

    @Test
    public void addTeamSizeTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        teamAPI.addTeam(mock(Team.class));
        assertEquals(teamAPI.getTeams().size(), 1);
    }

    @Test
    public void addTeamSizeTest1() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        teamAPI.addTeam(mock(Team.class));
        teamAPI.addTeam(mock(Team.class));
        assertEquals(teamAPI.getTeams().size(), 2);
    }

    @Test
    public void removeTeamContainsTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        Team team = mock(Team.class);
        teamAPI.addTeam(team);
        teamAPI.removeTeam(team);
        assertFalse(teamAPI.getTeams().contains(team));
    }


    @Test
    public void removeTeamSizeTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        Team team = mock(Team.class);
        teamAPI.addTeam(team);
        teamAPI.removeTeam(team);
        assertEquals(teamAPI.getTeams().size(), 0);
    }

    @Test
    public void removeTeamSizeTest1() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        Team team = mock(Team.class);
        teamAPI.addTeam(team);
        teamAPI.removeTeam(team);
        teamAPI.addTeam(mock(Team.class));
        assertEquals(teamAPI.getTeams().size(), 1);
    }

    @Test
    public void clearTeamSizeTest() {
        TeamAPI teamAPI = TeamAPI.createAPI();
        teamAPI.addTeam(mock(Team.class));
        teamAPI.addTeam(mock(Team.class));
        teamAPI.clear();
        assertEquals(teamAPI.getTeams().size(), 0);
    }
}
