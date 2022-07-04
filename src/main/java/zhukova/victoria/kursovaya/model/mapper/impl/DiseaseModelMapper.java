package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.DiseaseDto;
import zhukova.victoria.kursovaya.model.entity.Disease;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class DiseaseModelMapper implements ModelMapper<Disease, DiseaseDto> {
    private final DiseaseCategoryModelMapper diseaseCategoryModelMapper;

    @Autowired
    public DiseaseModelMapper(DiseaseCategoryModelMapper diseaseCategoryModelMapper) {
        this.diseaseCategoryModelMapper = diseaseCategoryModelMapper;
    }

    @Override
    public Disease toEntity(DiseaseDto dto) {
        if(dto == null) {
            return null;
        }
        return new Disease(
                dto.getId(),
                dto.getName(),
                diseaseCategoryModelMapper.toEntity(dto.getCategory()));
    }

    @Override
    public DiseaseDto toDto(Disease entity) {
        if(entity == null) {
            return null;
        }
        return new DiseaseDto(
                entity.getId(),
                entity.getName(),
                diseaseCategoryModelMapper.toDto(entity.getCategory()));
    }

    @Override
    public List<Disease> toEntityList(List<DiseaseDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList
                .stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<DiseaseDto> toDtoList(List<Disease> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList
                .stream()
                .map(this::toDto)
                .toList();
    }
}
