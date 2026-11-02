import com.islam.Priority;
import com.islam.TaskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test
    void addBlankTitleThrows() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.add("   ", Priority.LOW));
    }
}
