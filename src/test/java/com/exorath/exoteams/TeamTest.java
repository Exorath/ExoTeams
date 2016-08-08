package com.exorath.exoteams;

import com.exorath.exoproperties.Propertiesable;
import com.exorath.exoproperties.Property;
import com.exorath.exoteams.player.TeamPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by ToonSevrin on 7/23/2016.
 */
public class TeamTest {

    private Team team;
    private TeamPlayer player1, player2;

    @Before
    public void setup(){
        team = new Team();
        player1 = mock(TeamPlayer.class);
        player2 = mock(TeamPlayer.class);
    }

    @Test
    public void defaultSizeTest(){
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void addPlayerContainTest(){
        team.add(player1);
        assertTrue(team.getPlayers().contains(player1));
    }

    @Test
    public void addPlayerContainTest2(){
        team.add(player1);
        team.add(player2);
        assertTrue(team.getPlayers().contains(player1));
        assertTrue(team.getPlayers().contains(player2));
    }
    @Test
    public void addPlayerEventCalledTest(){
        //TODO; this test does not seem right
        team.add(player1);
        assertTrue(team.getPlayers().contains(player1));
    }
    @Test
    public void addPlayerSizeTest(){
        team.add(player1);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void addPlayerSizeTest2(){
        team.add(player1);
        team.add(player2);
        assertEquals(team.getPlayers().size(), 2);
    }

    @Test
    public void removePlayerContainTest(){
        team.add(player1);
        team.remove(player1);
        assertFalse(team.getPlayers().contains(player1));
    }

    @Test
    public void removePlayerSizeTest(){
        team.add(player1);
        team.remove(player1);
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void removePlayerSizeTest1(){
        team.add(player1);
        team.add(player2);
        team.remove(player1);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void removePlayerSizeTest2(){
        team.add(player1);
        team.remove(player1);
        team.add(player2);
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
    @Test
    public void isPropertiesableTest(){
        Team team = new Team();
        assertTrue(team instanceof Propertiesable);
    }
    @Test
    public void startRulesNotNullTest(){
        Team team = new Team();
        assertTrue(team.getStartRules() != null);
    }
}
