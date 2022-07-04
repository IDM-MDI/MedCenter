package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.CityDto;
import zhukova.victoria.kursovaya.model.entity.City;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class CityModelMapper implements ModelMapper<City, CityDto> {
    @Override
    public City toEntity(CityDto dto) {
        if(dto == null) {
            return null;
        }
        return new City(dto.getId(),dto.getName());
    }

    @Override
    public CityDto toDto(City entity) {
        if(entity == null) {
            return null;
        }
        return new CityDto(entity.getId(),entity.getName());
    }

    @Override
    public List<City> toEntityList(List<CityDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<CityDto> toDtoList(List<City> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
