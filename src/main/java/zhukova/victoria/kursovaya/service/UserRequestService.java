package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.UserRequestDto;
import zhukova.victoria.kursovaya.model.entity.UserRequest;
import zhukova.victoria.kursovaya.model.mapper.impl.UserRequestModelMapper;
import zhukova.victoria.kursovaya.repository.UserRequestRepo;

import java.util.List;

@Service
public class UserRequestService implements CrudService<UserRequest, UserRequestDto>{
    private final UserRequestRepo repository;
    private final UserRequestModelMapper mapper;

    @Autowired
    public UserRequestService(UserRequestRepo repository, UserRequestModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserRequest save(UserRequestDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public UserRequest update(UserRequestDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(UserRequestDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public UserRequest findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<UserRequest> findAll() {
        return repository.findAll();
    }
}
