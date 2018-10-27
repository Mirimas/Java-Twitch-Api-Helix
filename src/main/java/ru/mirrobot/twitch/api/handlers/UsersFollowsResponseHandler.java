package ru.mirrobot.twitch.api.handlers;

import ru.mirrobot.twitch.api.models.UsersFollows;

public interface UsersFollowsResponseHandler extends BaseFailureHandler {

    void onSuccess(UsersFollows channel);

}
