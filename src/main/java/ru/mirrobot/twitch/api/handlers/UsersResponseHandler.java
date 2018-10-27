package ru.mirrobot.twitch.api.handlers;

import ru.mirrobot.twitch.api.models.Users;

public interface UsersResponseHandler extends BaseFailureHandler {

    void onSuccess(Users channel);

}
