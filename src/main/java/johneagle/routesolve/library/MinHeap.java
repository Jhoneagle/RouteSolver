package johneagle.routesolve.library;

import java.util.AbstractQueue;

/**
 * Class that works just like Javas ready made PriorityQueue would work in the algorithm.
 *
 * @param <E>   type allowed to have in the queue.
 *
 * @author Johneagle
 */
public class MinHeap<E>  {

    private Object[] elementData;
    private int maxSize;
    private int actualSize;

    public MinHeap() {
        this.maxSize = 10;
        this.actualSize = 0;
        this.elementData = new Object[this.maxSize];
    }

    /**
     * Resize the array so that all elements can fit.
     */
    private void grow(double with) {
        int newMax = (int) (this.maxSize * with);

        if (newMax >= 10) {
            this.maxSize = newMax;
            Object[] newList = new Object[this.maxSize];

            for (int i = 0; i < this.actualSize; i++) {
                newList[i] = this.elementData[i];
            }

            this.elementData = newList;
        }
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue.
     */
    public int size() {
        return this.actualSize;
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty() {
        return this.actualSize == 0;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true}
     */
    public boolean add(E e) {
        return true;
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     * More formally, returns {@code true} if and only if this queue contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this queue is to be tested
     * @return {@code true} if this queue contains the specified element
     */
    public boolean contains(Object o) {
        return false;
    }

    /**
     * This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * <p>This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    public Object[] toArray() {
        return null;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public E poll() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public E peek() {
        return null;
    }
}
