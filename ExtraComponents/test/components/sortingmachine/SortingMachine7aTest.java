package components.sortingmachine;

import java.util.Comparator;

/**
 * Customized JUnit test fixture for {@code SortingMachine7a}.
 */
public final class SortingMachine7aTest extends SortingMachineTest {

    @Override
    protected SortingMachine<String> constructorTest(Comparator<String> order) {
        return new SortingMachine7a<String>(order);
    }

    @Override
    protected SortingMachine<String> constructorRef(Comparator<String> order) {
        return new SortingMachine1L<String>(order);
    }

}
