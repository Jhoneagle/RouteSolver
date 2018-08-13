package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.library.DataList;

import java.util.PriorityQueue;

/**
 * Main object of the algorithm. Contains the actual method to find shortest path in ascii map/grid.
 * It needs Map object which is declared in constructor to work which also has to have proper setup make sure algorithm works.
 * This only contains the Heart of the modificated A* path finding algorithm as lot of necessary stuff is done outside in other objects.
 * Mainly in Map object but also in PriorityQueue thanks to Chell objects comparability with each others.
 *
 * @see Finder#getPathAllstar(int, int, int, int)
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
     * Algorithm to solve the shortest path in the ascii map. Algorithm is A* based but works as own modification of it.
     * The best option from valid next visits is always chosen as a first from the queue which is ordered by sum of distance to start and end.
     * Where Chell object with smallest sum is first and largest as last.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Both are anyways saved as 2-dimensional table which makes it unhealthy when grid is large.
     * But is most convenient form to find the shortest path in ascii maps generally.
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
    public DataList<Chell> getPathAllstar(int startX, int startY, int endX, int endY) {
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
        Chell goal = null;

        // Starts from the beginning coordinate and continues until currently checked is destination coordinate or there is no more options to go to.

        while (!this.queue.isEmpty()) {
            Chell current = this.queue.poll();
            this.chellMap[current.getY()][current.getX()] = current;

            if (current.getY() == endY && current.getX() == endX) {
                goal = current;
                break;
            }

            //south
            if (this.asciiMap.isInsideMap(current.getX(), current.getY() + 1) && this.asciiMap.isWalkable(current.getX(), current.getY() + 1) == 1) {
                Chell next = this.chellMap[current.getY() + 1][current.getX()];

                if (next == null) {
                    next = new Chell(current.getX(), current.getY() + 1);
                    next.setDistanceToStart(Integer.MAX_VALUE);
                    next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                }

                checkNeighbour(current, next);
            }

            //north
            if (this.asciiMap.isInsideMap(current.getX(), current.getY() - 1) && this.asciiMap.isWalkable(current.getX(), current.getY() - 1) == 1) {
                Chell next = this.chellMap[current.getY() - 1][current.getX()];

                if (next == null) {
                    next = new Chell(current.getX(), current.getY() - 1);
                    next.setDistanceToStart(Integer.MAX_VALUE);
                    next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                }

                checkNeighbour(current, next);
            }

            //east (right)
            if (this.asciiMap.isInsideMap(current.getX() + 1, current.getY()) && this.asciiMap.isWalkable(current.getX() + 1, current.getY()) == 1) {
                Chell next = this.chellMap[current.getY()][current.getX() + 1];

                if (next == null) {
                    next = new Chell(current.getX() + 1, current.getY());
                    next.setDistanceToStart(Integer.MAX_VALUE);
                    next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                }

                checkNeighbour(current, next);
            }

            //west (left)
            if (this.asciiMap.isInsideMap(current.getX() - 1, current.getY()) && this.asciiMap.isWalkable(current.getX() - 1, current.getY()) == 1) {
                Chell next = this.chellMap[current.getY()][current.getX() - 1];

                if (next == null) {
                    next = new Chell(current.getX() - 1, current.getY());
                    next.setDistanceToStart(Integer.MAX_VALUE);
                    next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                }

                checkNeighbour(current, next);
            }

            //southeast (south right)
            if (this.asciiMap.isInsideMap(current.getX() + 1, current.getY() + 1) && this.asciiMap.isWalkable(current.getX() + 1, current.getY() + 1) == 1) {
                if (this.asciiMap.isWalkable(current.getX() + 1, current.getY()) == 1 && this.asciiMap.isWalkable(current.getX(), current.getY() + 1) == 1) {
                    Chell next = this.chellMap[current.getY() + 1][current.getX() + 1];

                    if (next == null) {
                        next = new Chell(current.getX() + 1, current.getY() + 1);
                        next.setDistanceToStart(Integer.MAX_VALUE);
                        next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                    }

                    checkNeighbour(current, next);
                }
            }

            //northwest (north left)
            if (this.asciiMap.isInsideMap(current.getX() - 1, current.getY() - 1) && this.asciiMap.isWalkable(current.getX() - 1, current.getY() - 1) == 1) {
                if (this.asciiMap.isWalkable(current.getX() - 1, current.getY()) == 1 && this.asciiMap.isWalkable(current.getX(), current.getY() - 1) == 1) {
                    Chell next = this.chellMap[current.getY() - 1][current.getX() - 1];

                    if (next == null) {
                        next = new Chell(current.getX() - 1, current.getY() - 1);
                        next.setDistanceToStart(Integer.MAX_VALUE);
                        next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                    }

                    checkNeighbour(current, next);
                }
            }

            //northeast (north right)
            if (this.asciiMap.isInsideMap(current.getX() + 1, current.getY() - 1) && this.asciiMap.isWalkable(current.getX() + 1, current.getY() - 1) == 1) {
                if (this.asciiMap.isWalkable(current.getX() + 1, current.getY()) == 1 && this.asciiMap.isWalkable(current.getX(), current.getY() - 1) == 1) {
                    Chell next = this.chellMap[current.getY() - 1][current.getX() + 1];

                    if (next == null) {
                        next = new Chell(current.getX() + 1, current.getY() - 1);
                        next.setDistanceToStart(Integer.MAX_VALUE);
                        next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                    }

                    checkNeighbour(current, next);
                }
            }

            //southwest (south left)
            if (this.asciiMap.isInsideMap(current.getX() - 1, current.getY() + 1) && this.asciiMap.isWalkable(current.getX() - 1, current.getY() + 1) == 1) {
                if (this.asciiMap.isWalkable(current.getX() - 1, current.getY()) == 1 && this.asciiMap.isWalkable(current.getX(), current.getY() + 1) == 1) {
                    Chell next = this.chellMap[current.getY() + 1][current.getX() - 1];

                    if (next == null) {
                        next = new Chell(current.getX() - 1, current.getY() + 1);
                        next.setDistanceToStart(Integer.MAX_VALUE);
                        next.setDistanceToEnd(this.asciiMap.getAproxDistance(next.getX(), endX, next.getY(), endY));
                    }

                    checkNeighbour(current, next);
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
     * Algorithm to solve the shortest path in the ascii map. Algorithm is based JPS extension of A* but works as own modification of it.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Both are anyways saved as 2-dimensional table which makes it unhealthy when grid is large.
     * But is most convenient form to find the shortest path in ascii maps generally.
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

        // Starts from the beginning coordinate and continues until destination coordinate is met or there is no more options to go to.

        while (!this.queue.isEmpty()) {
            Chell current = this.queue.poll();


        }

        // Puts together from linear line representation of the Chell matrix which places have been used in the shortest path.

        DataList<Chell> result = new DataList<>();
        Chell last = this.path[this.asciiMap.hash(endX, endY)];

        while (last != null) {
            result.add(last);
            last = this.path[this.asciiMap.hash(last.getX(), last.getY())];
        }

        return result;
    }

    /**
     * Function checks if neighbour has been visited already. If not then it updates íts distance to start point and makes representation of current one in the place neighbour belongs in linear line.
     * And no matter about the update adds the neighbour to queue of Chell objects.
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
}
