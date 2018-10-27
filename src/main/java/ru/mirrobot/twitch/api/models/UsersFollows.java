package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersFollows extends SearchResult<Follow> {

}
