package ru.alfabank.common;

/**
 * Created by Alex on 02.02.2017.
 */
public class Dog {

    private int legCount;

    private boolean hasTail;

    public Dog(int legCount, boolean hasTail) {

        this.legCount = legCount;
        this.hasTail = hasTail;
    }

    public int getLegCount() {

        return legCount;
    }

    public void setLegCount(int legCount) {

        this.legCount = legCount;
    }

    public boolean isHasTail() {

        return hasTail;
    }

    public void setHasTail(boolean hasTail) {

        this.hasTail = hasTail;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (hasTail != dog.hasTail) return false;
        if (legCount != dog.legCount) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = legCount;
        result = 31 * result + (hasTail ? 1 : 0);
        return result;
    }
}
