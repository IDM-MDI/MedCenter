package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.UserInfoDto;
import zhukova.victoria.kursovaya.model.entity.UserInfo;
import zhukova.victoria.kursovaya.model.mapper.impl.UserInfoModelMapper;
import zhukova.victoria.kursovaya.repository.UserInfoRepo;

import java.util.List;

@Service
public class UserInfoService implements CrudService<UserInfo, UserInfoDto>{
    private final UserInfoRepo repository;
    private final UserInfoModelMapper mapper;

    @Autowired
    public UserInfoService(UserInfoRepo repository, UserInfoModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserInfo save(UserInfoDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public UserInfo update(UserInfoDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(UserInfoDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public UserInfo findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<UserInfo> findAll() {
        return repository.findAll();
    }
}
