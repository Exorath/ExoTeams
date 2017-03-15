package com.exorath.exoteams;

import com.exorath.exorules.rule.CompositeRule;
import com.exorath.exorules.rule.Rule;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;
import com.exorath.exoteams.startRule.GlobalStartRule;
import com.exorath.exoteams.teamSelector.AlreadyInTeamException;
import com.exorath.exoteams.teamSelector.DistributeTeamSelector;
import com.exorath.exoteams.teamSelector.TeamSelector;
import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import java.util.*;

/**
 * Created by toonsev on 7/24/2016.
 */
public class TeamAPI {
    private List<Team> teams = new ArrayList<>();

    private TeamSelector teamSelector = new DistributeTeamSelector();

    private Subject<TeamPlayerJoinTeamEvent, TeamPlayerJoinTeamEvent> onPlayerJoinTeam = new SerializedSubject<>(PublishSubject.create());
    private Subject<TeamPlayerLeaveTeamEvent, TeamPlayerLeaveTeamEvent> onPlayerLeaveTeam = new SerializedSubject<>(PublishSubject.create());

    private HashMap<Team, Subscription[]> teamSubscriptions = new HashMap<>();
    private CompositeRule<GlobalStartRule> globalStartRule = new CompositeRule();

    /**
     * Adds a team to this teamAPI, also subscribes to it's observables.
     *
     * @param team team to add to this teamAPI
     */
    public void addTeam(Team team) {
        teams.add(team);

        if (team.getOnPlayerJoinTeamObservable() == null || team.getOnPlayerLeaveTeamObservable() == null)
            throw new NullPointerException("team.getOnPlayerJoinTeamObservable() and team.getOnPlayerLeaveTeamObservable() cannot be null.");
        //Map the observable player joins and player leaves to events and relay them to the observables in the TeamAPI.
        Subscription joinSubscription = team.getOnPlayerJoinTeamObservable()
                .map(player -> new TeamPlayerJoinTeamEvent(teams, team, player))
                .subscribe(event -> onPlayerJoinTeam.onNext(event));
        Subscription leaveSubscription = team.getOnPlayerLeaveTeamObservable()
                .map(player -> new TeamPlayerLeaveTeamEvent(teams, team, player))
                .subscribe(event -> onPlayerLeaveTeam.onNext(event));
        //Save the subscriptions so they can later be removed
        teamSubscriptions.put(team, new Subscription[]{joinSubscription, leaveSubscription});
    }

    /**
     * Removes a team from this TeamAPI. Returns true if this teamAPI contained the team.
     *
     * @param team team to remove
     * @return true if this teamAPI contained the team
     */
    public boolean removeTeam(Team team) {

        if (teamSubscriptions.containsKey(team))
            Arrays.stream(teamSubscriptions.remove(team))
                    .forEach(subscription -> subscription.unsubscribe());
        return teams.remove(team);
    }

    /**
     * Clears all registered teams.
     */
    public void clear() {
        teams.clear();
    }

    /**
     * The API consumer should call this whenever a player joins. Returns whether or not the team player was ad
     *
     * @param teamPlayer
     * @return
     */
    public Team onPlayerJoin(TeamPlayer teamPlayer) {
        Team toJoin = null;
        try {
            toJoin = teamSelector.getTeam(teams, teamPlayer);
        } catch (AlreadyInTeamException e) {
            e.printStackTrace();
            return null;
        }
        if (toJoin != null)
            toJoin.add(teamPlayer);
        return toJoin;
    }


    /**
     * The API consumer should call this whenever a player leaves. It will remove the player from the TeamAPI.
     * @param teamPlayer
     */
    public void onPlayerLeave(TeamPlayer teamPlayer) {
        for (Team team : teams) {
            if (team.getPlayers().contains(teamPlayer)) {
                team.remove(teamPlayer);
                return;
            }
        }
    }

    /**
     * The returned observable emits an event whenever the {@link TeamAPI#onPlayerJoin) is called (and the player succesfully joins a team).
     *
     * @return an observable for the {@link TeamPlayerJoinTeamEvent} events.
     */
    public Observable<TeamPlayerJoinTeamEvent> getPlayerJoinTeamObservable() {
        return onPlayerJoinTeam;
    }

    /**
     * The returned observable emits an event whenever the {@link TeamAPI#onPlayerLeave) is called (and the player was in a team).
     *
     * @return an observable for the {@link TeamPlayerJoinTeamEvent} events.
     */
    public Observable<TeamPlayerLeaveTeamEvent> getPlayerLeaveTeamObservable() {
        return onPlayerLeaveTeam;
    }

    /**
     * Returns the registered teams.
     *
     * @return the registered teams
     */
    public Collection getTeams() {
        return teams;
    }

    /**
     * Gets a new instance of the API.
     *
     * @return a new instance of the API
     */
    public static TeamAPI createAPI() {
        return new TeamAPI();
    }

    /**
     * Returns the teamSelector assigned to this teamAPI. By default this is am  {@link DistributeTeamSelector}.
     *
     * @return the teamSelector assigned to this teamAPI.
     */
    public TeamSelector getTeamSelector() {
        return teamSelector;
    }

    public Rule getGlobalStartRule() {
        return globalStartRule;
    }

    public void addStartRule(GlobalStartRule startRule) {
        globalStartRule.addRule(startRule);
    }

    public boolean removeStartRule(GlobalStartRule startRule) {
        return globalStartRule.removeRule(startRule);
    }
}
