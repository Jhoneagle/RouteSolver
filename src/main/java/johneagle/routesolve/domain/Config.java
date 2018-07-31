package johneagle.routesolve.domain;

public class Config {
    private int x;
    private int y;
    private String[] walkable;
    private String[] unwalkable;

    public Config(int x, int y, String[] walkable, String[] unwalkable) {
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

    public String[] getWalkable() {
        return walkable;
    }

    public String[] getUnwalkable() {
        return unwalkable;
    }
}
