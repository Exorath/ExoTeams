package com.exorath.exoteams;

import com.exorath.exoproperties.Properties;
import com.exorath.exoproperties.Propertiesable;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.exoteams.startRule.StartRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by toonsev on 7/23/2016.
 */
public class Team implements Propertiesable{
    private List<TeamPlayer> players = new ArrayList<>();
    private Properties properties = new Properties();
    private List<StartRule> startRules = new ArrayList<>();

    public void add(TeamPlayer teamPlayer) {
        players.add(teamPlayer);
    }
    public void remove(TeamPlayer teamPlayer) {
        players.remove(teamPlayer);
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

    public Collection<StartRule> getStartRules() {
        return startRules;
    }
}
