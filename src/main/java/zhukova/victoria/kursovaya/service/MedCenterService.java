package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhukova.victoria.kursovaya.model.dto.MedCenterDto;
import zhukova.victoria.kursovaya.model.entity.MedCenter;
import zhukova.victoria.kursovaya.model.mapper.impl.MedCenterModelMapper;
import zhukova.victoria.kursovaya.repository.MedCenterRepo;
import zhukova.victoria.kursovaya.validator.MedCenterValidator;

import java.util.List;

@Service
public class MedCenterService implements CrudService<MedCenter, MedCenterDto>{
    private final MedCenterRepo repository;
    private final MedCenterModelMapper mapper;
    private final CityService cityService;

    @Autowired
    public MedCenterService(MedCenterRepo repository, MedCenterModelMapper mapper, CityService cityService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cityService = cityService;
    }

    @Override
    public MedCenter save(MedCenterDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public MedCenter update(MedCenterDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(MedCenterDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public MedCenter findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<MedCenter> findAll() {
        return repository.findAll();
    }

    public MedCenterDto findByName(String name) {
        return repository.findMedCenterByNameIgnoreCase(name)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public List<MedCenterDto> findAllDtoByPage(Integer page) {
        return repository.findAll(PageRequest.of(page,10))
                         .map(mapper::toDto)
                         .toList();
    }

    public MedCenterDto getEmpty() {
        return MedCenterDto.createEmpty();
    }

    @Transactional
    public void addCenter(MedCenterDto center) {
        if(!MedCenterValidator.isMedValid(center)) {
            return;
        }
        cityService.setCityFromDB(center.getAddress());
        save(center);
    }
}
