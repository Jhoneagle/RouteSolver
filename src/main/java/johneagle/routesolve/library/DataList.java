package johneagle.routesolve.library;

import java.util.*;

/**
 * Class that works just like any ArrayList or Stack object. Implements most of basic methods needed for normal use.
 *
 * @param <E>   type allowed to have.
 *
 * @author Johneagle
 */
public class DataList<E> {

    private Object[] elementData;
    private int maxSize;
    private int actualSize;

    public DataList() {
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
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return this.actualSize;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return this.actualSize == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     */
    public Object[] toArray() {
        Object[] result = new Object[this.actualSize];
        for (int i = 0; i < this.actualSize; i++) {
            result[i] = this.elementData[i];
        }
        return result;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * @param e element to be appended to this list
     * @return {@code true}
     */
    public boolean add(E e) {
        if (this.actualSize >= this.maxSize) {
            grow(2);
        }

        this.elementData[this.actualSize] = e;
        this.actualSize++;

        return true;
    }

    /**
     * removes item in the place assigned by param and returns it.
     *
     * @param index     The point in array where element needs to be removed.
     *
     * @return element that was just removed.
     */
    public E remove(int index) {
        if (this.actualSize < (this.maxSize / 2)) {
            double half = 1.0 / 2.0;
            grow(half);
        }

        E found = null;

        if (index >= 0 && index < this.actualSize) {
            found = (E) this.elementData[index];
            this.actualSize--;

            for (int i = index; i < this.actualSize; i++) {
                this.elementData[i] = this.elementData[i + 1];
            }

            this.elementData[this.actualSize + 1] = null;
        }

        return found;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(Object o) {
        if (this.actualSize < (this.maxSize / 2)) {
            double half = 1 / 2;
            grow(half);
        }

        for (int i = 0; i < this.actualSize; i++) {
            Object current = this.elementData[i];
            if (o.equals(current)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    public void clear() {
        this.actualSize = 0;
        this.maxSize = 10;
        this.elementData = new Object[this.maxSize];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index) {
        if (index >= 0 && index < this.actualSize) {
            return (E) this.elementData[index];
        } else {
            return null;
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public E set(int index, E element) {
        if (index >= 0 && index < this.actualSize) {
            E result = (E) this.elementData[index];
            this.elementData[index] = element;

            return result;
        } else {
            return null;
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < this.actualSize; i++) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.actualSize; i++) {
                if (o.equals(this.elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * removes the top/last element if there is any and returns that.
     *
     * @see DataList#peek()
     * @see DataList#remove(int)
     *
     * @return gives the top element so last added one or null if empty.
     */
    public E pop() {
        E result = peek();
        if (result != null) {
            remove(this.actualSize - 1);
        }

        return result;
    }

    /**
     * Looks the top/last element if not empty.
     *
     * @return gives the top element so last added one or null if empty.
     */
    public E peek() {
        if (this.actualSize > 0) {
            return (E) this.elementData[this.actualSize - 1];
        } else {
            return null;
        }
    }
}
