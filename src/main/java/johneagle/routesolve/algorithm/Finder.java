package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;

import java.util.PriorityQueue;
import java.util.Stack;

public class Finder {

    private Map asciiMap;
    private Chell[] path;
    private PriorityQueue<Chell> queue;
    private Chell[][] chellMap;

    public Finder(Map map) {
        this.asciiMap = map;
    }

    public Map getAsciiMap() {
        return asciiMap;
    }

    public Stack<Chell> getPath(int startX, int startY, int endX, int endY) {
        if (this.asciiMap.getMap() == null || this.asciiMap.getProperties() == null) {
            return null;
        }

        int mapHeight = this.asciiMap.getMapHeight();
        int mapWeight = this.asciiMap.getMapWeight();

        this.path = new Chell[mapWeight * mapHeight];
        this.queue = new PriorityQueue<>();
        this.chellMap = new Chell[mapHeight][mapWeight];

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWeight; x++) {
                this.path[this.asciiMap.hash(x, y)] = null;

                Chell chell = new Chell(x, y);
                chell.setDistanceToStart(Integer.MAX_VALUE);
                chell.setDistanceToEnd(this.asciiMap.getAproxDistance(x, endX, y, endY));

                this.chellMap[y][x] = chell;
            }
        }

        this.chellMap[startY][startX].setDistanceToStart(0);
        this.queue.add(this.chellMap[startY][startX]);

        while (!this.queue.isEmpty()) {
            Chell current = this.queue.poll();
            current.setVisited();
            this.chellMap[current.getY()][current.getX()] = current;

            if (current.getY() == endY && current.getX() == endX) {
                break;
            }

            if (this.asciiMap.isInsideMap(current.getX(), current.getY() + 1) && this.asciiMap.isWalkable(current.getX(), current.getY() + 1) == 1) {
                Chell next = this.chellMap[current.getY() + 1][current.getX()];

                checkNeighbour(current, next);
            }

            if (this.asciiMap.isInsideMap(current.getX(), current.getY() - 1) && this.asciiMap.isWalkable(current.getX(), current.getY() - 1) == 1) {
                Chell next = this.chellMap[current.getY() - 1][current.getX()];

                checkNeighbour(current, next);
            }

            if (this.asciiMap.isInsideMap(current.getX() + 1, current.getY()) && this.asciiMap.isWalkable(current.getX() + 1, current.getY()) == 1) {
                Chell next = this.chellMap[current.getY()][current.getX() + 1];

                checkNeighbour(current, next);
            }

            if (this.asciiMap.isInsideMap(current.getX() - 1, current.getY()) && this.asciiMap.isWalkable(current.getX() - 1, current.getY()) == 1) {
                Chell next = this.chellMap[current.getY()][current.getX() - 1];

                checkNeighbour(current, next);
            }
        }

        Stack<Chell> result = new Stack<>();
        Chell last = this.path[this.asciiMap.hash(endX, endY)];

        while (last != null) {
            result.push(last);
            last = this.path[this.asciiMap.hash(last.getX(), last.getY())];
        }

        return result;
    }

    private void checkNeighbour(Chell current, Chell next) {
        if (!next.isVisited()) {
            int sum = current.getDistanceToStart() + this.asciiMap.getValue(next.getX(), next.getY());

            if (next.getDistanceToStart() > sum) {
                next.setDistanceToStart(sum);
                this.path[this.asciiMap.hash(next.getX(), next.getY())] = current;
                this.chellMap[next.getY()][next.getX()] = next;
            }

            this.queue.add(next);
        }
    }

}
