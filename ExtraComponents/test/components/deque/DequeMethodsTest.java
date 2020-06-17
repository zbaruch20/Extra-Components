package components.deque;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Deque} methods, tested on {@code Deque1L}.
 *
 * @author Zach Baruch
 *
 */
public class DequeMethodsTest {

    /*
     * Testing creating from args ---------------------------------------------
     */

    /**
     * Creates and reports a {@code Deque} from string arguments.
     *
     * @param args
     *            elements of {@code Deque}
     * @return a {@code Deque} from string arguments
     * @ensures createFromArgs = args
     */
    private Deque<String> createFromArgs(String... args) {
        assert args != null : "Violation of: args is not null";

        Deque<String> deque = new Deque1L<>();
        for (String x : args) {
            deque.pushBack(x);
        }
        return deque;
    }

//    @Test
//    public void testCreateFromArgs() {
//        Deque<String> d = this.createFromArgs("one", "two", "three");
//        Deque<String> dExp = new Deque1L<>();
//        dExp.pushBack("one");
//        dExp.pushBack("two");
//        dExp.pushBack("three");
//
//        assertEquals(dExp, d);
//    }

    /*
     * Kernel tests -----------------------------------------------------------
     */

    @Test
    public void testPushFrontEmpty() {
        Deque<String> d = this.createFromArgs();
        Deque<String> dExp = this.createFromArgs("one");

        d.pushFront("one");

        assertEquals(dExp, d);
    }

    @Test
    public void testPushFrontNonEmpty1() {
        Deque<String> d = this.createFromArgs("two");
        Deque<String> dExp = this.createFromArgs("one", "two");

        d.pushFront("one");

        assertEquals(dExp, d);
    }

    @Test
    public void testPushFrontNonEmpty2() {
        Deque<String> d = this.createFromArgs("two", "three");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        d.pushFront("one");

        assertEquals(dExp, d);
    }

    @Test
    public void testPushBackEmpty() {
        Deque<String> d = this.createFromArgs();
        Deque<String> dExp = this.createFromArgs("one");

        d.pushBack("one");

        assertEquals(dExp, d);
    }

    @Test
    public void testPushBackNonEmpty1() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs("one", "two");

        d.pushBack("two");

        assertEquals(dExp, d);
    }

    @Test
    public void testPushBackNonEmpty2() {
        Deque<String> d = this.createFromArgs("one", "two");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        d.pushBack("three");

        assertEquals(dExp, d);
    }

    @Test
    public void testPopFrontLeavingEmpty() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs();

        String front = d.popFront();

        assertEquals("one", front);
        assertEquals(dExp, d);
    }

    @Test
    public void testPopFrontLeavingNonEmpty1() {
        Deque<String> d = this.createFromArgs("one", "two");
        Deque<String> dExp = this.createFromArgs("two");

        String front = d.popFront();

        assertEquals("one", front);
        assertEquals(dExp, d);
    }

    @Test
    public void testPopFrontLeavingNonEmpty2() {
        Deque<String> d = this.createFromArgs("one", "two", "three");
        Deque<String> dExp = this.createFromArgs("two", "three");

        String front = d.popFront();

        assertEquals("one", front);
        assertEquals(dExp, d);
    }

    @Test
    public void testPopBackLeavingEmpty() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs();

        String back = d.popBack();

        assertEquals("one", back);
        assertEquals(dExp, d);
    }

    @Test
    public void testPopBackLeavingNonEmpty1() {
        Deque<String> d = this.createFromArgs("one", "two");
        Deque<String> dExp = this.createFromArgs("one");

        String back = d.popBack();

        assertEquals("two", back);
        assertEquals(dExp, d);
    }

    @Test
    public void testPopBackLeavingNonEmpty2() {
        Deque<String> d = this.createFromArgs("one", "two", "three");
        Deque<String> dExp = this.createFromArgs("one", "two");

        String back = d.popBack();

        assertEquals("three", back);
        assertEquals(dExp, d);
    }

    @Test
    public void testLengthEmpty() {
        Deque<String> d = this.createFromArgs();
        Deque<String> dExp = this.createFromArgs();

        assertEquals(0, d.length());
        assertEquals(dExp, d);
    }

    @Test
    public void testLengthNonEmpty1() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs("one");

        assertEquals(1, d.length());
        assertEquals(dExp, d);
    }

    @Test
    public void testLengthNonEmpty2() {
        Deque<String> d = this.createFromArgs("one", "two");
        Deque<String> dExp = this.createFromArgs("one", "two");

        assertEquals(2, d.length());
        assertEquals(dExp, d);
    }

    @Test
    public void testLengthNonEmpty10() {
        Deque<String> d = this.createFromArgs("one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten");
        Deque<String> dExp = this.createFromArgs("one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten");

        assertEquals(10, d.length());
        assertEquals(dExp, d);
    }

    /*
     * Secondary tests --------------------------------------------------------
     */

    @Test
    public void testFlipEmpty() {
        Deque<String> d = this.createFromArgs();
        Deque<String> dExp = this.createFromArgs();

        d.flip();

        assertEquals(dExp, d);
    }

    @Test
    public void testFlipNonEmpty1() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs("one");

        d.flip();

        assertEquals(dExp, d);
    }

    @Test
    public void testFlipNonEmpty2() {
        Deque<String> d = this.createFromArgs("one", "two");
        Deque<String> dExp = this.createFromArgs("two", "one");

        d.flip();

        assertEquals(dExp, d);
    }

    @Test
    public void testFlipNonEmpty5() {
        Deque<String> d = this.createFromArgs("one", "two", "three", "four",
                "five");
        Deque<String> dExp = this.createFromArgs("five", "four", "three", "two",
                "one");

        d.flip();

        assertEquals(dExp, d);
    }

    @Test
    public void testFront1() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs("one");

        assertEquals("one", d.front());
        assertEquals(dExp, d);
    }

    @Test
    public void testFront3() {
        Deque<String> d = this.createFromArgs("one", "two", "three");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        assertEquals("one", d.front());
        assertEquals(dExp, d);
    }

    @Test
    public void testBack1() {
        Deque<String> d = this.createFromArgs("one");
        Deque<String> dExp = this.createFromArgs("one");

        assertEquals("one", d.back());
        assertEquals(dExp, d);
    }

    @Test
    public void testBack3() {
        Deque<String> d = this.createFromArgs("one", "two", "three");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        assertEquals("three", d.back());
        assertEquals(dExp, d);
    }

    @Test
    public void testReplaceFront1() {
        Deque<String> d = this.createFromArgs("two");
        Deque<String> dExp = this.createFromArgs("one");

        String front = d.replaceFront("one");

        assertEquals("two", front);
        assertEquals(dExp, d);
    }

    @Test
    public void testReplaceFront3() {
        Deque<String> d = this.createFromArgs("four", "two", "three");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        String front = d.replaceFront("one");

        assertEquals("four", front);
        assertEquals(dExp, d);
    }

    @Test
    public void testReplaceBack1() {
        Deque<String> d = this.createFromArgs("two");
        Deque<String> dExp = this.createFromArgs("one");

        String back = d.replaceBack("one");

        assertEquals("two", back);
        assertEquals(dExp, d);
    }

    @Test
    public void testReplaceBack3() {
        Deque<String> d = this.createFromArgs("one", "two", "four");
        Deque<String> dExp = this.createFromArgs("one", "two", "three");

        String back = d.replaceBack("three");

        assertEquals("four", back);
        assertEquals(dExp, d);
    }

}
