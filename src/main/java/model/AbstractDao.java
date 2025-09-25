package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public abstract class AbstractDao<T, Id> implements Dao<T, Id> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
    private final Class<T> clazz;
    private Session session;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<T> get(Integer id) {
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            T entity = session.find(clazz, id);
            session.close();
            if (entity == null) {
                logger.debug("{} with ID: {} not found", clazz.getSimpleName(), id);
                return Optional.empty();
            }
            return Optional.of(entity);
        } catch (Exception e) {
            logger.error("Error finding {} by ID: {}", clazz.getSimpleName(), id, e);
            return Optional.empty();
        } finally {
            session.close();
        }
    }


    @Override
    public List<T> getAll() {
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            Query<T> query = session.createQuery("FROM " + clazz.getName(), clazz);
            session.close();
            return query.list();
        } catch (Exception e) {
            logger.error("Error finding all {}", clazz.getSimpleName(), e);
            return List.of();
        } finally {
            session.close();
        }
    }

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.debug("Transaction rolled back {}", clazz.getSimpleName(), e);
                } catch (Exception ex) {
                    logger.error("Error transaction rollback {}", clazz.getSimpleName(), e);
                }
            }
            logger.error("Error saving {}", clazz.getSimpleName(), e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.debug("Transaction rolled back {}", clazz.getSimpleName(), e);
                } catch (Exception ex) {
                    logger.error("Error transaction rollback {}", clazz.getSimpleName(), e);
                }
            }
            logger.error("Error updating {}", clazz.getSimpleName(), e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.debug("Transaction rolled back {}", clazz.getSimpleName(), e);
                } catch (Exception ex) {
                    logger.error("Error transaction rollback {}", clazz.getSimpleName(), e);
                }
            }
            logger.error("Error deleting {}", clazz.getSimpleName(), e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Transaction transaction = null;
        try {
            session = SessionFactoryHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            T entity = session.find(clazz, id);
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.debug("Transaction rolled back {}", clazz.getSimpleName(), e);
                } catch (Exception ex) {
                    logger.error("Error transaction rollback {}", clazz.getSimpleName(), e);
                }
            }
            logger.error("Error deletingById {}", clazz.getSimpleName(), e);
        }finally {
            session.close();
        }
    }
}
