package com.exorath.exoteams;

import com.exorath.exoproperties.Property;
import com.exorath.exoteams.player.TeamPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by ToonSevrin on 7/23/2016.
 */
public class TeamTest {

    @Test
    public void defaultSizeTest(){
        Team team = new Team();
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void addPlayerContainTest(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        assertTrue(team.getPlayers().contains(player));
    }

    @Test
    public void addPlayerContainTest2(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        TeamPlayer player2 = mock(TeamPlayer.class);
        team.add(player);
        team.add(player2);
        assertTrue(team.getPlayers().contains(player));
        assertTrue(team.getPlayers().contains(player2));
    }
    @Test
    public void addPlayerEventCalledTest(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        assertTrue(team.getPlayers().contains(player));
    }
    @Test
    public void addPlayerSizeTest(){
        Team team = new Team();
        team.add(mock(TeamPlayer.class));
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void addPlayerSizeTest2(){
        Team team = new Team();
        team.add(mock(TeamPlayer.class));
        team.add(mock(TeamPlayer.class));
        assertEquals(team.getPlayers().size(), 2);
    }

    @Test
    public void removePlayerContainTest(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        team.remove(player);
        assertFalse(team.getPlayers().contains(player));
    }

    @Test
    public void removePlayerSizeTest(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        team.remove(player);
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void removePlayerSizeTest1(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        team.add(mock(TeamPlayer.class));
        team.remove(player);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void removePlayerSizeTest2(){
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);
        team.add(player);
        team.remove(player);
        team.add(mock(TeamPlayer.class));
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void clearTeamTest(){
        Team team = new Team();
        team.add(mock(TeamPlayer.class));
        team.add(mock(TeamPlayer.class));
        team.clear();
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void propertiesNotNullTest(){
        Team team = new Team();
        assertTrue(team.getProperties() != null);
    }
    @Test
    public void propertiesAddTest(){
        Team team = new Team();
        Property<String> property = new Property<>();
        team.getProperties().set(property, "blbla");
        assertTrue(team.getProperties().contains(property));
    }
}
