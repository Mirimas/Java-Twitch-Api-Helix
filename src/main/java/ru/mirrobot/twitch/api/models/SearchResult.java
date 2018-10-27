package ru.mirrobot.twitch.api.models;

import java.util.List;
import java.util.Objects;

public class SearchResult<T> {

    private int total;
    private List<T> data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult<?> that = (SearchResult<?>) o;
        return total == that.total &&
              Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, data);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
