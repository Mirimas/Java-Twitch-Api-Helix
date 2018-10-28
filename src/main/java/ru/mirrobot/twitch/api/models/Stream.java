package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stream {

	private long id;
	private long userId;
	private long gameId;
	private List<String> communityIds;
	private Type type;
	private String	title;
	private int viewerCount;
	private Date startedAt;
	private String language;
	private String	thumbnailUrl;

	public enum Type {
		ALL("all"),
		LIVE("live"),
		VODCAST("vodcast");

		private final String key;

		Type(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return key;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public List<String> getCommunityIds() {
		return communityIds;
	}

	public void setCommunityIds(List<String> communityIds) {
		this.communityIds = communityIds;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViewerCount() {
		return viewerCount;
	}

	public void setViewerCount(int viewerCount) {
		this.viewerCount = viewerCount;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stream stream = (Stream) o;
		return id == stream.id &&
				userId == stream.userId &&
				gameId == stream.gameId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, gameId);
	}

	@Override
	public String toString() {
		return "Stream{" +
				"id=" + id +
				", userId=" + userId +
				", gameId=" + gameId +
				", communityIds=" + communityIds +
				", type='" + type + '\'' +
				", title='" + title + '\'' +
				", viewerCount=" + viewerCount +
				", startedAt=" + startedAt +
				", language='" + language + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				'}';
	}
}
