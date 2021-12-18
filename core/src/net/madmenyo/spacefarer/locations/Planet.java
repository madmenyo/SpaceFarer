package net.madmenyo.spacefarer.locations;

import java.util.ArrayList;
import java.util.List;

public class Planet extends Location implements Comparable<Planet> {

    /** Planets have a number based on distance from the sun compared to other planets in the system **/
    private int number;

    public Planet(Location parent, String name, int number) {
        super(parent, name);
        this.number = number;
    }

    public Planet(Star parent, String name, int number) {
        super(parent, name);
        this.number = number;
    }

    @Override
    public String toString() {
        return "Planet [" +  parent.name + " | " + number + "]";
    }

    @Override
    public int compareTo(Planet o) {
        if (this.number > o.number) return +1;
        if (this.number < o.number) return -1;
        if (this.number == o.number) {
            throw new IllegalArgumentException("Planets in system [" + parent.name + "] have share same numbers [" + number + "]");
        }
        return 0;
    }
}
