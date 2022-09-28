package com.thompete.programon.util;

import java.util.Objects;

public class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Pair other = (Pair) object;
        return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
