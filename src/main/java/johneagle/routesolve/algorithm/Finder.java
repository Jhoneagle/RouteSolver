package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.library.DataList;

import java.util.PriorityQueue;

/**
 * Main object of the algorithm. Contains the actual method to find shortest path in ascii map/grid.
 * It needs Map object which is declared in constructor to work which also has to have proper setup to make sure algorithm works.
 * It contains both regular A* and Jump point search (JPS) path finding algorithms.
 *
 * @see Map
 * @see PriorityQueue
 *
 * @author Johneagle
 */
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

    /**
     * Algorithm to solve the shortest path in the ascii map. Algorithm is based on the A* path finding algorithm.
     * The best option from valid next visits is always chosen as a first from the queue which is ordered by sum of distance to start and end.
     * Where Chell object with smallest sum is first and largest as last.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Which is most convenient to find the shortest path in ascii maps generally even tho in large maps it can take some space from memory.
     *
     * @see DataList
     * @see PriorityQueue#poll()
     * @see Map#getAproxDistance(int, int, int, int)
     * @see Map#isInsideMap(int, int)
     * @see Map#isWalkable(int, int)
     * @see Map#hash(int, int)
     * @see Finder#checkNeighbour(Chell, Chell)
     *
     * @param startX    Begging x-coordinate.
     * @param startY    Begging y-coordinate.
     * @param endX      Destination x-coordinate.
     * @param endY      Destination y-coordinate.
     *
     * @return Pile of Chell objects in Stack object.
     */
    public DataList<Chell> getPathAStar(int startX, int startY, int endX, int endY) {
        if (this.asciiMap.getMap() == null || this.asciiMap.getProperties() == null) {
            return null;
        }

        int mapHeight = this.asciiMap.getMapHeight();
        int mapWeight = this.asciiMap.getMapWeight();

        this.path = new Chell[mapWeight * mapHeight];
        this.queue = new PriorityQueue<>();
        this.chellMap = new Chell[mapHeight][mapWeight];

        Chell start = new Chell(startX, startY);
        start.setDistanceToStart(0);
        start.setDistanceToEnd(this.asciiMap.getAproxDistance(start.getX(), endX, start.getY(), endY));
        this.queue.add(start);
        Chell goal = new Chell(endX, endY);

        // Starts from the beginning coordinate and continues until currently checked is destination coordinate or there is no more options to go to.

        while (!this.queue.isEmpty()) {
            Chell current = this.queue.poll();
            this.chellMap[current.getY()][current.getX()] = current;

            if (goal.equals(current)) {
                goal = current;
                break;
            }

            // Checks neighbours from all directions.

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue;   // We don't want to look again the place we are already.
                    }

                    // Diagonal or vertical/horizontal.

                    if (dx != 0 && dy != 0) {
                        if (this.asciiMap.isInsideMap(current.getX() + dx, current.getY() + dy) && this.asciiMap.isWalkable(current.getX() + dx, current.getY() + dy) == 1) {
                            if (this.asciiMap.isWalkable(current.getX() + dx, current.getY()) == 1 || this.asciiMap.isWalkable(current.getX(), current.getY() + dy) == 1) {
                                Chell next = this.chellMap[current.getY() + dy][current.getX() + dx];

                                if (next == null) {
                                    next = new Chell(current.getX() + dx, current.getY() + dy);
                                    next.setDistanceToStart(Integer.MAX_VALUE);
                                    next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                                }

                                checkNeighbour(current, next);
                            }
                        }
                    } else {
                        if (this.asciiMap.isInsideMap(current.getX() + dx, current.getY() + dy) && this.asciiMap.isWalkable(current.getX() + dx, current.getY() + dy) == 1) {
                            Chell next = this.chellMap[current.getY() + dy][current.getX() + dx];

                            if (next == null) {
                                next = new Chell(current.getX() + dx, current.getY() + dy);
                                next.setDistanceToStart(Integer.MAX_VALUE);
                                next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                            }

                            checkNeighbour(current, next);
                        }
                    }
                }
            }
        }

        // Puts together from linear line representation of the Chell matrix which places have been used in the shortest path.

        DataList<Chell> result = new DataList<>();
        result.add(goal);

        Chell last = this.path[this.asciiMap.hash(endX, endY)];

        while (last != null) {
            result.add(last);
            last = this.path[this.asciiMap.hash(last.getX(), last.getY())];
        }

        return result;
    }

    /**
     * Function checks if neighbour has been visited already. If not then it updates íts distance to start point and makes representation of current one in the place neighbour belongs in linear line.
     * And no matter the update happens or not adds the neighbour to queue of Chell objects.
     *
     * @see Map#getValueForMovement(int, int, int, int)
     * @see Map#hash(int, int)
     * @see PriorityQueue#add(Object)
     *
     * @param current   Chell object that currently is selected.
     * @param next      Chell object next to current one.
     */
    private void checkNeighbour(Chell current, Chell next) {
        if (!next.isVisited()) {
            double sum = current.getDistanceToStart() + this.asciiMap.getValueForMovement(current.getX(), current.getY(), next.getX(), next.getY());
            next.setVisited();

            if (next.getDistanceToStart() > sum) {
                next.setDistanceToStart(sum);
                this.path[this.asciiMap.hash(next.getX(), next.getY())] = current;
            }

            this.chellMap[next.getY()][next.getX()] = next;
            this.queue.add(next);
        }
    }

    /**
     * Algorithm to solve the shortest path in the ascii map. Algorithm is based JPS extension of A* path finding algorithm.
     * The best option from valid next visits is always chosen as a first from the queue which is ordered by sum of distance to start and end.
     * Where Chell object with smallest sum is first and largest as last.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Which is most convenient to find the shortest path in ascii maps generally even tho in large maps it can take some space from memory.
     *
     * @see DataList
     * @see PriorityQueue#poll()
     * @see Map#getAproxDistance(int, int, int, int)
     * @see Map#isInsideMap(int, int)
     * @see Map#isWalkable(int, int)
     * @see Map#hash(int, int)
     * @see Finder#checkForJumpPoint(Chell, Chell, int, int)
     *
     * @param startX    Begging x-coordinate.
     * @param startY    Begging y-coordinate.
     * @param endX      Destination x-coordinate.
     * @param endY      Destination y-coordinate.
     *
     * @return Pile of Chell objects in Stack object.
     */
    public DataList<Chell> getPathJPS(int startX, int startY, int endX, int endY) {
        if (this.asciiMap.getMap() == null || this.asciiMap.getProperties() == null) {
            return null;
        }

        int mapHeight = this.asciiMap.getMapHeight();
        int mapWeight = this.asciiMap.getMapWeight();

        this.path = new Chell[mapWeight * mapHeight];
        this.queue = new PriorityQueue<>();
        this.chellMap = new Chell[mapHeight][mapWeight];

        Chell start = new Chell(startX, startY);
        start.setDistanceToStart(0);
        start.setDistanceToEnd(this.asciiMap.getAproxDistance(start.getX(), endX, start.getY(), endY));
        this.queue.add(start);
        Chell goal = new Chell(endX, endY);

        // Starts from the beginning coordinate and continues until destination coordinate is met or there is no more options to go to.

        while (!this.queue.isEmpty()) {
            Chell current = this.queue.poll();
            current.setVisited();

            if (goal.equals(current)) {
                goal = current;
                break;
            }

            // Checks neighbours from all directions.

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue;   // We don't want to look again the place we are already.
                    }

                    if (this.asciiMap.isInsideMap(current.getX() + dx, current.getY() + dy) && this.asciiMap.isWalkable(current.getX() + dx, current.getY() + dy) == 1) {
                        if (this.chellMap[current.getY() + dy][current.getX() + dx] != null) {
                            if (!this.chellMap[current.getY() + dy][current.getX() + dx].isVisited()) {
                                Chell jumpPoint = checkForJumpPoint(current, goal, dx, dy);

                                if (jumpPoint != null) {
                                    if (this.chellMap[jumpPoint.getY()][jumpPoint.getX()] == null) {
                                        this.path[this.asciiMap.hash(jumpPoint.getX(), jumpPoint.getY())] = current;
                                        this.chellMap[jumpPoint.getY()][jumpPoint.getX()] = jumpPoint;
                                        this.queue.add(jumpPoint);
                                    }
                                }
                            }
                        } else {
                            Chell jumpPoint = checkForJumpPoint(current, goal, dx, dy);

                            if (jumpPoint != null) {
                                if (this.chellMap[jumpPoint.getY()][jumpPoint.getX()] == null) {
                                    this.path[this.asciiMap.hash(jumpPoint.getX(), jumpPoint.getY())] = current;
                                    this.chellMap[jumpPoint.getY()][jumpPoint.getX()] = jumpPoint;
                                    this.queue.add(jumpPoint);
                                }
                            }
                        }
                    }
                }
            }

            this.chellMap[current.getY()][current.getX()] = current;
        }

        // Puts together from linear line representation of the Chell matrix which places have been used in the shortest path.

        DataList<Chell> result = new DataList<>();
        result.add(goal);

        Chell last = this.path[this.asciiMap.hash(endX, endY)];

        if (last != null) {
            while (last.getX() != startX || last.getY() != startY) {
                result.add(last);
                last = this.path[this.asciiMap.hash(last.getX(), last.getY())];
            }
        }

        return result;
    }

    /**
     * Method is recursive to find next jump point if any even exists on that direct path. Chell considered jump point must have forced neighbour.
     * So basically neighbour that you wouldn't get to from the origin point without going through Chell object currently at optimally.
     *
     * @see Map#getAproxDistance(int, int, int, int)
     * @see Map#isInsideMap(int, int)
     * @see Map#isWalkable(int, int)
     * @see Map#getValueForMovement(int, int, int, int)
     *
     * @param whereAt   which Chell block to be
     * @param goal      destination Chell of the path finding
     * @param dx        x-direction
     * @param dy        y-direction
     * @return null or Chell block that has forcedNeighbour so basically neighbour that makes new route ways
     */
    private Chell checkForJumpPoint(Chell whereAt, Chell goal, int dx, int dy) {
        Chell next = new Chell(whereAt.getX() + dx, whereAt.getY() + dy);

        next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), goal.getX(), next.getY(), goal.getY()));
        double sum = whereAt.getDistanceToStart() + this.asciiMap.getValueForMovement(whereAt.getX(), whereAt.getY(), next.getX(), next.getY());
        next.setDistanceToStart(sum);

        if (!this.asciiMap.isInsideMap(next.getX(), next.getY()) || this.asciiMap.isWalkable(next.getX(), next.getY()) == -1) {
            return null;
        }

        if (next.equals(goal)) {
            return next;
        }

        // Diagonal or vertical/horizontal.

        if (dx != 0 && dy != 0) {
            if (this.asciiMap.isInsideMap(next.getX() - dx, next.getY()) && this.asciiMap.isInsideMap(next.getX() - dx, next.getY() + dy)) {
                if (this.asciiMap.isWalkable(next.getX() - dx, next.getY()) == -1 && this.asciiMap.isWalkable(next.getX() - dx, next.getY() + dy) == 1) {
                    return next;
                }
            }

            if (this.asciiMap.isInsideMap(next.getX(), next.getY() - dy) && this.asciiMap.isInsideMap(next.getX() + dx, next.getY() - dy)) {
                if (this.asciiMap.isWalkable(next.getX(), next.getY() - dy) == -1 && this.asciiMap.isWalkable(next.getX() + dx, next.getY() - dy) == 1) {
                    return next;
                }
            }

            // Checks if position could find something next.

            Chell forcedDX = checkForJumpPoint(next, goal, dx, 0);
            Chell forcedDY = checkForJumpPoint(next, goal, 0, dy);

            if (forcedDX != null || forcedDY != null) {
                return next;
            }
        } else {
            if (dx != 0) {
                if (this.asciiMap.isWalkable(next.getX(), next.getY() + 1) == -1 && this.asciiMap.isWalkable(next.getX() + dx, next.getY()) == 1) {
                    if (this.asciiMap.isWalkable(next.getX() + dx, next.getY() + 1) == 1) {
                        return next;
                    }
                }

                if (this.asciiMap.isWalkable(next.getX(), next.getY() - 1) == -1 && this.asciiMap.isWalkable(next.getX() + dx, next.getY()) == 1) {
                    if (this.asciiMap.isWalkable(next.getX() + dx, next.getY() - 1) == 1) {
                        return next;
                    }
                }
            } else {
                if (this.asciiMap.isWalkable(next.getX() + 1, next.getY()) == -1 && this.asciiMap.isWalkable(next.getX(), next.getY() + dy) == 1) {
                    if (this.asciiMap.isWalkable(next.getX() + 1, next.getY() + dy) == 1) {
                        return next;
                    }
                }

                if (this.asciiMap.isWalkable(next.getX() - 1, next.getY()) == -1 && this.asciiMap.isWalkable(next.getX(), next.getY() + dy) == 1) {
                    if (this.asciiMap.isWalkable(next.getX() - 1, next.getY() + dy) == 1) {
                        return next;
                    }
                }
            }
        }

        return checkForJumpPoint(next, goal, dx, dy);
    }
}
