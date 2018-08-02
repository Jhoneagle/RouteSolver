package johneagle.routesolve.domain;

public class Map {
    private char[][] map;
    private Config properties;

    public Map() {
        this.map = null;
        this.properties = null;
    }

    public char[][] getMap() {
        return map;
    }

    public Config getProperties() {
        return properties;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void setProperties(Config properties) {
        this.properties = properties;
    }

    public int hash(int x, int y) {
        return y * this.properties.getX() + x;
    }

    public int getMapHeight() {
        return this.properties.getY();
    }

    public int getMapWeight() {
        return this.properties.getX();
    }

    public boolean isInsideMap(int x, int y) {
        int mapHeight = this.properties.getY();
        int mapWeight = this.properties.getX();

        if (x >= 0 && x < mapWeight) {
            if (y >= 0 && y < mapHeight) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Integer isWalkable(int x, int y) {
        char type = this.map[y][x];
        char[] walkable = this.properties.getWalkable();
        char[] unwalkable = this.properties.getUnwalkable();

        for (int i = 0; i < walkable.length; i++) {
            if (type == walkable[i]) {
                return 1;
            }
        }

        for (int i = 0; i < unwalkable.length; i++) {
            if (type == unwalkable[i]) {
                return -1;
            }
        }

        return 0;
    }

    public Integer getValue(int x, int y) {
        char type = this.map[y][x];

        return 1;
    }

    public Integer getAproxDistance(int fristX, int secondX, int fristY, int secondY) {
        return Math.abs(fristX - secondX) + Math.abs(fristY - secondY);
    }
}
