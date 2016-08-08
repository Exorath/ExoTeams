package com.exorath.exoteams.player;

import com.exorath.exoteams.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Toon on 8/2/2016.
 */
public class TeamPlayerLeaveTeamEventTest {

    private TeamPlayerLeaveTeamEvent teamPlayerEvent;

    @Before
    public void setup() {
        teamPlayerEvent = new TeamPlayerLeaveTeamEvent(mock(Collection.class), mock(Team.class), mock(TeamPlayer.class));
    }

    @Test
    public void playerNotNullTest() {
        assertTrue(teamPlayerEvent.getPlayer() != null);
    }

    @Test
    public void teamsNotNullTest() {
        assertTrue(teamPlayerEvent.getTeams() != null);
    }


    @Test
    public void teamNotNullTest() {
        assertTrue(teamPlayerEvent.getTeam() != null);
    }
}
