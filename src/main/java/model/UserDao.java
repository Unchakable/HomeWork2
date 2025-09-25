package model;

public class UserDao extends AbstractDao<User, Integer> {
    public UserDao() {
        super(User.class);
    }
}
