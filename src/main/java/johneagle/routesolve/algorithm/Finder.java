package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.interfaces.Pathfinding;
import johneagle.routesolve.library.DataList;
import johneagle.routesolve.library.MinHeap;

/**
 * Abstract main class for all heuristic pathfinding algorithms. Contains prepare-method and global variables of those that extend it.
 *
 * @see Map
 * @see MinHeap
 *
 * @author Johneagle
 */
public abstract class Finder implements Pathfinding {

    protected Map map;
    protected Chell[] path;
    protected MinHeap<Chell> queue;
    protected Chell[][] chells;

    public Finder(Map map) {
        this.map = map;
    }

    /**
     * Method for pathfinding algorithm to solve the shortest path.
     *
     * @param startX    Begging x-coordinate.
     * @param startY    Begging y-coordinate.
     * @param endX      Destination x-coordinate.
     * @param endY      Destination y-coordinate.
     *
     * @return Path as list of Chell objects.
     */
    @Override
    public abstract DataList<Chell> getPath(int startX, int startY, int endX, int endY);

    /**
     * Does the preparations for pathfinding algorithm.
     *
     * @param startX    Begging x-coordinate.
     * @param startY    Begging y-coordinate.
     * @param endX      Destination x-coordinate.
     * @param endY      Destination y-coordinate.
     *
     * @return Goal point as Chell object.
     */
    @Override
    public Chell prepare(int startX, int startY, int endX, int endY) {
        if (map.getMap() == null) {
            return null;
        }
        
        int mapHeight = map.getMapHeight();
        int mapWeight = map.getMapWeight();

        path = new Chell[mapWeight * mapHeight];
        queue = new MinHeap<>();
        chells = new Chell[mapHeight][mapWeight];

        Chell start = new Chell(startX, startY);
        start.setDistanceToStart(0);
        start.setDistanceToEnd(map.getAproxDistance(start.getX(), endX, start.getY(), endY));
        queue.add(start);
        Chell goal = new Chell(endX, endY);

        return goal;
    }
}
