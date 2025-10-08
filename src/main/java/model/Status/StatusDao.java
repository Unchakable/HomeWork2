package model.Status;

import model.AbstractDao;

public class StatusDao extends AbstractDao<Status, Integer> {
    public StatusDao() {
        super(Status.class);
    }
}
