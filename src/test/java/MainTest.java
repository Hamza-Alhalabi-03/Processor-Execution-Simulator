import org.example.Main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testMain() {
        Main.main(null);
    }

    @Test
    public void testMain2() {
        assertEquals(1, 1);
    }
}
