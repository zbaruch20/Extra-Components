package components.deque;

/**
 * Customized JUnit test fixture for {@code Deque4}.
 *
 * @author Zach Baruch
 *
 */
public class Deque4Test extends DequeTest {

    @Override
    protected final Deque<String> constructorTest() {
        return new Deque4<>();
    }

    @Override
    protected final Deque<String> constructorRef() {
        return new Deque1L<>();
    }

}
