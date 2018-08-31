package johneagle.routesolve.interfaces;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.library.DataList;

/**
 * Interface for Pathfinding algorithm to tell how to build it commonly on classes.
 *
 * @author Johneagle
 */
public interface Pathfinding {
    DataList<Chell> getPath(int startX, int startY, int endX, int endY);

    Chell prepare(int startX, int startY, int endX, int endY);
}
