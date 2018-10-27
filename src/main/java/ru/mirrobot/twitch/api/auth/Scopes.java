package ru.mirrobot.twitch.api.auth;

import org.apache.commons.lang3.ArrayUtils;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * When requesting authorization from users, the scope parameter allows you to specify
 * which permissions your app requires. These scopes are ties to the access token you
 * receive upon a successful authorization. Without specifying scopes, your app only has
 * access to basic information about the authenticated user. You may specify any or all
 * of the following scopes.
 */
public enum Scopes {

    CLIPS_EDIT("clips:edit"),
    USER_EDIT("user:edit"),
    USER_READ_EMAIL("user:read:email");

    private String key;

    Scopes(String key) {
        this.key = key;
    }

    /**
     * Combine <code>Scopes</code> into a '+' separated <code>String</code>.
     * This is the required input format for twitch.tv
     *
     * @param scopes <code>Scopes</code> to combine.
     * @return <code>String</code> representing '+' separated list of <code>Scopes</code>
     */
    public static String join(Scopes... scopes) {
        if (ArrayUtils.isEmpty(scopes)) return EMPTY;
        StringBuilder sb = new StringBuilder();
        for (Scopes scope : scopes) {
            sb.append(scope.getKey()).append("+");
        }
        return sb.toString();
    }

    /**
     * Convert the string representation of the Scope to the Enum.
     *
     * @param text Text representation of Enum value
     * @return Enum value that the text represents
     */
    public static Scopes fromString(String text) {
        if (isEmpty(text)) return null;
        for (Scopes b : Scopes.values()) {
            if (text.equalsIgnoreCase(b.key)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Get the identifier that twitch will recognize.
     *
     * @return A <code>String</code> identifier
     */
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }
}
