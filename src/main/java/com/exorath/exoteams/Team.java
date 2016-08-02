package com.exorath.exoteams;

import com.exorath.exoproperties.Properties;
import com.exorath.exoteams.player.TeamPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by toonsev on 7/23/2016.
 */
public class Team {
    private List<TeamPlayer> players = new ArrayList<>();
    private Properties properties = new Properties();

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

}
