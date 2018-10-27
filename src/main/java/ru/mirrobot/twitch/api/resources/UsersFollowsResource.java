package ru.mirrobot.twitch.api.resources;

import ru.mirrobot.twitch.api.handlers.UsersFollowsResponseHandler;
import ru.mirrobot.twitch.api.models.UsersFollows;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@link UsersFollowsResource} provides the functionality
 * to access the <code>/channels</code> endpoints of the Twitch API.
 *
 * @author Anton Kildishev
 */
public class UsersFollowsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    public UsersFollowsResource(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Returns a users object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: none</p>
     *
     * @param handler the response handler
     */
    public void get(final long userId, final UsersFollowsResponseHandler handler) {
        String url = String.format("%s/users/follows?to_id=%s", getBaseUrl(), Long.toString(userId));

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UsersFollows usersFollows = objectMapper.readValue(content, UsersFollows.class);
                    handler.onSuccess(usersFollows);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    public void get(final long userId, final int first, final UsersFollowsResponseHandler handler) {
        String url = String.format("%s/users/follows?to_id=%s&first=%s", getBaseUrl(), Long.toString(userId),
              Integer.toString(first));

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UsersFollows value = objectMapper.readValue(content, UsersFollows.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
