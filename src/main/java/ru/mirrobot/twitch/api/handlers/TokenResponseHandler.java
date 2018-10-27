package ru.mirrobot.twitch.api.handlers;

import ru.mirrobot.twitch.api.models.Token;

public interface TokenResponseHandler extends BaseFailureHandler {

    void onSuccess(Token token);

}
