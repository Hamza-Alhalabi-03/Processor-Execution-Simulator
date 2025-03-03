import org.example.Clock;
import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMain {

    @BeforeEach
    public void setUp() {
        Clock.reset();
        System.out.println("\n\n\nStarting a new test case...\n\n\n");
    }

    @AfterEach
    public void destroy() {
        System.out.println("\n\n\nTest case completed.\n\n\n");
    }

    @Test
    public void testCaseOne() {
        String[] arguments = {"2", "10", "input-tasks-one.txt"};
        Main.main(arguments);
    }

    @Test
    public void testCaseTwo() {
        String[] arguments = {"4", "12", "input-tasks-two.txt"};
        Main.main(arguments);
    }
}
