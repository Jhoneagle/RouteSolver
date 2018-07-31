package johneagle.routesolve.domain;

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    private int distanceStart;
    private int distanceEnd;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.distanceStart = Integer.MAX_VALUE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistanceStart() {
        return distanceStart;
    }

    public int getDistanceEnd() {
        return distanceEnd;
    }

    public void setDistanceStart(int distanceStart) {
        this.distanceStart = distanceStart;
    }

    public void setDistanceEnd(int distanceEnd) {
        this.distanceEnd = distanceEnd;
    }

    @Override
    public String toString() {
        return this.x+","+this.y;
    }

    @Override
    public int compareTo(Point o) {
        int original = getDistanceStart() + getDistanceEnd();
        int compare = o.getDistanceStart() + o.getDistanceEnd();

        if (original < compare) {
            return -1;
        } else if (original == compare) {
            return 0;
        } else {
            return 1;
        }
    }
}
