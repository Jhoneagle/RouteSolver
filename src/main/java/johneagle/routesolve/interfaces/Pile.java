package johneagle.routesolve.interfaces;

/**
 * Interface for pile to demand important basic operations.
 *
 * @author Johneagle
 */
public interface Pile<E> {
    E push(E item);

    E pop();

    E peek();
}
