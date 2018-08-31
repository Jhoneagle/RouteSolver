package johneagle.routesolve.interfaces;

/**
 * Interface for grid to make sure handling important operations.
 *
 * @author Johneagle
 */
public interface Grid {
    boolean isInsideMap(int x, int y);

    boolean isWalkable(int x, int y);

    double getValueForMovement(int fromX, int fromY, int whereX, int whereY);

    double getAproxDistance(int fristX, int secondX, int fristY, int secondY);
}
