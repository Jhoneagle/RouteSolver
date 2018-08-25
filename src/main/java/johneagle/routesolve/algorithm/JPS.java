package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.library.DataList;
import johneagle.routesolve.library.MinHeap;

/**
 * Jump Point search algorithm.
 * Class is extension of Finder class as it only includes implementation of the abstract method getPath().
 * Otherwise it could be any other heuristic pathfinding algorithm.
 * Also contains necessary method for finding next jump point in the map.
 *
 * @see Finder
 *
 * @author Johneagle
 */
public class JPS extends Finder {
    public JPS(Map map) {
        super(map);
    }

    /**
     * Algorithm to solve the shortest path in the ascii map. Algorithm is based JPS extension of A* path finding algorithm.
     * The best option from valid next visits is always chosen as a first from the queue which is ordered by sum of distance to start and end.
     * Where Chell object with smallest sum is first and largest as last.
     *
     * Method uses Matrix made out of Chell objects and operations from Map object that contains actual ascii grid to proceed.
     * Which is most convenient to find the shortest path in ascii maps generally even tho in large maps it can take some space from memory.
     *
     * @see Finder#prepare(int, int, int, int)
     * @see DataList
     * @see MinHeap#poll()
     * @see MinHeap#add(Object)
     * @see Map#getAproxDistance(int, int, int, int)
     * @see Map#isInsideMap(int, int)
     * @see Map#isWalkable(int, int)
     * @see Map#hash(int, int)
     * @see JPS#checkForJumpPoint(Chell, Chell, int, int)
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

        // Starts from the beginning coordinate and continues until destination coordinate is met or there is no more options to go to.

        while (!queue.isEmpty()) {
            Chell current = queue.poll();
            current.setVisited();

            int x = current.getX();
            int y = current.getY();

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

                    if (map.isInsideMap(x + dx, y + dy) && map.isWalkable(x + dx, y + dy)) {
                        if (chells[y + dy][x + dx] != null) {
                            if (!chells[y + dy][x + dx].isVisited()) {
                                Chell jump = checkForJumpPoint(current, goal, dx, dy);

                                if (jump != null) {
                                    if (chells[jump.getY()][jump.getX()] == null) {
                                        path[map.hash(jump.getX(), jump.getY())] = current;
                                        chells[jump.getY()][jump.getX()] = jump;
                                        queue.add(jump);
                                    }
                                }
                            }
                        } else {
                            Chell jump = checkForJumpPoint(current, goal, dx, dy);

                            if (jump != null) {
                                if (chells[jump.getY()][jump.getX()] == null) {
                                    path[map.hash(jump.getX(), jump.getY())] = current;
                                    chells[jump.getY()][jump.getX()] = jump;
                                    queue.add(jump);
                                }
                            }
                        }
                    }
                }
            }

            chells[y][x] = current;
        }

        // Puts together from linear line representation of the Chell matrix which places have been used in the shortest path.

        DataList<Chell> result = new DataList<>();
        result.add(goal);

        Chell last = path[map.hash(endX, endY)];

        if (last != null) {
            while (last.getX() != startX || last.getY() != startY) {
                result.add(last);
                last = path[map.hash(last.getX(), last.getY())];
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
        int x = next.getX();
        int y = next.getY();

        next.setDistanceToEnd(map.getAproxDistance(x, goal.getX(), y, goal.getY()));
        double sum = whereAt.getDistanceToStart() + map.getValueForMovement(whereAt.getX(), whereAt.getY(), x, y);
        next.setDistanceToStart(sum);

        if (!map.isInsideMap(x, y) || !map.isWalkable(x, y)) {
            return null;
        }

        if (next.equals(goal)) {
            return next;
        }

        // Diagonal or vertical/horizontal.

        if (dx != 0 && dy != 0) {
            if (chells[y - dy][x] != null || chells[y][x - dx] != null) {
                return null; // we went too fast and jumped over better route
            }

            if (map.isInsideMap(x - dx, y) && map.isInsideMap(x - dx, y + dy)) {
                if (!map.isWalkable(x - dx, y) && map.isWalkable(x - dx, y + dy)) {
                    return next;
                }
            }

            if (map.isInsideMap(x, y - dy) && map.isInsideMap(x + dx, y - dy)) {
                if (!map.isWalkable(x, y - dy) && map.isWalkable(x + dx, y - dy)) {
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
                if (!map.isWalkable(x, y + 1) && map.isWalkable(x + dx, y)) {
                    if (map.isWalkable(x + dx, y + 1)) {
                        return next;
                    }
                }

                if (!map.isWalkable(x, y - 1) && map.isWalkable(x + dx, y)) {
                    if (map.isWalkable(x + dx, y - 1)) {
                        return next;
                    }
                }
            } else {
                if (!map.isWalkable(x + 1, y) && map.isWalkable(x, y + dy)) {
                    if (map.isWalkable(x + 1, y + dy)) {
                        return next;
                    }
                }

                if (!map.isWalkable(x - 1, y) && map.isWalkable(x, y + dy)) {
                    if (map.isWalkable(x - 1, y + dy)) {
                        return next;
                    }
                }
            }
        }

        return checkForJumpPoint(next, goal, dx, dy);
    }
}
