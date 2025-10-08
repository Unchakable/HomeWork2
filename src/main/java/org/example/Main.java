package org.example;

import model.Status.Status;
import model.Status.StatusDao;
import model.User.User;
import model.User.UserDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        Status status = new Status("Программист", "Программирует на Java");
        Status status1 = new Status("Тестировщик", "Тестирует");
        User user = new User("Алексей", 39, "zhdjjklhzgjh@mail.ru", LocalDateTime.now());
        user.setStatus(status);
        userDao.save(user);
        User user1 = new User("Иван", 18, "f;lgkijhk@mail.ru", LocalDateTime.now());
        user1.setStatus(status1);
        userDao.save(user1);
        Optional<User> optional = userDao.get(1);
        System.out.println(optional.map(User::getName).orElse("Unknown"));
        Optional<User> optional1 = userDao.get(6);
        if (optional1.isPresent()) {
            User user = optional1.get();
            user.setName("Петр");
            userDao.update(user);
        }
        userDao.deleteById(6);
        Optional<User> optional1 = userDao.get(5);
        System.out.println(optional1.toString());
        optional1.ifPresent(userDao::delete);
    }
}