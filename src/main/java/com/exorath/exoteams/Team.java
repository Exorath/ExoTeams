package com.exorath.exoteams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by toonsev on 7/23/2016.
 */
public class Team {
    private List<TeamPlayer> players = new ArrayList<>();
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

    public Properties getMetaData() {
        return null;
    }
}
