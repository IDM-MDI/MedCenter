package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.UserComplaintDto;
import zhukova.victoria.kursovaya.model.entity.UserComplaint;
import zhukova.victoria.kursovaya.model.mapper.impl.UserComplaintModelMapper;
import zhukova.victoria.kursovaya.repository.UserComplaintRepo;

import java.util.List;

@Service
public class UserComplaintService implements CrudService<UserComplaint, UserComplaintDto>{
    private final UserComplaintRepo repository;
    private final UserComplaintModelMapper mapper;

    @Autowired
    public UserComplaintService(UserComplaintRepo repository, UserComplaintModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserComplaint save(UserComplaintDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public UserComplaint update(UserComplaintDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(UserComplaintDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public UserComplaint findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<UserComplaint> findAll() {
        return repository.findAll();
    }
}
