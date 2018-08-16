package johneagle.routesolve.domain;

/**
 * Basic block used by the algorithm to represent each coordinate in matrix as a valued point.
 * Also supports knowledge if been visited and how far it is or estimated to be from start and end point.
 * And also can be sorted according to distances sum.
 *
 * @author Johneagle
 */
public class Chell implements Comparable<Chell> {
    private int x;
    private int y;
    private double distanceToStart;
    private double distanceToEnd;
    private boolean visited;

    public Chell(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistanceToStart() {
        return distanceToStart;
    }

    public void setDistanceToStart(double distanceToStart) {
        this.distanceToStart = distanceToStart;
    }

    public double getDistanceToEnd() {
        return distanceToEnd;
    }

    public void setDistanceToEnd(double distanceToEnd) {
        this.distanceToEnd = distanceToEnd;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    /**
     * Compares sum of distances and as answer tells which one is first.
     *
     * @see Comparable#compareTo(Object)
     *
     * @param o     Another Chell object which is compared to this one
     *
     * @return Integer
     */
    @Override
    public int compareTo(Chell o) {
        double original = getDistanceToStart() + getDistanceToEnd();
        double compare = o.getDistanceToStart() + o.getDistanceToEnd();

        if (original < compare) {
            return -1;
        } else if (original == compare) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Checks if objects are representation of same cordinates in the grid.
     *
     * @param o     Another Chell object which is compared to this one
     * @return  {@code true} if Chell objects contain same coordinates
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Chell chell = (Chell) o;

        return getX() == chell.getX() && getY() == chell.getY();
    }
}
