package johneagle.routesolve.library;

import johneagle.routesolve.domain.Chell;
import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {

    @Test
    public void create() {
        MinHeap<String> test = new MinHeap<>();

        Assert.assertNotNull(test);
        Assert.assertTrue(test.isEmpty());
    }

    @Test
    public void returnNull() {
        MinHeap<Chell> test = new MinHeap<>();

        Chell isPollNull = test.poll();
        Chell isPeekNull = test.peek();

        Assert.assertNull(isPeekNull);
        Assert.assertNull(isPollNull);
    }

    @Test
    public void smallInsert() {
        MinHeap<Integer> test = new MinHeap<>();

        test.add(43);
        test.add(100);
        test.add(0);

        Assert.assertEquals(3, test.size());
    }

    @Test
    public void bigInsert() {
        MinHeap<Integer> test = new MinHeap<>();
        Integer[] numbers = {11, 1, 2, -8, 23, 31, 99, 59, 72, -4};

        test.add(numbers[1]); // 1
        test.add(numbers[4]); // 23
        test.add(numbers[2]); // 2
        test.add(numbers[7]); // 59
        test.add(numbers[3]); // -8
        test.add(numbers[5]); // 31
        test.add(numbers[6]); // 99
        test.add(numbers[8]); // 72
        test.add(numbers[0]); // 11
        test.add(numbers[9]); // -4

        Assert.assertEquals(10, test.size());
    }

    @Test
    public void smallPoll() {
        MinHeap<Double> test = new MinHeap<>();
        Double[] numbers = {-3.14, 7.0 / 8.0, 12.45};

        test.add(numbers[1]);
        test.add(numbers[2]);
        test.add(numbers[0]);

        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(test.poll() == numbers[i]);
        }
    }

    @Test
    public void bigPoll() {
        MinHeap<Integer> test = new MinHeap<>();
        Integer[] numbers = {0, 1, 2, 7, 23, 31, 43, 59, 100};

        test.add(numbers[1]); // 1
        test.add(numbers[4]); // 23
        test.add(numbers[2]); // 2
        test.add(numbers[7]); // 59
        test.add(numbers[3]); // 7
        test.add(numbers[5]); // 31
        test.add(numbers[6]); // 43
        test.add(numbers[8]); // 100
        test.add(numbers[0]); // 0

        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(numbers[i], test.poll());
        }
    }

    @Test
    public void peekNoDel() {
        MinHeap<Integer> test = new MinHeap<>();

        test.add(112);
        test.add(7);
        test.add(666);

        Assert.assertEquals(3, test.size());
        Integer peek = test.peek();
        Assert.assertEquals(3, test.size());
    }

    @Test
    public void peekTop() {
        MinHeap<Integer> test = new MinHeap<>();

        test.add(112);
        test.add(7);
        test.add(666);

        Assert.assertEquals(7, (long) test.peek());
    }

    @Test
    public void contains1() {
        MinHeap<String> test = new MinHeap<>();

        test.add("moi");
        test.add("hola");
        test.add("hej");
        test.add("tere");
        test.add("hallo");
        test.add("hae");
        test.add("he");

        Assert.assertTrue(test.contains("tere"));
        Assert.assertTrue(test.contains("hola"));
        Assert.assertTrue(test.contains("he"));

        Assert.assertFalse(test.contains("nope"));
    }

    @Test
    public void contains2() {
        MinHeap<Chell> test = new MinHeap<>();

        test.add(new Chell(0,0));
        test.add(new Chell(5,1));
        test.add(new Chell(6,12));
        test.add(new Chell(5,11));
        test.add(new Chell(7,9));
        test.add(new Chell(1,3));
        test.add(new Chell(2,5));
        test.add(new Chell(8,8));

        Assert.assertTrue(test.contains(new Chell(5,1)));
        Assert.assertTrue(test.contains(new Chell(8,8)));
        Assert.assertTrue(test.contains(new Chell(0,0)));

        Assert.assertFalse(test.contains(new Chell(15,200)));
    }

    @Test
    public void toArray() {
        MinHeap<String> test = new MinHeap<>();

        test.add("d");
        test.add("a");
        test.add("c");
        test.add("b");
        test.add("g");
        test.add("e");
        test.add("f");

        Object[] strings = test.toArray();
        String result = "";
        String expected = "abcdgef";

        for (int i = 1; i < strings.length; i++) {
            result += strings[i];
        }

        Assert.assertEquals(expected, result);
    }
}