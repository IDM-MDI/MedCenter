package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.DoctorInfoDto;
import zhukova.victoria.kursovaya.model.entity.DoctorInfo;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class DoctorInfoModelMapper implements ModelMapper<DoctorInfo, DoctorInfoDto> {
    private final DoctorPositionModelMapper doctorPositionModelMapper;
    private final MedCenterModelMapper medCenterModelMapper;

    @Autowired
    public DoctorInfoModelMapper(DoctorPositionModelMapper doctorPositionModelMapper, MedCenterModelMapper medCenterModelMapper) {
        this.doctorPositionModelMapper = doctorPositionModelMapper;
        this.medCenterModelMapper = medCenterModelMapper;
    }

    @Override
    public DoctorInfo toEntity(DoctorInfoDto dto) {
        if(dto == null) {
            return null;
        }
        return new DoctorInfo(
                dto.getId(),
                doctorPositionModelMapper.toEntity(dto.getPosition()),
                medCenterModelMapper.toEntity(dto.getMedCenter()));
    }

    @Override
    public DoctorInfoDto toDto(DoctorInfo entity) {
        if(entity == null) {
            return null;
        }
        return new DoctorInfoDto(
                entity.getId(),
                doctorPositionModelMapper.toDto(entity.getPosition()),
                medCenterModelMapper.toDto(entity.getMedCenter()));
    }

    @Override
    public List<DoctorInfo> toEntityList(List<DoctorInfoDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<DoctorInfoDto> toDtoList(List<DoctorInfo> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
