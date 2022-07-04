package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.DoctorDto;
import zhukova.victoria.kursovaya.model.entity.Doctor;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class DoctorModelMapper implements ModelMapper<Doctor, DoctorDto> {
    private final UserModelMapper userModelMapper;
    private final DoctorInfoModelMapper doctorInfoModelMapper;

    @Autowired
    public DoctorModelMapper(UserModelMapper userModelMapper, DoctorInfoModelMapper doctorInfoModelMapper) {
        this.userModelMapper = userModelMapper;
        this.doctorInfoModelMapper = doctorInfoModelMapper;
    }

    @Override
    public Doctor toEntity(DoctorDto dto) {
        if(dto == null) {
            return null;
        }
        return new Doctor(
                dto.getId(),
                userModelMapper.toEntity(dto.getUser()),
                doctorInfoModelMapper.toEntity(dto.getInfo()));
    }

    @Override
    public DoctorDto toDto(Doctor entity) {
        if(entity == null) {
            return null;
        }
        return new DoctorDto(
                entity.getId(),
                userModelMapper.toDto(entity.getUser()),
                doctorInfoModelMapper.toDto(entity.getInfo()));
    }

    @Override
    public List<Doctor> toEntityList(List<DoctorDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<DoctorDto> toDtoList(List<Doctor> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
