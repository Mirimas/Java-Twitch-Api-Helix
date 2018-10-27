package ru.mirrobot.twitch.api.handlers;

import ru.mirrobot.twitch.api.models.Games;

public interface GamesResponseHandler extends BaseFailureHandler {

	void onSuccess(Games channel);

}
