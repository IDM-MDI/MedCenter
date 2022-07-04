package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.UserComplaintDto;
import zhukova.victoria.kursovaya.model.entity.UserComplaint;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class UserComplaintModelMapper implements ModelMapper<UserComplaint, UserComplaintDto> {
    @Override
    public UserComplaint toEntity(UserComplaintDto dto) {
        if(dto == null) {
            return null;
        }
        return new UserComplaint(dto.getId(),dto.getDescription());
    }

    @Override
    public UserComplaintDto toDto(UserComplaint entity) {
        if(entity == null) {
            return null;
        }
        return new UserComplaintDto(entity.getId(), entity.getDescription());
    }

    @Override
    public List<UserComplaint> toEntityList(List<UserComplaintDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<UserComplaintDto> toDtoList(List<UserComplaint> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
