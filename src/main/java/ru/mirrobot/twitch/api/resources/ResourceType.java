package ru.mirrobot.twitch.api.resources;

/**
 * Resource types with ability to create new Resource
 *
 * @author Anton Kildishev
 */
public enum ResourceType {

    USERS {
        @Override
        public AbstractResource createResource(String baseUrl) {
            return new UsersResource(baseUrl);
        }
    },

    USERS_FOLLOWERS {
        @Override
        public AbstractResource createResource(String baseUrl) {
            return new UsersFollowsResource(baseUrl);
        }
    },

    STREAMS {
        @Override
        public AbstractResource createResource(String baseUrl) {
            return new StreamsResource(baseUrl);
        }
    },

    GAMES {
        @Override
        public AbstractResource createResource(String baseUrl) {
            return new GamesResource(baseUrl);
        }
    };

    public abstract AbstractResource createResource(String baseUrl);
}
