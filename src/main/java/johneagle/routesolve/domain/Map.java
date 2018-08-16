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
     * @return {@code true} if cordinates are inside the map
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
     * Method returns cost of moving from one block to another next to it. Currently in straight this means one and in diagonally it means square two.
     * All other coordinate pairs result in returning zero.
     *
     * @param fromX     x-coordinate came
     * @param fromY     y-coordinate came
     * @param whereX     x-coordinate go
     * @param whereY     y-coordinate go
     *
     * @return Integer
     */
    public double getValueForMovement(int fromX, int fromY, int whereX, int whereY) {
        if (fromX == whereX + 1 && fromY == whereY + 1) {
            return Math.sqrt(2);
        } else if (fromX == whereX + 1 && fromY == whereY - 1) {
            return Math.sqrt(2);
        } else if (fromX == whereX - 1 && fromY == whereY + 1) {
            return Math.sqrt(2);
        } else if (fromX == whereX - 1 && fromY == whereY - 1) {
            return Math.sqrt(2);
        }

        if (fromX == whereX && fromY == whereY + 1) {
            return 1;
        } else if (fromX == whereX && fromY == whereY - 1) {
            return 1;
        } else if (fromX == whereX - 1 && fromY == whereY) {
            return 1;
        } else if (fromX == whereX + 1 && fromY == whereY) {
            return 1;
        }

        return 0;
    }

    /**
     * Returns octile distance between two coordinates in the ascii map.
     *
     * @see Math#max(int, int)
     * @see Math#min(int, int)
     * @see Math#abs(int)
     * @see Math#sqrt(double)
     *
     * @param fristX    Start points x-coordinate.
     * @param secondX   Start points y-coordinate.
     * @param fristY    End points x-coordinate.
     * @param secondY   End points y-coordinate.
     *
     * @return Double
     */
    public double getAproxDistance(int fristX, int secondX, int fristY, int secondY) {
        int diffirentX = Math.abs(fristX - secondX);
        int diffirentY = Math.abs(fristY - secondY);
        double result = (double) Math.max(diffirentX, diffirentY) + (Math.sqrt(2) - 1) * Math.min(diffirentX, diffirentY);

        return result;
    }
}
