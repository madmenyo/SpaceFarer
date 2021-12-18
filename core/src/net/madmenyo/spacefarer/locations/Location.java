package net.madmenyo.spacefarer.locations;

public abstract class Location {
    protected Location parent;
    protected String name;

    /** A local parallax **/
    protected String parallaxBack;

    /** Fixed image **/
    protected String image;

    /** Parallax in front of player **/
    protected String parallaxFront;


    public Location(Location parent, String name) {
        this.parent = parent;
        this.name = name;
    }
}
