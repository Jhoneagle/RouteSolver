package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.library.DataList;
import johneagle.routesolve.library.MinHeap;

/**
 * A* algorithm. Class is extension of Finder class as it only includes implementation of the abstract method getPath().
 * Otherwise it could be any other heuristic pathfinding algorithm.
 * Also contains necessary method to check if neighbour is valid to go.
 *
 * @see Finder
 *
 * @author Johneagle
 */
public class AStar extends Finder {

    public AStar(Map map) {
        super(map);
    }

    /**
     * Algorithm to solve the shortest path in the ascii map. Algorithm is based on the A* path finding algorithm.
     * The best option from valid next visits is always chosen as a first from the queue which is ordered by sum of distance to start and end.
     * Where Chell object with smallest sum is first and largest as last.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Which is most convenient to find the shortest path in ascii maps generally even tho in large maps it can take some space from memory.
     *
     * @see Finder#prepare(int, int, int, int)
     * @see DataList
     * @see MinHeap#poll()
     * @see Map#getAproxDistance(int, int, int, int)
     * @see Map#isInsideMap(int, int)
     * @see Map#isWalkable(int, int)
     * @see Map#hash(int, int)
     * @see AStar#checkNeighbour(Chell, Chell)
     *
     * @param startX    Begging x-coordinate.
     * @param startY    Begging y-coordinate.
     * @param endX      Destination x-coordinate.
     * @param endY      Destination y-coordinate.
     *
     * @return Pile of Chell objects in Stack object.
     */
    @Override
    public DataList<Chell> getPath(int startX, int startY, int endX, int endY) {
        Chell goal = prepare(startX, startY, endX, endY);

        // Starts from the beginning coordinate and continues until currently checked is destination coordinate or there is no more options to go to.

        while (!queue.isEmpty()) {
            Chell current = queue.poll();
            int x = current.getX();
            int y = current.getY();

            chells[y][x] = current;

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
                        if (map.isInsideMap(x + dx, y + dy) && map.isWalkable(x + dx, y + dy)) {
                            if (map.isWalkable(x + dx, y) || map.isWalkable(x, y + dy)) {
                                Chell next = chells[y + dy][x + dx];

                                if (next == null) {
                                    next = new Chell(x + dx, y + dy);
                                    next.setDistanceToStart(Integer.MAX_VALUE);
                                    next.setDistanceToEnd(map.getAproxDistance(next.getX(), endX, next.getY(), endY));
                                }

                                checkNeighbour(current, next);
                            }
                        }
                    } else {
                        if (map.isInsideMap(x + dx, y + dy) && map.isWalkable(x + dx, y + dy)) {
                            Chell next = chells[y + dy][x + dx];

                            if (next == null) {
                                next = new Chell(x + dx, y + dy);
                                next.setDistanceToStart(Integer.MAX_VALUE);
                                next.setDistanceToEnd(map.getAproxDistance(next.getX(), endX, next.getY(), endY));
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

        Chell last = path[map.hash(endX, endY)];

        while (last != null) {
            result.add(last);
            last = path[map.hash(last.getX(), last.getY())];
        }

        return result;
    }

    /**
     * Function checks if neighbour has been visited already. If not then it updates íts distance to start point and makes representation of current one in the place neighbour belongs in linear line.
     * And no matter the update happens or not adds the neighbour to queue of Chell objects.
     *
     * @see Map#getValueForMovement(int, int, int, int)
     * @see Map#hash(int, int)
     * @see MinHeap#add(Object)
     *
     * @param current   Chell object that currently is selected.
     * @param next      Chell object next to current one.
     */
    private void checkNeighbour(Chell current, Chell next) {
        if (!next.isVisited()) {
            double sum = current.getDistanceToStart() + map.getValueForMovement(current.getX(), current.getY(), next.getX(), next.getY());
            next.setVisited();

            if (next.getDistanceToStart() > sum) {
                next.setDistanceToStart(sum);
                path[map.hash(next.getX(), next.getY())] = current;
            }

            chells[next.getY()][next.getX()] = next;
            queue.add(next);
        }
    }
}
