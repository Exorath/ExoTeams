package com.exorath.exoteams.teamSelector;


import com.exorath.exoteams.Team;
import org.junit.Test;

/**
 * Created by toonsev on 8/11/2016.
 */
public class SelectorTest {
    @Test
    public void test(){
        TeamSelector selector = (teams, player) -> {
            Team lowest = null;
            for (Team team : teams)
                if (lowest == null || lowest.getPlayers().size() > team.getPlayers().size())
                    lowest = team;
            return lowest;
        };
    }
}
