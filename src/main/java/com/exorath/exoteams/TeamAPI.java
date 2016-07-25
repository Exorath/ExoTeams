package com.exorath.exoteams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by toonsev on 7/24/2016.
 */
public class TeamAPI {
    private List<Team> teams = new ArrayList<>();

    public static TeamAPI createAPI(){
        return new TeamAPI();
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public void clear(){
        teams.clear();
    }

    public Collection getTeams() {
        return teams;
    }
}
