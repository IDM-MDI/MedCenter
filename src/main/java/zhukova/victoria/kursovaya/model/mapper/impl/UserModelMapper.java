package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.UserDto;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class UserModelMapper implements ModelMapper<User, UserDto> {
    private final UserInfoModelMapper infoModelMapper;
    private final DiseaseModelMapper diseaseModelMapper;
    private final UserComplaintModelMapper complaintModelMapper;
    private final UserRequestModelMapper requestModelMapper;

    @Autowired
    public UserModelMapper(UserInfoModelMapper infoModelMapper, DiseaseModelMapper diseaseModelMapper, UserComplaintModelMapper complaintModelMapper, UserRequestModelMapper requestModelMapper) {
        this.infoModelMapper = infoModelMapper;
        this.diseaseModelMapper = diseaseModelMapper;
        this.complaintModelMapper = complaintModelMapper;
        this.requestModelMapper = requestModelMapper;
    }

    @Override
    public User toEntity(UserDto dto) {
        if(dto == null) {
            return null;
        }
        return new User(
                dto.getId(),
                dto.getEmail(),
                dto.getPassword(),
                infoModelMapper.toEntity(dto.getInfo()),
                diseaseModelMapper.toEntityList(dto.getDiseaseBook()),
                complaintModelMapper.toEntityList(dto.getComplaints()),
                requestModelMapper.toEntityList(dto.getRequests())
                );
    }

    @Override
    public UserDto toDto(User entity) {
        if(entity == null) {
            return null;
        }
        return new UserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                infoModelMapper.toDto(entity.getInfo()),
                diseaseModelMapper.toDtoList(entity.getDiseaseBook()),
                complaintModelMapper.toDtoList(entity.getComplaints()),
                requestModelMapper.toDtoList(entity.getRequests())
        );
    }

    @Override
    public List<User> toEntityList(List<UserDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<UserDto> toDtoList(List<User> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
