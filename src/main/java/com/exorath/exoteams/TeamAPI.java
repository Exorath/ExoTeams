package com.exorath.exoteams;

import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;
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

    private Subject<TeamPlayerJoinTeamEvent, TeamPlayerJoinTeamEvent> onPlayerJoinTeam = new SerializedSubject<>(PublishSubject.create());
    private Subject<TeamPlayerLeaveTeamEvent, TeamPlayerLeaveTeamEvent> onPlayerLeaveTeam = new SerializedSubject<>(PublishSubject.create());

    private HashMap<Team, Subscription[]> teamSubscriptions = new HashMap<>();

    public void addTeam(Team team) {
        teams.add(team);
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

    public boolean removeTeam(Team team) {
        if(teamSubscriptions.containsKey(team))
            Arrays.stream(teamSubscriptions.remove(team))
                    .forEach(subscription -> subscription.unsubscribe());
        return teams.remove(team);
    }

    public void clear() {
        teams.clear();
    }

    public Observable getPlayerJoinTeamObservable() {
        return onPlayerJoinTeam;
    }

    public Observable getPlayerLeaveTeamObservable() {
        return onPlayerLeaveTeam;
    }

    public Collection getTeams() {
        return teams;
    }

    public static TeamAPI createAPI() {
        return new TeamAPI();
    }
}
