package components.sortingmachine;
import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Zach Baruch
 * @author Andrew Duffy
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String>, Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases ---------------------------------------------
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /*
     * My Test Cases ----------------------------------------------------------
     */

    @Test
    public final void testAddNonEmpty1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, true,
                "red", "green");

        m.add("green");
        assertEquals(mExp, m);
    }

    @Test
    public final void testAddNonEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "blue");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, true,
                "blue", "red", "green");

        m.add("green");
        assertEquals(mExp, m);
    }

    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false,
                "red", "green", "blue");

        m.changeToExtractionMode();

        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testIsInInsertionModeTrueEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true);

        assertEquals(true, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testIsInInsertionModeTrueNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true, "red",
                "green", "blue");

        assertEquals(true, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testIsInInsertionModeFalseEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false);

        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testIsInInsertionModeFalseNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false,
                "red", "green", "blue");

        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExp, m);
    }

    @Test
    public final void testOrderInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true);

        assertEquals(mExp.order(), m.order());
        assertEquals(mExp, m);
    }

    @Test
    public final void testOrderInsertionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true, "red",
                "green", "blue");

        assertEquals(mExp.order(), m.order());
        assertEquals(mExp, m);
    }

    @Test
    public final void testOrderExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false);

        assertEquals(mExp.order(), m.order());
        assertEquals(mExp, m);
    }

    @Test
    public final void testOrderExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false,
                "red", "green", "blue");

        assertEquals(mExp.order(), m.order());
        assertEquals(mExp, m);
    }

    @Test
    public final void testSizeInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true);

        assertEquals(mExp.size(), m.size());
        assertEquals(mExp, m);
    }

    @Test
    public final void testSizeInsertionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true, "red",
                "green", "blue");

        assertEquals(mExp.size(), m.size());
        assertEquals(mExp, m);
    }

    @Test
    public final void testSizeExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false);

        assertEquals(mExp.size(), m.size());
        assertEquals(mExp, m);
    }

    @Test
    public final void testSizeExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false,
                "red", "green", "blue");

        assertEquals(mExp.size(), m.size());
        assertEquals(mExp, m);
    }

    @Test
    public final void testRemoveFirstSize1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, false);

        String s = m.removeFirst();

        assertEquals("green", s);
        assertEquals(mExp, m);
    }

    @Test
    public final void testRemoveFirstSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, false,
                "red");

        String s = m.removeFirst();

        assertEquals("green", s);
        assertEquals(mExp, m);
    }

    @Test
    public final void testRemoveFirstSize3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red", "yellow");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, false,
                "red", "yellow");

        String s = m.removeFirst();

        assertEquals("green", s);
        assertEquals(mExp, m);
    }

    @Test
    public final void testRemoveFirstSize6Unsorted() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red", "yellow", "blue", "orange", "crimson");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, false,
                "green", "red", "yellow", "orange", "crimson");

        String s = m.removeFirst();

        assertEquals("blue", s);
        assertEquals(mExp, m);
    }

    @Test
    public final void testRemoveFirstSize3Repeating() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red", "green");
        SortingMachine<String> mExp = this.createFromArgsTest(ORDER, false,
                "red", "green");

        String s = m.removeFirst();

        assertEquals("green", s);
        assertEquals(mExp, m);
    }

}
