package ru.mirrobot.twitch.api.auth.grants.implicit;

/**
 * The <code>AuthenticationException</code> class represents an error during
 * authentication with <a href="http://twitch.tv">http://www.twitch.tv</a>.
 */
public class AuthenticationException extends Exception {
    private final String description; // the description of the error

    public AuthenticationException(String message, String description) {
        super(message);
        this.description = description;
    }

    public AuthenticationException(String message, String description, Throwable cause) {
        super(message, cause);
        this.description = description;
    }

    /**
     * Get the description of the error.
     *
     * @return the description of the error.
     */
    public String getDescription() {
        return description;
    }
}
