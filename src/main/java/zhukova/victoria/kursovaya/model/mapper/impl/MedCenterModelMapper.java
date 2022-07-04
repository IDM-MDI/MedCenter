package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.MedCenterDto;
import zhukova.victoria.kursovaya.model.entity.MedCenter;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class MedCenterModelMapper implements ModelMapper<MedCenter, MedCenterDto> {
    private final AddressModelMapper addressModelMapper;

    @Autowired
    public MedCenterModelMapper(AddressModelMapper addressModelMapper) {
        this.addressModelMapper = addressModelMapper;
    }

    @Override
    public MedCenter toEntity(MedCenterDto dto) {
        if(dto == null) {
            return null;
        }
        return new MedCenter(dto.getId(),dto.getName(),addressModelMapper.toEntity(dto.getAddress()));
    }

    @Override
    public MedCenterDto toDto(MedCenter entity) {
        if(entity == null) {
            return null;
        }
        return new MedCenterDto(entity.getId(),entity.getName(),addressModelMapper.toDto(entity.getAddress()));
    }

    @Override
    public List<MedCenter> toEntityList(List<MedCenterDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<MedCenterDto> toDtoList(List<MedCenter> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
