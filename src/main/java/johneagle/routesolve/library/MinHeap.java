package johneagle.routesolve.library;

/**
 * Class that works just like Javas ready made PriorityQueue would work in the algorithm.
 *
 * @param <E>   type allowed to have in the queue.
 *
 * @author Johneagle
 */
public class MinHeap<E>  {

    private Object[] elementData;
    private int actualSize;

    public MinHeap() {
        actualSize = 0;
        elementData = new Object[10];
    }

    /**
     * Resize the array so that all elements can fit.
     */
    private void grow(double with) {
        int newMax = (int) (elementData.length * with);

        if (newMax >= 10) {
            Object[] newList = new Object[newMax];

            for (int i = 0; i < actualSize; i++) {
                newList[i] = elementData[i];
            }

            elementData = newList;
        }
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return The number of elements in this queue.
     */
    public int size() {
        return actualSize;
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty() {
        return actualSize == 0;
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
        if (actualSize == 0) {
            elementData[1] = e;
            actualSize++;
            return true;
        } else {
            actualSize++;
            int index = actualSize;
            int parent = index / 2;

            if (actualSize >= elementData.length) {
                grow(4);
            }

            while (index > 1) {
                Comparable<? super E> whereAt = (Comparable<? super E>) elementData[parent];

                if (whereAt.compareTo(e) <= 0) {
                    break;
                } else {
                    elementData[index] = whereAt;
                }

                index = parent;
                parent = index / 2;
            }

            elementData[index] = e;
        }

        return true;
    }

    /**
     * Balances the binary heap after adding or removing element in the queue.
     *
     * @param index     where to start in list.
     */
    private void balance(int index) {
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        if (rightChild <= actualSize) {
            Comparable<? super E> left = (Comparable<? super E>) elementData[leftChild];
            E right = (E) elementData[rightChild];
            Comparable<? super E> parent = (Comparable<? super E>) elementData[index];

            if (left.compareTo(right) < 0) {
                if (parent.compareTo((E) left) > 0) {
                    elementData[leftChild] = parent;
                    elementData[index] = left;
                    balance(leftChild);
                }
            } else {
                if (parent.compareTo(right) > 0) {
                    elementData[rightChild] = parent;
                    elementData[index] = right;
                    balance(rightChild);
                }
            }
        } else if (leftChild == actualSize) {
            Comparable<? super E> left = (Comparable<? super E>) elementData[leftChild];
            Comparable<? super E> parent = (Comparable<? super E>) elementData[index];

            if (parent.compareTo((E) left) > 0) {
                elementData[leftChild] = parent;
                elementData[index] = left;
                balance(leftChild);
            }
        }
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
        return find(1, (E) o);
    }

    /**
     * Recursive part of contains method.
     */
    private boolean find(int index, E e) {
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        E whereAt = (E) elementData[index];
        boolean left = false;
        boolean right = false;

        if (whereAt.equals(e)) {
            return true;
        }

        if (leftChild <= actualSize) {
            left = find(leftChild, e);
        }

        if (rightChild <= actualSize) {
            right = find(rightChild, e);
        }

        return left || right;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * @return An array containing all of the elements in this list in proper
     * sequence
     */
    public Object[] toArray() {
        Object[] result = new Object[actualSize + 1];

        for (int i = 0; i < actualSize + 1; i++) {
            result[i] = elementData[i];
        }

        return result;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return The head of this queue, or {@code null} if this queue is empty
     */
    public E poll() {
        E result = peek();

        if (result != null) {
            elementData[1] = elementData[actualSize];
            actualSize--;

            if ((actualSize / 3) < elementData.length) {
                grow(1 / 2);
            }

            balance(1);
        }

        return result;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return The head of this queue, or {@code null} if this queue is empty
     */
    public E peek() {
        if (actualSize > 0) {
            return (E) elementData[1];
        } else {
            return null;
        }
    }
}
