package components.deque;

/**
 * Customized JUnit test fixture for {@code Deque3}.
 *
 * @author Zach Baruch
 *
 */
public class Deque3Test extends DequeTest {

    @Override
    protected final Deque<String> constructorTest() {
        return new Deque3<>();
    }

    @Override
    protected final Deque<String> constructorRef() {
        return new Deque1L<>();
    }

}
