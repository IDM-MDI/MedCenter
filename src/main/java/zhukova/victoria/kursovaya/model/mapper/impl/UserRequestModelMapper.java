package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.UserRequestDto;
import zhukova.victoria.kursovaya.model.entity.UserRequest;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class UserRequestModelMapper implements ModelMapper<UserRequest, UserRequestDto> {
    @Override
    public UserRequest toEntity(UserRequestDto dto) {
        if(dto == null) {
            return null;
        }
        return new UserRequest(dto.getId(), dto.isAccepted());
    }

    @Override
    public UserRequestDto toDto(UserRequest entity) {
        if(entity == null) {
            return null;
        }
        return new UserRequestDto(entity.getId(),entity.isAccepted());
    }

    @Override
    public List<UserRequest> toEntityList(List<UserRequestDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<UserRequestDto> toDtoList(List<UserRequest> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
