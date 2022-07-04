package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.UserInfoDto;
import zhukova.victoria.kursovaya.model.entity.UserInfo;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class UserInfoModelMapper implements ModelMapper<UserInfo, UserInfoDto> {
    private final AddressModelMapper addressModelMapper;

    @Autowired
    public UserInfoModelMapper(AddressModelMapper addressModelMapper) {
        this.addressModelMapper = addressModelMapper;
    }

    @Override
    public UserInfo toEntity(UserInfoDto dto) {
        if(dto == null) {
            return null;
        }
        return new UserInfo(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                addressModelMapper.toEntity(dto.getAddress()));
    }

    @Override
    public UserInfoDto toDto(UserInfo entity) {
        if(entity == null) {
            return null;
        }
        return new UserInfoDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                addressModelMapper.toDto(entity.getAddress()));
    }

    @Override
    public List<UserInfo> toEntityList(List<UserInfoDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<UserInfoDto> toDtoList(List<UserInfo> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }

}
