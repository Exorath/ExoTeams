package com.exorath.exoteams;

import com.exorath.exoproperties.Properties;
import com.exorath.exoproperties.Propertiesable;
import com.exorath.exorules.rule.CompositeRule;
import com.exorath.exorules.rule.Rule;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.startRule.StartRule;
import com.exorath.exoteams.startRule.TeamStartRule;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by toonsev on 7/23/2016.
 */
public class Team implements Propertiesable{
    private List<TeamPlayer> players = new ArrayList<>();
    private Properties properties = new Properties();

    private CompositeRule compositeRule = new CompositeRule();
    private List<TeamStartRule> startRules = new ArrayList<>();

    private Subject<TeamPlayer, TeamPlayer> onPlayerJoinTeam = new SerializedSubject<>(PublishSubject.create());
    private Subject<TeamPlayer, TeamPlayer> onPlayerLeaveTeam = new SerializedSubject<>(PublishSubject.create());

    public void add(TeamPlayer teamPlayer) {
        players.add(teamPlayer);
        onPlayerJoinTeam.onNext(teamPlayer);
        startRules.forEach(startRule -> startRule.onPlayerJoinTeam(this, teamPlayer));
    }
    public void remove(TeamPlayer teamPlayer) {
        players.remove(teamPlayer);
        onPlayerLeaveTeam.onNext(teamPlayer);
        startRules.forEach(startRule -> startRule.onPlayerLeaveTeam(this, teamPlayer));
    }

    public Observable<TeamPlayer> getOnPlayerJoinTeamObservable() {
        return onPlayerJoinTeam;
    }

    public Observable<TeamPlayer> getOnPlayerLeaveTeamObservable() {
        return onPlayerLeaveTeam;
    }

    public Collection<TeamPlayer> getPlayers() {
        return players;
    }

    public void clear() {
        players.clear();
    }

    public Properties getProperties() {
        return properties;
    }

    public Rule getCompositeStartRule() {
        return compositeRule;
    }

    public void addStartRule(TeamStartRule startRule) {
        compositeRule.addRule(startRule);
        startRules.add(startRule);
    }
    public boolean removeStartRule(TeamStartRule startRule) {
        compositeRule.removeRule(startRule);
        return startRules.remove(startRule);
    }
}
