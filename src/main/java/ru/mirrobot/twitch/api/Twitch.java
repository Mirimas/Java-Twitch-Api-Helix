package ru.mirrobot.twitch.api;

import ru.mirrobot.twitch.api.auth.Authenticator;
import ru.mirrobot.twitch.api.resources.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ru.mirrobot.twitch.api.resources.ResourceType.*;

/**
 * Enables the ability to interact with the Twitch.tv REST API.
 *
 * @see <a href="http://dev.twitch.tv/docs/api/reference">http://dev.twitch.tv/docs/api/reference</a>
 * @author Anton Kildishev
 */
public class Twitch {

    private static final String DEFAULT_BASE_URL = "https://api.twitch.tv/helix";
    private String clientId; // User's app client Id
    private Authenticator authenticator;
    private Map<ResourceType, AbstractResource> resources;

    /**
     * Constructs a Twitch application instance with a set API base URL and API version number.
     *
     * @param baseUrl    the base URL of the Twitch API
     */
    public Twitch(String baseUrl) {
        authenticator = new Authenticator(baseUrl);
        // Instantiate resource connectors
        resources = new HashMap<>();
        Arrays.stream(ResourceType.values()).forEach(resourceType ->
                resources.put(resourceType, resourceType.createResource(baseUrl)));
    }

    /**
     * Constructs a Twitch application instance.
     */
    public Twitch() {
        this(DEFAULT_BASE_URL);
    }

    /**
     * Get the set Twitch client ID.
     *
     * @return The Twitch client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Set the Twitch client ID. Register your application on Twitch.tv to retrieve
     * a client ID.
     * <p>Passed to authorization endpoints to identify your application.</p>
     *
     * @param clientId Twitch client ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
        // Update client id in all resources
        for (AbstractResource resource : resources.values()) {
            resource.setClientId(clientId);
        }
    }

    private AbstractResource getResource(ResourceType key) {
        AbstractResource resource = resources.get(key);
        resource.setAuthAccessToken(authenticator.getAccessToken());
        return resource;
    }

    /**
     * Get the authenticator object. The authenticator object allows a user to
     * authenticate with the Twitch.tv servers.
     *
     * @return the authenticator object
     * @see Authenticator
     */
    public Authenticator auth() {
        return authenticator;
    }

    /**
     * Get the {@link UsersResource} object. The {@link UsersResource} provides
     * the functionality to access the <code>/users</code> endpoints of the Twitch API.
     *
     * @return the {@link UsersResource} object
     * @see UsersResource
     * @see RootResource
     */
    public UsersResource users() {
        return (UsersResource) getResource(USERS);
    }

    /**
     * Get the {@link UsersFollowsResource} object. The {@link UsersFollowsResource} provides
     * the functionality to access the <code>/users</code> endpoints of the Twitch API.
     *
     * @return the {@link UsersFollowsResource} object
     * @see UsersFollowsResource
     * @see RootResource
     */
    public UsersFollowsResource usersFollows() {
        return (UsersFollowsResource) getResource(USERS_FOLLOWERS);
    }

    /**
     * Get the {@link StreamsResource} object. The {@link StreamsResource} provides
     * the functionality to access the <code>/streams</code> endpoints of the Twitch API.
     *
     * @return the {@link StreamsResource} object
     * @see StreamsResource
     * @see RootResource
     */
    public StreamsResource streams() {
        return (StreamsResource) getResource(STREAMS);
    }

    /**
     * Get the {@link GamesResource} object. The {@link GamesResource} provides
     * the functionality to access the <code>/streams</code> endpoints of the Twitch API.
     *
     * @return the {@link GamesResource} object
     * @see GamesResource
     * @see RootResource
     */
    public GamesResource games() {
        return (GamesResource) getResource(GAMES);
    }
}
