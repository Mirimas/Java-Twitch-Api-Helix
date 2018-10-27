package ru.mirrobot.twitch.api.handlers;

import ru.mirrobot.twitch.api.models.Streams;

public interface StreamsResponseHandler extends BaseFailureHandler {

    void onSuccess(Streams streams);

}
