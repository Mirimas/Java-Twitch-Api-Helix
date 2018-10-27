package ru.mirrobot.twitch.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

	private long id;
	private String name;
	private String boxArtUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBoxArtUrl() {
		return boxArtUrl;
	}

	public void setBoxArtUrl(String boxArtUrl) {
		this.boxArtUrl = boxArtUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return id == game.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
