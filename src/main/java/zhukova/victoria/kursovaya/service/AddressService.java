package zhukova.victoria.kursovaya.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.AddressDto;
import zhukova.victoria.kursovaya.model.dto.CityDto;
import zhukova.victoria.kursovaya.model.entity.Address;
import zhukova.victoria.kursovaya.model.mapper.impl.AddressModelMapper;
import zhukova.victoria.kursovaya.repository.AddressRepo;

import java.util.List;

@Service
public class AddressService implements CrudService<Address, AddressDto> {
    private final AddressModelMapper mapper;
    private final AddressRepo repository;
    private final CityService cityService;

    @Autowired
    public AddressService(AddressModelMapper mapper, AddressRepo repository, CityService cityService) {
        this.mapper = mapper;
        this.repository = repository;
        this.cityService = cityService;
    }

    @Override
    public Address save(AddressDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public Address update(AddressDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(AddressDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @SneakyThrows
    @Override
    public Address findById(int id) {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    @SneakyThrows
    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }

    public AddressDto findByParamDto(AddressDto dto) {
        return mapper.toDto(findByParam(dto));
    }
    public Address findByParam(AddressDto dto) {
        return repository.findOne(Example.of(mapper.toEntity(dto))).get();
    }

    public AddressDto updateUniteAddress(AddressDto fromDB, AddressDto update) {
        if(update == null) {
            return fromDB;
        }
        CityDto cityDto = cityService.findByCityDto(update.getCity());
        if (fromDB == null) {
            return new AddressDto(
                    update.getId(),
                    update.getStreet(),
                    update.getHouse(),
                    update.getFlat(),
                    cityDto);
        }
        return new AddressDto(
                fromDB.getId(),
                (update.getStreet() == null || update.getStreet().isBlank())?fromDB.getStreet(): update.getStreet(),
                (update.getHouse() == null || update.getHouse().isBlank())?fromDB.getHouse(): update.getHouse(),
                (update.getFlat() == null || update.getFlat().isBlank())?fromDB.getFlat(): update.getFlat(),
                cityDto
                );
    }
}
