package johneagle.routesolve.domain;

/**
 * Object to handle ascii map and any straightly related actions.
 * Uses configurations given to it to know about ascii types purposes.
 * Also gives manhattan distance estimation between coordinates and simple has to represent matrix as a line.
 *
 * @author Johneagle
 */
public class Map {
    private boolean[][] map;

    public Map() {
        map = null;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
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
        return y * map[0].length + x;
    }

    public int getMapHeight() {
        if (map != null) {
            return map.length;
        } else {
            return 0;
        }
    }

    public int getMapWeight() {
        if (map != null) {
            return map[0].length;
        } else {
            return 0;
        }
    }

    /**
     * Function tells if given coordinates are inside the ascii grid.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return {@code true} if coordinates are inside the map
     */
    public boolean isInsideMap(int x, int y) {
        int mapHeight = getMapHeight();
        int mapWeight = getMapWeight();

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
     * Tells if the place in the grid is walkable.
     *
     * @param x     x-coordinate
     * @param y     y-coordinate
     *
     * @return {@code true} if place is walkable
     */
    public boolean isWalkable(int x, int y) {
        return map[y][x];
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
        int dx = fromX - whereX;
        int dy = fromY - whereY;

        if (dx <= 1 && dx >= -1 && dy <= 1 && dy >= -1) {
            if (dx != 0 && dy != 0) {
                return Math.sqrt(2);
            }

            if (dx != 0 || dy != 0) {
                return 1;
            }
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
