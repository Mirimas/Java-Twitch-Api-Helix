package ru.mirrobot.twitch.api.resources;

import ru.mirrobot.twitch.api.auth.Scopes;
import ru.mirrobot.twitch.api.handlers.GamesResponseHandler;
import ru.mirrobot.twitch.api.models.Games;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@link GamesResource} provides the functionality
 * to access the <code>/channels</code> endpoints of the Twitch API.
 *
 * @author Anton Kildishev
 */
public class GamesResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    public GamesResource(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Returns a users object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: {@link Scopes#USER_READ_EMAIL}</p>
     *
     * @param handler the response handler
     */
    public void get(final long gameId, final GamesResponseHandler handler) {
        String url = String.format("%s/games?id=%s", getBaseUrl(), gameId);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
					Games value = objectMapper.readValue(content, Games.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
