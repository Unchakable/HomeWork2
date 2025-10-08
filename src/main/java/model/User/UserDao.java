package model.User;

import model.AbstractDao;

public class UserDao extends AbstractDao<User, Integer> {
    public UserDao() {
        super(User.class);
    }
}
