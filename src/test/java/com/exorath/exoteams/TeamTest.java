package com.exorath.exoteams;

import com.exorath.exoproperties.Propertiesable;
import com.exorath.exoproperties.Property;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.startRule.MinPlayersStartRule;
import com.exorath.exoteams.startRule.StartRule;
import com.exorath.exoteams.startRule.TeamStartRule;
import org.junit.Before;
import org.junit.Test;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ToonSevrin on 7/23/2016.
 */
public class TeamTest {

    private Team team;
    private TeamPlayer player1, player2;
    private TeamStartRule min1PlayerStartRule;

    @Before
    public void setup() {
        team = new Team();
        player1 = mock(TeamPlayer.class);
        player2 = mock(TeamPlayer.class);
        min1PlayerStartRule = new MinPlayersStartRule(1);
    }

    @Test
    public void defaultSizeTest() {
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void addPlayerContainTest() {
        team.add(player1);
        assertTrue(team.getPlayers().contains(player1));
    }

    @Test
    public void addPlayerContainTest2() {
        team.add(player1);
        team.add(player2);
        assertTrue(team.getPlayers().contains(player1));
        assertTrue(team.getPlayers().contains(player2));
    }

    @Test
    public void addPlayerSubscriberCalledTest() {
        AtomicBoolean called = new AtomicBoolean(false);
        team.getOnPlayerJoinTeamObservable().subscribe(bool -> called.set(true));
        team.add(player1);
        assertTrue(called.get());
    }

    @Test
    public void addPlayerSizeTest() {
        team.add(player1);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void addPlayerSizeTest2() {
        team.add(player1);
        team.add(player2);
        assertEquals(team.getPlayers().size(), 2);
    }

    @Test
    public void removePlayerContainTest() {
        team.add(player1);
        team.remove(player1);
        assertFalse(team.getPlayers().contains(player1));
    }

    @Test
    public void removePlayerSizeTest() {
        team.add(player1);
        team.remove(player1);
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void removePlayerSizeTest1() {
        team.add(player1);
        team.add(player2);
        team.remove(player1);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void removePlayerSizeTest2() {
        team.add(player1);
        team.remove(player1);
        team.add(player2);
        assertEquals(team.getPlayers().size(), 1);
    }

    @Test
    public void removePlayerSubscriberCalledTest() {
        AtomicBoolean called = new AtomicBoolean(false);
        team.getOnPlayerLeaveTeamObservable().subscribe(bool -> called.set(true));
        team.add(player1);
        team.remove(player1);
        assertTrue(called.get());
    }

    @Test
    public void clearTeamTest() {
        team.add(player1);
        team.add(player2);
        team.clear();
        assertEquals(team.getPlayers().size(), 0);
    }

    @Test
    public void propertiesNotNullTest() {
        assertTrue(team.getProperties() != null);
    }

    @Test
    public void propertiesAddTest() {
        Property<String> property = new Property<>();
        team.getProperties().set(property, "blbla");
        assertTrue(team.getProperties().contains(property));
    }

    @Test
    public void isPropertiesableTest() {
        assertTrue(team instanceof Propertiesable);
    }

    @Test
    public void compositeStartRuleNotNullByDefaultTest() {
        assertTrue(team.getCompositeStartRule() != null);
    }


    @Test
    public void afterStartRuleAddedRemoveStartRuleReturnsTrueTest() {
        team.addStartRule(min1PlayerStartRule);
        assertTrue(team.removeStartRule(min1PlayerStartRule));
    }

    @Test
    public void removeStartRuleReturnsFalseWhenRuleNotAddedTest() {
        assertFalse(team.removeStartRule(min1PlayerStartRule));
    }

    @Test
    public void compositeStartRuleCalledWhenAddedStartRuleIsTriggeredTest() {
        AtomicBoolean result = new AtomicBoolean(false);
        team.getCompositeStartRule().getObservableEvaluation().subscribe(bool -> result.set(true));
        team.addStartRule(min1PlayerStartRule);
        team.add(player1);
        assertTrue(result.get());
    }
}
