package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.AddressDto;
import zhukova.victoria.kursovaya.model.dto.CityDto;
import zhukova.victoria.kursovaya.model.entity.City;
import zhukova.victoria.kursovaya.model.mapper.impl.CityModelMapper;
import zhukova.victoria.kursovaya.repository.CityRepo;

import java.util.List;

import static zhukova.victoria.kursovaya.validator.AddressValidator.isCityValid;

@Service
public class CityService implements CrudService<City, CityDto> {
    private final CityRepo repository;
    private final CityModelMapper mapper;

    @Autowired
    public CityService(CityRepo repository, CityModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public City save(CityDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public City update(CityDto dto) {
        return null;
    }

    @Override
    public void delete(CityDto dto) {
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public List<City> findAll() {
        return null;
    }
    public CityDto findByName(String name) {
        return repository.findCityByNameIgnoreCase(name)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public CityDto findByCityDto(CityDto city) {
        return city == null ? null : findByName(city.getName());
    }

    public void setCityFromDB(AddressDto address) {
        address.setCity(findByName(address.getCity().getName()));
    }

    public CityDto getEmpty() {
        return CityDto.createEmpty();
    }

    public List<CityDto> findAllDtoByPage(int page) {
        return repository.findAll(PageRequest.of(page,10))
                .map(mapper::toDto)
                .toList();
    }

    public void addCity(CityDto city) {
        if(!isCityValid(city)) {
            return;
        }
        if(repository.findCityByNameIgnoreCase(city.getName()).isEmpty()) {
            save(city);
        }
    }
}
