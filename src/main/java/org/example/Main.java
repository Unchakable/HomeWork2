package org.example;

import model.Status;
import model.StatusDao;
import model.User;
import model.UserDao;
import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        StatusDao statusDao = new StatusDao();

//        Status status = new Status("Программист", "Программирует на Java");
//        Status status1 = new Status("Тестировщик", "Тестирует");
//        User user = new User("Алексей", 39, "zhdjjklhzgjh@mail.ru", LocalDateTime.now());
//        user.setStatus(status);
//        userDao.save(user);
//        User user1 = new User("Иван", 18, "f;lgkijhk@mail.ru", LocalDateTime.now());
//        user1.setStatus(status1);
//        userDao.save(user1);
//        Optional<User> optional = userDao.get(1);
//        System.out.println(optional.map(User::getName).orElse("Unknown"));
//        Optional<User> optional1 = userDao.get(2);
//        if (optional.isPresent()){
//            User user = optional.get();
//            user.setName("Петр");
//            userDao.update(user);
//        }
//        userDao.deleteById(1);
        Optional<User> optional1 = userDao.get(2);
        System.out.println(optional1.toString());
//        optional1.ifPresent(userDao::delete);
    }
}