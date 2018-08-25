package johneagle.routesolve.library;

import org.junit.Assert;
import org.junit.Test;

public class DataListTest {

    @Test
    public void isEmpty() {
        DataList<Integer> list = new DataList<>();

        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void add() {
        DataList<Integer> list = new DataList<>();

        list.add(1);
        list.add(2);
        list.add(12);
        list.add(1212);

        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.size() == 4);
        Assert.assertEquals(1212, (long) list.get(3));
        Assert.assertEquals(12, (long) list.get(2));
        Assert.assertEquals(2, (long) list.get(1));
        Assert.assertEquals(1, (long) list.get(0));
        Assert.assertNull(list.get(4));
    }

    @Test
    public void removeBig() {
        DataList<Integer> list = new DataList<>();

        list.add(1);
        list.add(2);
        list.add(12);
        list.add(1212);

        Object delete = 12;

        list.remove(1);
        list.remove(delete);

        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.size() == 2);
        Assert.assertEquals(1212, (long) list.get(1));
        Assert.assertEquals(1, (long) list.get(0));
        Assert.assertNull(list.get(3));
        Assert.assertNull(list.get(2));
    }

    @Test
    public void removeSmall() {
        DataList<Integer> list = new DataList<>();

        list.add(1);
        list.add(12);

        Object delete = 12;
        list.remove(delete);

        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.size() == 1);
        Assert.assertEquals(1, (long) list.peek());
    }

    @Test
    public void find() {
        DataList<Integer> list = new DataList<>();

        list.add(1);
        list.add(5);
        list.add(497);
        list.add(-12);

        Assert.assertTrue(list.contains(5));
        Assert.assertTrue(list.contains(-12));
        Assert.assertFalse(list.contains(111));
        Assert.assertFalse(list.contains(-222345));
    }

    @Test
    public void cleared() {
        DataList<Integer> list = new DataList<>();

        list.add(5);
        list.add(-12);

        Assert.assertTrue(list.contains(5));
        Assert.assertTrue(list.contains(-12));

        list.clear();

        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void replaceing() {
        DataList<Integer> list = new DataList<>();

        list.add(5);
        list.add(-12);

        Assert.assertTrue(list.contains(5));
        Assert.assertTrue(list.contains(-12));

        Integer set1 = list.set(0, 29);
        Integer set2 = list.set(1, -700);
        Integer set3 = list.set(6, 1);

        Assert.assertEquals(5, (long) set1);
        Assert.assertEquals(-12, (long) set2);
        Assert.assertNull(set3);

        Assert.assertTrue(list.get(0) == 29);
        Assert.assertTrue(list.get(1) == -700);
    }

    @Test
    public void toArray() {
        DataList<Integer> list = new DataList<>();

        list.add(5);
        list.add(-12);

        Object[] objects = list.toArray();
        Assert.assertTrue(objects.length == list.size());

        for (int i = 0; i < objects.length; i++) {
            Assert.assertTrue(objects[i] == list.get(i));
        }
    }

    @Test
    public void popOne() {
        DataList<Integer> list = new DataList<>();

        list.add(5);
        list.add(-12);

        Integer poped = list.pop();
        Integer peeked = list.peek();

        Assert.assertEquals(-12, (long) poped);
        Assert.assertEquals(5, (long) peeked);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void popTooMuch() {
        DataList<Integer> list = new DataList<>();

        list.add(5);
        list.add(-12);

        Integer poped = list.pop();
        Integer poped2 = list.pop();
        Integer poped3 = list.pop();

        Assert.assertEquals(-12, (long) poped);
        Assert.assertEquals(5, (long) poped2);
        Assert.assertNull(poped3);
    }
}