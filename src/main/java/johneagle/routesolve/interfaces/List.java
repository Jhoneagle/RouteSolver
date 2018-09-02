package johneagle.routesolve.interfaces;

/**
 * Interface for list to provide info what operations are needed to be support in implementations.
 *
 * @author Johneagle
 */
public interface List<E> {
    int size();

    boolean isEmpty();

    boolean add(E e);

    boolean contains(Object o);

    E remove(int index);

    E get(int index);

    E set(int index, E element);

    int indexOf(Object o);
}
