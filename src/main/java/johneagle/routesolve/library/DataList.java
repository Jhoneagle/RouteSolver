package johneagle.routesolve.library;

import johneagle.routesolve.interfaces.List;
import johneagle.routesolve.interfaces.Pile;

/**
 * Class that works just like any ArrayList or Stack object. Implements most of basic methods needed for normal use.
 *
 * @param <E>   type allowed to have.
 *
 * @author Johneagle
 */
public class DataList<E> implements List<E>, Pile<E> {

    private Object[] elementData;
    private int maxSize;
    private int actualSize;

    public DataList() {
        maxSize = 10;
        actualSize = 0;
        elementData = new Object[maxSize];
    }

    /**
     * Resize the array so that all elements can fit.
     */
    private void grow(double with) {
        int newMax = (int) (maxSize * with);

        if (newMax >= 10) {
            maxSize = newMax;
            Object[] newList = new Object[maxSize];

            for (int i = 0; i < actualSize; i++) {
                newList[i] = elementData[i];
            }

            elementData = newList;
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return The number of elements in this list
     */
    @Override
    public int size() {
        return actualSize;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return actualSize == 0;
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
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * @return An array containing all of the elements in this list in proper
     * sequence
     */
    public Object[] toArray() {
        Object[] result = new Object[actualSize];

        for (int i = 0; i < actualSize; i++) {
            result[i] = elementData[i];
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
    @Override
    public boolean add(E e) {
        if (actualSize >= maxSize) {
            grow(2);
        }

        elementData[actualSize] = e;
        actualSize++;

        return true;
    }

    /**
     * removes item in the place assigned by param and returns it.
     *
     * @param index     The point in array where element needs to be removed.
     *
     * @return Element that was just removed.
     */
    @Override
    public E remove(int index) {
        if (actualSize < (maxSize / 3)) {
            double half = 1.0 / 2.0;
            grow(half);
        }

        E found = null;

        if (index >= 0 && index < actualSize) {
            found = (E) elementData[index];
            actualSize--;

            for (int i = index; i < actualSize; i++) {
                elementData[i] = elementData[i + 1];
            }

            elementData[actualSize + 1] = null;
        }

        return found;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain
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
        if (actualSize < (maxSize / 3)) {
            double half = 1 / 2;
            grow(half);
        }

        for (int i = 0; i < actualSize; i++) {
            Object current = elementData[i];
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
        actualSize = 0;
        maxSize = 10;
        elementData = new Object[maxSize];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return The element at the specified position in this list
     */
    @Override
    public E get(int index) {
        if (index >= 0 && index < actualSize) {
            return (E) elementData[index];
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
     * @return The element previously at the specified position
     */
    @Override
    public E set(int index, E element) {
        if (index >= 0 && index < actualSize) {
            E result = (E) elementData[index];
            elementData[index] = element;

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
     * @return The index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < actualSize; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < actualSize; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Adds the element top of the list and returns the new element back.
     *
     * @param e  Element wanted to add on the head of list.
     *
     * @return Element added or null if failed to add.
     */
    @Override
    public E push(E e) {
        try {
            add(e);
            return e;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Removes the top/last element if there is any and returns that.
     *
     * @see DataList#peek()
     * @see DataList#remove(int)
     *
     * @return Gives the top element so last added one or null if empty.
     */
    @Override
    public E pop() {
        E result = peek();
        if (result != null) {
            remove(actualSize - 1);
        }

        return result;
    }

    /**
     * Looks the top/last element if not empty.
     *
     * @return Gives the top element so last added one or null if empty.
     */
    @Override
    public E peek() {
        if (actualSize > 0) {
            return (E) elementData[actualSize - 1];
        } else {
            return null;
        }
    }
}
