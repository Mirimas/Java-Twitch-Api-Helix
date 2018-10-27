package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private long id;
    private String login;
    private String displayName;
    private Type type;
    private BroadcasterType broadcasterType;
    private String description;
    private String profileImageUrl;
    private String offlineImageUrl;
    private long viewCount;
    private String email;

    public enum Type {
        STAFF("staff"),
        ADMIN("admin"),
        GLOBAL_MOD("global_mod"),
        EMPTY("");

        Type(String key) {
            this.key = key;
        }

        private final String key;

        @Override
        public String toString() {
            return key;
        }

        @JsonCreator
        public static Type fromString(String key) {
            for (Type type : values()) {
                if(type.key.equals(key)) {
                    return type;
                }
            }
            throw new RuntimeException("Unknown key=" + key);
        }
    }

    public enum BroadcasterType {
		PARTNER("partner"),
		AFFILIATE("affiliate"),
		EMPTY("");

		 BroadcasterType(String key) {
			this.key = key;
		}

		private final String key;

		@Override
		public String toString() {
			return key;
		}

		@JsonCreator
		public static BroadcasterType fromString(String key) {
			for (BroadcasterType type : values()) {
				if(type.key.equals(key)) {
					return type;
				}
			}
			throw new RuntimeException("Unknown key=" + key);
		}
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BroadcasterType getBroadcasterType() {
        return broadcasterType;
    }

    public void setBroadcasterType(BroadcasterType broadcasterType) {
        this.broadcasterType = broadcasterType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getOfflineImageUrl() {
        return offlineImageUrl;
    }

    public void setOfflineImageUrl(String offlineImageUrl) {
        this.offlineImageUrl = offlineImageUrl;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User channel = (User) o;
        return id == channel.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
              ", login='" + login + '\'' +
              ", displayName='" + displayName + '\'' +
              ", type='" + type + '\'' +
              ", broadcasterType='" + broadcasterType + '\'' +
              ", description='" + description + '\'' +
              ", profileImageUrl='" + profileImageUrl + '\'' +
              ", offlineImageUrl='" + offlineImageUrl + '\'' +
              ", viewCount=" + viewCount +
              ", email='" + email + '\'' +
              '}';
    }
}
