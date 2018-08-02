package johneagle.routesolve.domain;

/**
 * Storage for configuration of the Path finding algorithm.
 * Tells which types of ascii characters it can move and which not.
 * Also how tall and width the maps are suppose to be.
 *
 * @author Johneagle
 */
public class Config {
    private int x;
    private int y;
    private char[] walkable;
    private char[] unwalkable;

    public Config(int x, int y, char[] walkable, char[] unwalkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.unwalkable = unwalkable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char[] getWalkable() {
        return walkable;
    }

    public char[] getUnwalkable() {
        return unwalkable;
    }
}
