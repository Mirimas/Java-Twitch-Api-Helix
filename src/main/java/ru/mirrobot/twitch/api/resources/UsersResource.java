package ru.mirrobot.twitch.api.resources;

import ru.mirrobot.twitch.api.auth.Scopes;
import ru.mirrobot.twitch.api.handlers.UsersResponseHandler;
import ru.mirrobot.twitch.api.models.Users;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@link UsersResource} provides the functionality
 * to access the <code>/channels</code> endpoints of the Twitch API.
 *
 * @author Anton Kildishev
 */
public class UsersResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    public UsersResource(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Returns a users object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: {@link Scopes#USER_READ_EMAIL}</p>
     *
     * @param handler the response handler
     */
    public void get(final UsersResponseHandler handler) {
        String url = String.format("%s/users", getBaseUrl());

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Users value = objectMapper.readValue(content, Users.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

	/**
	 * Returns a users object of authenticated user. Channel object includes stream key.
	 * <p>Authenticated, required scope: {@link Scopes#USER_READ_EMAIL}</p>
	 *
	 * @param handler the response handler
	 */
	public void get(final String login, final UsersResponseHandler handler) {
		String url = String.format("%s/users?login=%s", getBaseUrl(), login);

		http.get(url, new TwitchHttpResponseHandler(handler) {
			@Override
			public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
				try {
					Users value = objectMapper.readValue(content, Users.class);
					handler.onSuccess(value);
				} catch (IOException e) {
					handler.onFailure(e);
				}
			}
		});
	}
}
