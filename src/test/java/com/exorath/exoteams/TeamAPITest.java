package com.exorath.exoteams;

import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;
import com.exorath.exoteams.startRule.GlobalStartRule;
import com.exorath.exoteams.startRule.StartRule;
import com.exorath.exoteams.teamSelector.DistributeTeamSelector;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

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
        team1 = new Team();
        team2 = new Team();
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

    @Test
    public void getTeamSelectorNotNullByDefaultTest(){
        assertTrue(teamAPI.getTeamSelector() != null);
    }


    @Test
    public void getTeamSelectorInstanceOfDistributeTeamSelectorByDefaultTest(){
        assertTrue(teamAPI.getTeamSelector() instanceof DistributeTeamSelector);
    }



    @Test
    public void getPlayerJoinTeamObservableCalledOnPlayerJoinTest(){
        AtomicReference<TeamPlayer> ref = new AtomicReference<>();
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);

        teamAPI.getPlayerJoinTeamObservable().map(event -> event.getPlayer()).subscribe(p -> ref.set(p));
        teamAPI.addTeam(team);
        teamAPI.onPlayerJoin(player);
        assertEquals(ref.get(), player);
    }

    @Test
    public void getPlayerLeaveTeamObservableCalledOnPlayerLeaveTest(){
        AtomicReference<TeamPlayer> ref = new AtomicReference<>();
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);

        teamAPI.getPlayerLeaveTeamObservable().map(event -> event.getPlayer()).subscribe(p -> ref.set(p));
        teamAPI.addTeam(team);
        teamAPI.onPlayerJoin(player);
        teamAPI.onPlayerLeave(player);
        assertEquals(ref.get(), player);
    }

    @Test
    public void getPlayerLeaveTeamObservableNotCalledIfPlayerNotAdded(){
        AtomicReference<TeamPlayer> ref = new AtomicReference<>();
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);

        teamAPI.getPlayerLeaveTeamObservable().map(event -> event.getPlayer()).subscribe(p -> ref.set(p));
        teamAPI.addTeam(team);
        teamAPI.onPlayerLeave(player);
        assertEquals(ref.get(), null);
    }

    @Test
    public void getPlayerLeaveTeamNotCalledIfPlayerNotLeft(){
        AtomicReference<TeamPlayer> ref = new AtomicReference<>();
        Team team = new Team();
        TeamPlayer player = mock(TeamPlayer.class);

        teamAPI.getPlayerLeaveTeamObservable().map(event -> event.getPlayer()).subscribe(p -> ref.set(p));
        teamAPI.addTeam(team);
        teamAPI.onPlayerJoin(player);
        assertEquals(ref.get(), null);
    }

    @Test
    public void test(){
        GlobalStartRule startRule = mock(GlobalStartRule.class);
        teamAPI.addStartRule(startRule);
        teamAPI.removeStartRule(startRule);
    }
}
