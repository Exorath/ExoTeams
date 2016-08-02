package com.exorath.exoteams.player;

import com.exorath.exoteams.Team;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Toon on 8/2/2016.
 */
public class TeamPlayerEventTest {
    @Test
    public void playerNotNullTest(){
        TeamPlayerEvent teamPlayerEvent = new TeamPlayerEvent(mock(Collection.class), mock(Team.class), mock(TeamPlayer.class));
        assertTrue(teamPlayerEvent.getPlayer() != null);
    }

    @Test
    public void teamsNotNullTest(){
        TeamPlayerEvent teamPlayerEvent = new TeamPlayerEvent(mock(Collection.class), mock(Team.class),  mock(TeamPlayer.class));
        assertTrue(teamPlayerEvent.getTeams() != null);
    }



    @Test
    public void teamNotNullTest(){
        TeamPlayerEvent teamPlayerEvent = new TeamPlayerEvent(mock(Collection.class), mock(Team.class),  mock(TeamPlayer.class));
        assertTrue(teamPlayerEvent.getTeam() != null);
    }
}
