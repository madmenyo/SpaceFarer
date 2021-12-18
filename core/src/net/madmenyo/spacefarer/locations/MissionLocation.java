package net.madmenyo.spacefarer.locations;

/**
 * A mission location is a special location visible only when the player has accepted a mission
 * where he needs to go to some place in a system. It's parent can be either a star,
 */
public class MissionLocation extends Location {

    public MissionLocation(Location parent, String name) {
        super(parent, name);
    }
}
