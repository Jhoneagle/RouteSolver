package johneagle.routesolve.domain;

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
