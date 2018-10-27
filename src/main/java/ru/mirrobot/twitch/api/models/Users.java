package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends SearchResult<User> {

}
