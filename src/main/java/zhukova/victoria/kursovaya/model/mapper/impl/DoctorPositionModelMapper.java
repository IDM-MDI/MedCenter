package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.DoctorPositionDto;
import zhukova.victoria.kursovaya.model.entity.DoctorPosition;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class DoctorPositionModelMapper implements ModelMapper<DoctorPosition, DoctorPositionDto> {
    @Override
    public DoctorPosition toEntity(DoctorPositionDto dto) {
        if(dto == null) {
            return null;
        }
        return new DoctorPosition(dto.getId(),dto.getName());
    }

    @Override
    public DoctorPositionDto toDto(DoctorPosition entity) {
        if(entity == null) {
            return null;
        }
        return new DoctorPositionDto(entity.getId(),entity.getName());
    }

    @Override
    public List<DoctorPosition> toEntityList(List<DoctorPositionDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<DoctorPositionDto> toDtoList(List<DoctorPosition> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
