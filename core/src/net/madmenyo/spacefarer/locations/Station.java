package net.madmenyo.spacefarer.locations;

public class Station extends Location {

    /** Stations have names, unlike other locations.
     *  These names could be based on the owner or function of the station **/
    private String stationName;

    public Station(Location parent, String name) {
        super(parent, name);
    }
}
