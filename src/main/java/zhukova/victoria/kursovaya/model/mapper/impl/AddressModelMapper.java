package zhukova.victoria.kursovaya.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhukova.victoria.kursovaya.model.dto.AddressDto;
import zhukova.victoria.kursovaya.model.entity.Address;
import zhukova.victoria.kursovaya.model.mapper.ModelMapper;

import java.util.List;

@Component
public class AddressModelMapper implements ModelMapper<Address, AddressDto> {
    private final CityModelMapper cityModelMapper;

    @Autowired
    public AddressModelMapper(CityModelMapper cityModelMapper) {
        this.cityModelMapper = cityModelMapper;
    }

    @Override
    public Address toEntity(AddressDto dto) {
        if(dto == null) {
            return null;
        }
        return new Address(
                dto.getId(),
                dto.getStreet(),
                dto.getHouse(),
                dto.getFlat(),
                cityModelMapper.toEntity(dto.getCity()));
    }

    @Override
    public AddressDto toDto(Address entity) {
        if(entity == null) {
            return null;
        }
        return new AddressDto(
                entity.getId(),
                entity.getStreet(),
                entity.getHouse(),
                entity.getFlat(),
                cityModelMapper.toDto(entity.getCity()));
    }

    @Override
    public List<Address> toEntityList(List<AddressDto> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<AddressDto> toDtoList(List<Address> entityList) {
        if(entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
