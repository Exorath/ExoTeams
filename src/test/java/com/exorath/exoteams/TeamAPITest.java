package com.exorath.exoteams;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by toonsev on 7/24/2016.
 */
public class TeamAPITest {

    private TeamAPI teamAPI;
    private Team team1, team2;

    @Before
    public void setup() {
        teamAPI = TeamAPI.createAPI();
        team1 = mock(Team.class);
        team2 = mock(Team.class);
    }

    @Test
    public void defaultTeamSizeTest() {
        assertTrue(teamAPI.getTeams().isEmpty());
    }

    @Test
    public void addTeamContainsTest() {
        teamAPI.addTeam(team1);
        assertTrue(teamAPI.getTeams().contains(team1));
    }

    @Test
    public void addTeamContainsTest1() {
        teamAPI.addTeam(team1);
        teamAPI.addTeam(team2);
        assertTrue(teamAPI.getTeams().contains(team1) && teamAPI.getTeams().contains(team2));
    }

    @Test
    public void addTeamSizeTest() {
        teamAPI.addTeam(team1);
        assertEquals(teamAPI.getTeams().size(), 1);
    }

    @Test
    public void addTeamSizeTest1() {
        teamAPI.addTeam(team1);
        teamAPI.addTeam(team2);
        assertEquals(teamAPI.getTeams().size(), 2);
    }


    @Test
    public void removeTeamAPIDoesNotContainsTest() {
        teamAPI.addTeam(team1);
        teamAPI.removeTeam(team1);
        assertFalse(teamAPI.getTeams().contains(team1));
    }


    @Test
    public void removeTeamSizeTest() {
        teamAPI.addTeam(team1);
        teamAPI.removeTeam(team1);
        assertEquals(teamAPI.getTeams().size(), 0);
    }

    @Test
    public void removeTeamSizeTest1() {
        teamAPI.addTeam(team1);
        teamAPI.removeTeam(team1);
        teamAPI.addTeam(team2);
        assertEquals(teamAPI.getTeams().size(), 1);
    }

    @Test
    public void clearTeamSizeTest() {
        teamAPI.addTeam(team1);
        teamAPI.addTeam(team2);
        teamAPI.clear();
        assertEquals(teamAPI.getTeams().size(), 0);
    }
}
