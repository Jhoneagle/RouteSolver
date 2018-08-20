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
        this.actualSize = 0;
        this.elementData = new Object[10];
    }

    /**
     * Resize the array so that all elements can fit.
     */
    private void grow(double with) {
        int newMax = (int) (this.elementData.length * with);

        if (newMax >= 10) {
            Object[] newList = new Object[newMax];

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
        if (this.actualSize == 0) {
            this.elementData[1] = e;
            this.actualSize++;
            return true;
        } else {
            this.actualSize++;
            int index = this.actualSize;
            int parent = index / 2;

            if (this.actualSize >= this.elementData.length) {
                grow(4);
            }

            while (index > 1) {
                Comparable<? super E> whereAt = (Comparable<? super E>) this.elementData[parent];

                if (whereAt.compareTo(e) <= 0) {
                    break;
                } else {
                    this.elementData[index] = whereAt;
                }

                index = parent;
                parent = index / 2;
            }

            this.elementData[index] = e;

            balance(index);
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

        if (rightChild <= this.actualSize) {
            Comparable<? super E> left = (Comparable<? super E>) this.elementData[leftChild];
            E right = (E) this.elementData[rightChild];
            Comparable<? super E> parent = (Comparable<? super E>) this.elementData[index];

            if (left.compareTo(right) < 0) {
                if (parent.compareTo((E) left) > 0) {
                    this.elementData[leftChild] = parent;
                    this.elementData[index] = left;
                    balance(leftChild);
                }
            } else {
                if (parent.compareTo(right) > 0) {
                    this.elementData[rightChild] = parent;
                    this.elementData[index] = right;
                    balance(rightChild);
                }
            }
        } else if (leftChild == this.actualSize) {
            Comparable<? super E> left = (Comparable<? super E>) this.elementData[leftChild];
            Comparable<? super E> parent = (Comparable<? super E>) this.elementData[index];

            if (parent.compareTo((E) left) > 0) {
                this.elementData[leftChild] = parent;
                this.elementData[index] = left;
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

        E whereAt = (E) this.elementData[index];
        boolean left = false;
        boolean right = false;

        if (whereAt.equals(e)) {
            return true;
        }

        if (leftChild <= this.actualSize) {
            left = find(leftChild, e);
        }

        if (rightChild <= this.actualSize) {
            right = find(rightChild, e);
        }

        return left || right;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     */
    public Object[] toArray() {
        Object[] result = new Object[this.actualSize + 1];

        for (int i = 0; i < this.actualSize + 1; i++) {
            result[i] = this.elementData[i];
        }

        return result;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public E poll() {
        E result = peek();

        if (result != null) {
            this.elementData[1] = this.elementData[this.actualSize];
            this.actualSize--;

            if ((this.actualSize / 3) < this.elementData.length) {
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
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public E peek() {
        if (this.actualSize > 0) {
            return (E) this.elementData[1];
        } else {
            return null;
        }
    }
}
