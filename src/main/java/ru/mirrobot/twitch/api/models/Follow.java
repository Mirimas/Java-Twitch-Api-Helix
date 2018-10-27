package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Follow {

	private Date followedAt;
	private String fromId;
	private String toId;

	public Date getFollowedAt() {
		return followedAt;
	}

	public void setFollowedAt(Date followedAt) {
		this.followedAt = followedAt;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Follow follow = (Follow) o;
		return Objects.equals(followedAt, follow.followedAt) &&
				Objects.equals(fromId, follow.fromId) &&
				Objects.equals(toId, follow.toId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(followedAt, fromId, toId);
	}

	@Override
	public String toString() {
		return "Follow{" +
				"followedAt=" + followedAt +
				", fromId='" + fromId + '\'' +
				", toId='" + toId + '\'' +
				'}';
	}
}
