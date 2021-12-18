package net.madmenyo.spacefarer.locations;

public class Star extends Location {

    /**
     * AThe main star does not have a planet
     * @param name
     */
    public Star(String name) {
        super(null, name);
    }

    /**
     * In a multi star system the largest star is the root
     */
    public Star(Location parent, String name) {
        super(parent, name);
    }
}
