package components.deque;

/**
 * Customized JUnit test fixture for {@code Deque2}.
 *
 * @author Zach Baruch
 *
 */
public class Deque2Test extends DequeTest {

    @Override
    protected final Deque<String> constructorTest() {
        return new Deque2<>();
    }

    @Override
    protected final Deque<String> constructorRef() {
        return new Deque1L<>();
    }

}
