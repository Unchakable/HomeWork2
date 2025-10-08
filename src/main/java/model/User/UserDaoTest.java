package model.User;

import model.Status.Status;

import java.time.LocalDateTime;

@Testcontainers
public class UserDaoTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Test
    void testSave() {
        User user = new User("Алексей", 39, "zhdjjklhzgjh@mail.ru", LocalDateTime.now());
        UserDao userDao = new UserDao();
        Status status = new Status("Программист", "Программирует на Java");
        user.setStatus(status);
        boolean resultSave = userDao.save(user);
        assertTrue(resultSave);
    }
}
