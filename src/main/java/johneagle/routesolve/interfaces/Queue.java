package johneagle.routesolve.interfaces;

/**
 * Interface for queue. Provides operations important for queue to all its implementations.
 *
 * @author Johneagle
 */
public interface Queue<E> {
    boolean add(E e);

    boolean contains(Object o);

    E poll();

    E peek();

    boolean offer(E e);
}
