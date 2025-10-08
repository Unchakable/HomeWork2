package model;

import java.util.List;
import java.util.Optional;

public interface Dao<T, Id> {
    Optional<T> get(Integer id);

    List<T> getAll();

    boolean save(T t);

    void update(T t);

    void delete(T t);

    void deleteById(Integer id);
}
