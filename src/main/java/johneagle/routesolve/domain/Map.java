package johneagle.routesolve.domain;

/**
 * Object to handle ascii map and any straightly related actions.
 * Uses configurations given to it to know about ascii types purposes.
 * Also gives manhattan distance estimation between coordinates and simple has to represent matrix as a line.
 *
 * @see Config
 *
 * @author Johneagle
 */
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

    /**
     * Gives a linear place of the coordinate in matrix.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return Integer
     */
    public int hash(int x, int y) {
        return y * this.properties.getX() + x;
    }

    public int getMapHeight() {
        return this.properties.getY();
    }

    public int getMapWeight() {
        return this.properties.getX();
    }

    /**
     * Function tells if given coordinates are inside the ascii grid.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return boolean
     */
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

    /**
     * Tells if the place in the grid is walkable according to configurations.
     * IF you can bass it return is 1, if you cant it's -1 and those that can't be recognize are 0.
     *
     * @see Config#getWalkable()
     * @see Config#getUnwalkable()
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return Integer
     */
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

    /**
     * Current only returns 1 but suppose to give cost what it takes to move to the given coordinates according to its type.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return Integer
     */
    public Integer getValue(int x, int y) {
        char type = this.map[y][x];

        return 1;
    }

    /**
     * Returns manhattan distance between two coordinates in the ascii map.
     * Uses absolute value (abs) from Math to make code more compact.
     *
     * @see Math#abs(int)
     *
     * @param fristX    Start points x-coordinate.
     * @param secondX   Start points y-coordinate.
     * @param fristY    End points x-coordinate.
     * @param secondY   End points y-coordinate.
     *
     * @return Integer
     */
    public Integer getAproxDistance(int fristX, int secondX, int fristY, int secondY) {
        int diffirentX = fristX - secondX;
        int diffirentY = fristY - secondY;
        int result = Math.abs(diffirentX) + Math.abs(diffirentY);

        return result;
    }
}
