package zhukova.victoria.kursovaya.service;

import java.util.List;

public interface CrudService<E,D> {
    E save(D dto);
    E update(D dto);
    void delete(D dto);
    E findById(int id);
    List<E> findAll();
}
