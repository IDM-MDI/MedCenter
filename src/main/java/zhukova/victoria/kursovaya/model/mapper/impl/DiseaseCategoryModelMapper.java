package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.DiseaseCategoryDto;
import zhukova.victoria.kursovaya.model.entity.DiseaseCategory;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class DiseaseCategoryModelMapper implements ModelMapper<DiseaseCategory, DiseaseCategoryDto> {
    @Override
    public DiseaseCategory toEntity(DiseaseCategoryDto dto) {
        if(dto == null) {
            return null;
        }
        return new DiseaseCategory(dto.getId(),dto.getName());
    }

    @Override
    public DiseaseCategoryDto toDto(DiseaseCategory entity) {
        if(entity == null) {
            return null;
        }
        return new DiseaseCategoryDto(entity.getId(),entity.getName());
    }

    @Override
    public List<DiseaseCategory> toEntityList(List<DiseaseCategoryDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<DiseaseCategoryDto> toDtoList(List<DiseaseCategory> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
