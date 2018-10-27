package ru.mirrobot.twitch.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mb3364.http.AsyncHttpClient;
import com.mb3364.http.StringHttpResponseHandler;
import ru.mirrobot.twitch.api.handlers.BaseFailureHandler;
import ru.mirrobot.twitch.api.models.Error;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * AbstractResource is the abstract base class of a Twitch resource.
 * A resource provides the functionality to access the REST endpoints of the Twitch API.
 *
 * @author Anton Kildishev
 */
public abstract class AbstractResource {

    protected static final ObjectMapper objectMapper = new ObjectMapper(); // can reuse
    protected static final AsyncHttpClient http = new AsyncHttpClient(); // can reuse
    private final String baseUrl; // Base url for twitch rest api

    /**
     * Construct a resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    protected AbstractResource(String baseUrl) {
        this.baseUrl = baseUrl;
        configureObjectMapper();
    }

    /**
     * Configure the Jackson JSON {@link ObjectMapper} to properly parse the API responses.
     */
    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    /**
     * Sets the authentication access token to be included in the HTTP headers of each
     * API request.
     *
     * @param accessToken the user's authentication access token
     */
    public void setAuthAccessToken(String accessToken) {
        if (isNotEmpty(accessToken)) {
            http.setHeader("Authorization", String.format("Bearer %s", accessToken));
        } else {
            http.removeHeader("Authorization");
        }
    }

    /**
     * Sets the application's client ID to be included in the HTTP headers of each API request.
     *
     * @param clientId the application's client ID
     */
    public void setClientId(String clientId) {
        if (isNotEmpty(clientId)) {
            http.setHeader("Client-ID", clientId);
        } else {
            http.removeHeader("Client-ID");
        }
    }

    /**
     * Get the base URL to the Twitch API. Intended to be called by subclasses when generating
     * their resource URL endpoint.
     *
     * @return the base URL to the Twitch API
     */
    protected String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Handles HTTP response's from the Twitch API.
     * <p>Since all Http failure logic is the same, we handle it all in one place: here.</p>
     */
    protected static abstract class TwitchHttpResponseHandler extends StringHttpResponseHandler {
        private BaseFailureHandler apiHandler;

        public TwitchHttpResponseHandler(BaseFailureHandler apiHandler) {
            this.apiHandler = apiHandler;
        }

        @Override
        public abstract void onSuccess(int statusCode, Map<String, List<String>> headers, String content);

        @Override
        public void onFailure(int statusCode, Map<String, List<String>> headers, String content) {
            try {
                if (content.length() > 0) {
                    Error error = objectMapper.readValue(content, Error.class);
                    apiHandler.onFailure(statusCode, error.getStatusText(), error.getMessage());
                } else {
                    apiHandler.onFailure(statusCode, "", "");
                }
            } catch (IOException e) {
                apiHandler.onFailure(e);
            }
        }

        @Override
        public void onFailure(Throwable throwable) {
            apiHandler.onFailure(throwable);
        }
    }
}
