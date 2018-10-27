package ru.mirrobot.twitch.api.resources;

import ru.mirrobot.twitch.api.handlers.StreamsResponseHandler;
import ru.mirrobot.twitch.api.models.Streams;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@link StreamsResource} provides the functionality
 * to access the <code>/channels</code> endpoints of the Twitch API.
 *
 * @author Anton Kildishev
 */
public class StreamsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    public StreamsResource(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Returns a channel object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: none</p>
     *
     * @param handler the response handler
     */
    public void get(final long userId, final StreamsResponseHandler handler) {
        String url = String.format("%s/streams?user_id=%s", getBaseUrl(), Long.toString(userId));

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Streams value = objectMapper.readValue(content, Streams.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a channel object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: none</p>
     *
     * @param handler the response handler
     */
    public void get(final String user, final StreamsResponseHandler handler) {
        String url = String.format("%s/streams?user_login=%s", getBaseUrl(), user);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Streams value = objectMapper.readValue(content, Streams.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
