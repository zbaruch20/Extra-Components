package components.deque;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Deque<String>}'s constructor and kernel
 * methods.
 *
 * @author Zach Baruch
 *
 */
public abstract class DequeTest {

    /**
     * Invokes the appropriate {@code Deque} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new deque
     * @ensures constructorTest = <>
     */
    protected abstract Deque<String> constructorTest();

    /**
     * Invokes the appropriate {@code Deque} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new deque
     * @ensures constructorRed = <>
     */
    protected abstract Deque<String> constructorRef();

    /**
     * Creates and returns a {@code Deqie<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the deque
     * @return the constructed deque
     * @ensures createFromArgsTest = [entries in args]
     */
    private Deque<String> createFromArgsTest(String... args) {
        assert args != null : "Violation of: args is not null";

        Deque<String> deque = this.constructorTest();
        for (String x : args) {
            deque.pushBack(x);
        }
        return deque;
    }

    /**
     * Creates and returns a {@code Deqie<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the deque
     * @return the constructed deque
     * @ensures createFromArgsTest = [entries in args]
     */
    private Deque<String> createFromArgsRef(String... args) {
        assert args != null : "Violation of: args is not null";

        Deque<String> deque = this.constructorTest();
        for (String x : args) {
            deque.pushBack(x);
        }
        return deque;
    }

    /*
     * Constructor tests ------------------------------------------------------
     */

    @Test
    public void testNoArgumentConstructor() {
        Deque<String> dTest = this.constructorTest();
        Deque<String> dRef = this.constructorRef();

        assertEquals(dRef, dTest);
    }

//    @Test
//    public void testCreateFromArgsEmpty() {
//        Deque<String> dTest = this.createFromArgsTest();
//        Deque<String> dRef = this.createFromArgsRef();
//
//        assertEquals(dRef, dTest);
//    }
//
//    @Test
//    public void testCreateFromArgsNonEmpty1() {
//        Deque<String> dTest = this.createFromArgsTest("one");
//        Deque<String> dRef = this.createFromArgsRef("one");
//
//        assertEquals(dRef, dTest);
//    }
//
//    @Test
//    public void testCreateFromArgsNonEmpty3() {
//        Deque<String> dTest = this.createFromArgsTest("one", "two", "three");
//        Deque<String> dRef = this.createFromArgsRef("one", "two", "three");
//
//        assertEquals(dRef, dTest);
//    }

    /*
     * Kernel tests -----------------------------------------------------------
     */

    @Test
    public void testPushFrontEmpty() {
        Deque<String> dTest = this.createFromArgsTest();
        Deque<String> dRef = this.createFromArgsRef();

        dTest.pushFront("one");
        dRef.pushFront("one");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPushFrontNonEmpty1() {
        Deque<String> dTest = this.createFromArgsTest("two");
        Deque<String> dRef = this.createFromArgsRef("two");

        dTest.pushFront("one");
        dRef.pushFront("one");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPushFrontNonEmpty2() {
        Deque<String> dTest = this.createFromArgsTest("two", "three");
        Deque<String> dRef = this.createFromArgsRef("two", "three");

        dTest.pushFront("one");
        dRef.pushFront("one");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPushBackEmpty() {
        Deque<String> dTest = this.createFromArgsTest();
        Deque<String> dRef = this.createFromArgsRef();

        dTest.pushBack("three");
        dRef.pushBack("three");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPushBackNonEmpty1() {
        Deque<String> dTest = this.createFromArgsTest("two");
        Deque<String> dRef = this.createFromArgsRef("two");

        dTest.pushBack("three");
        dRef.pushBack("three");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPushBackNonEmpty2() {
        Deque<String> dTest = this.createFromArgsTest("one", "two");
        Deque<String> dRef = this.createFromArgsRef("one", "two");

        dTest.pushBack("three");
        dRef.pushBack("three");

        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopFrontLeavingEmpty() {
        Deque<String> dTest = this.createFromArgsTest("one");
        Deque<String> dRef = this.createFromArgsRef("one");

        String frontTest = dTest.popFront();
        String frontRef = dRef.popFront();

        assertEquals(frontRef, frontTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopFrontLeavingNonEmpty1() {
        Deque<String> dTest = this.createFromArgsTest("one", "two");
        Deque<String> dRef = this.createFromArgsRef("one", "two");

        String frontTest = dTest.popFront();
        String frontRef = dRef.popFront();

        assertEquals(frontRef, frontTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopFrontLeavingNonEmpty2() {
        Deque<String> dTest = this.createFromArgsTest("one", "two", "three");
        Deque<String> dRef = this.createFromArgsRef("one", "two", "three");

        String frontTest = dTest.popFront();
        String frontRef = dRef.popFront();

        assertEquals(frontRef, frontTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopBackLeavingEmpty() {
        Deque<String> dTest = this.createFromArgsTest("one");
        Deque<String> dRef = this.createFromArgsRef("one");

        String backTest = dTest.popBack();
        String backRef = dRef.popBack();

        assertEquals(backRef, backTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopBackLeavingNonEmpty1() {
        Deque<String> dTest = this.createFromArgsTest("one", "two");
        Deque<String> dRef = this.createFromArgsRef("one", "two");

        String backTest = dTest.popBack();
        String backRef = dRef.popBack();

        assertEquals(backRef, backTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testPopBackLeavingNonEmpty2() {
        Deque<String> dTest = this.createFromArgsTest("one", "two", "three");
        Deque<String> dRef = this.createFromArgsRef("one", "two", "three");

        String backTest = dTest.popBack();
        String backRef = dRef.popBack();

        assertEquals(backRef, backTest);
        assertEquals(dRef, dTest);
    }

    @Test
    public void testLengthEmpty() {
        Deque<String> dTest = this.createFromArgsTest();
        Deque<String> dRef = this.createFromArgsRef();

        assertEquals(dRef.length(), dTest.length());
        assertEquals(dRef, dTest);
    }

    @Test
    public void testLengthNonEmpty1() {
        Deque<String> dTest = this.createFromArgsTest("one");
        Deque<String> dRef = this.createFromArgsRef("one");

        assertEquals(dRef.length(), dTest.length());
        assertEquals(dRef, dTest);
    }

    @Test
    public void testLengthNonEmpty2() {
        Deque<String> dTest = this.createFromArgsTest("one", "two");
        Deque<String> dRef = this.createFromArgsRef("one", "two");

        assertEquals(dRef.length(), dTest.length());
        assertEquals(dRef, dTest);
    }

    @Test
    public void testLengthNonEmpty10() {
        Deque<String> dTest = this.createFromArgsTest("one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine", "ten");
        Deque<String> dRef = this.createFromArgsRef("one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine", "ten");

        assertEquals(dRef.length(), dTest.length());
        assertEquals(dRef, dTest);
    }

}
