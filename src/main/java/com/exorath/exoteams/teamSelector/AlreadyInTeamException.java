package com.exorath.exoteams.teamSelector;

import com.exorath.exoteams.ExoTeamsException;

/**
 * Created by toonsev on 3/15/2017.
 */
public class AlreadyInTeamException extends ExoTeamsException {
    public AlreadyInTeamException(String message){
        super(message);
    }

    public AlreadyInTeamException() {
    }
}
