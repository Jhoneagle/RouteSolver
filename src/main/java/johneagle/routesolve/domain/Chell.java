package johneagle.routesolve.domain;

public class Chell implements Comparable<Chell> {
    private int x;
    private int y;
    private int distanceToStart;
    private int distanceToEnd;
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

    public int getDistanceToStart() {
        return distanceToStart;
    }

    public void setDistanceToStart(int distanceToStart) {
        this.distanceToStart = distanceToStart;
    }

    public int getDistanceToEnd() {
        return distanceToEnd;
    }

    public void setDistanceToEnd(int distanceToEnd) {
        this.distanceToEnd = distanceToEnd;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public int compareTo(Chell o) {
        int original = getDistanceToStart() + getDistanceToEnd();
        int compare = o.getDistanceToStart() + o.getDistanceToEnd();

        if (original < compare) {
            return -1;
        } else if (original == compare) {
            return 0;
        } else {
            return 1;
        }
    }
}
