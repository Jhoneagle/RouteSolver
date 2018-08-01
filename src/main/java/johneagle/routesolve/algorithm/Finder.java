package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Config;
import johneagle.routesolve.domain.Chell;

import java.util.PriorityQueue;

public class Finder {
    private char[][] map;
    private Config properties;

    public Finder() {
        this.map = null;
        this.properties = null;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void setProperties(Config properties) {
        this.properties = properties;
    }

    public Chell[] getPath(int startX, int startY, int endX, int endY) {
        if (this.map == null || this.properties == null) {
            System.out.println("tarvittavia tietoja ei ole määritelty!");
            return null;
        }

        Chell[] path = new Chell[this.properties.getX() * this.properties.getY()];
        PriorityQueue<Chell> queue = new PriorityQueue<>();
        Chell[][] chellMap = new Chell[this.properties.getY()][this.properties.getX()];

        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[0].length; x++) {
                path[Hash(x, y)] = null;

                Chell chell = new Chell(x, y);
                chell.setDistanceToStart(Integer.MAX_VALUE);
                chell.setDistanceToEnd(getAproxDistance(x, endX, y, endY));

                chellMap[y][x] = chell;
            }
        }

        chellMap[startY][startX].setDistanceToStart(0);
        queue.add(chellMap[startY][startX]);

        while (!queue.isEmpty()) {
            Chell current = queue.poll();
            current.setVisited();
            chellMap[current.getY()][current.getX()] = current;

            if (current.getY() == endY && current.getX() == endX) {
                break;
            }

            if (isInsideMap(current.getY() + 1, current.getX()) && isWalkable(current.getY() + 1, current.getX()) == 1) {
                Chell next = chellMap[startY + 1][startX];

                if (!next.isVisited()) {
                    int sum = current.getDistanceToStart() + getValue(next.getX(), next.getY());

                    if (next.getDistanceToStart() > sum) {
                        next.setDistanceToStart(sum);
                        path[Hash(next.getX(), next.getY())] = current;
                        chellMap[next.getY()][next.getX()] = next;
                    }

                    queue.add(next);
                }
            }

            if (isInsideMap(current.getY() - 1, current.getX()) && isWalkable(current.getY() - 1, current.getX()) == 1) {
                Chell next = chellMap[startY - 1][startX];

                if (!next.isVisited()) {
                    int sum = current.getDistanceToStart() + getValue(next.getX(), next.getY());

                    if (next.getDistanceToStart() > sum) {
                        next.setDistanceToStart(sum);
                        path[Hash(next.getX(), next.getY())] = current;
                        chellMap[next.getY()][next.getX()] = next;
                    }

                    queue.add(next);
                }
            }

            if (isInsideMap(current.getY(), current.getX() + 1) && isWalkable(current.getY(), current.getX() + 1) == 1) {
                Chell next = chellMap[startY][startX + 1];

                if (!next.isVisited()) {
                    int sum = current.getDistanceToStart() + getValue(next.getX(), next.getY());

                    if (next.getDistanceToStart() > sum) {
                        next.setDistanceToStart(sum);
                        path[Hash(next.getX(), next.getY())] = current;
                        chellMap[next.getY()][next.getX()] = next;
                    }

                    queue.add(next);
                }
            }

            if (isInsideMap(current.getY(), current.getX() - 1) && isWalkable(current.getY(), current.getX() - 1) == 1) {
                Chell next = chellMap[startY][startX - 1];

                if (!next.isVisited()) {
                    int sum = current.getDistanceToStart() + getValue(next.getX(), next.getY());

                    if (next.getDistanceToStart() > sum) {
                        next.setDistanceToStart(sum);
                        path[Hash(next.getX(), next.getY())] = current;
                        chellMap[next.getY()][next.getX()] = next;
                    }

                    queue.add(next);
                }
            }
        }

        return path;
    }

    public int Hash(int x, int y) {
        return y * this.properties.getX() + x;
    }

    private boolean isInsideMap(int x, int y) {
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

    private Integer isWalkable(int x, int y) {
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

    private Integer getValue(int x, int y) {
        char type = this.map[y][x];

        return 1;
    }

    private Integer getAproxDistance(int fristX, int secondX, int fristY, int secondY) {
        int aproximate = (fristX - secondX) + (fristY - secondY);

        if (aproximate < 0) {
            aproximate *= -1;
        }

        return aproximate;
    }
}
