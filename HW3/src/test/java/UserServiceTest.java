import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void getAge() throws Exception {
        assertEquals(30,userService.getAge());
    }

}