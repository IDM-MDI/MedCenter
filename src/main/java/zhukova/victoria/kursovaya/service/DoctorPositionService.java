package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.DoctorPositionDto;
import zhukova.victoria.kursovaya.model.mapper.impl.DoctorPositionModelMapper;
import zhukova.victoria.kursovaya.repository.DoctorPositionRepo;
import zhukova.victoria.kursovaya.validator.DoctorPositionValidator;

import java.util.List;

import static zhukova.victoria.kursovaya.validator.DoctorValidator.isDoctorPositionValid;

@Service
public class DoctorPositionService {
    private final DoctorPositionRepo repository;
    private final DoctorPositionModelMapper mapper;

    @Autowired
    public DoctorPositionService(DoctorPositionRepo repository, DoctorPositionModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DoctorPositionDto findDtoByName(String name) {
        return repository.findDoctorPositionByNameIgnoreCase(name)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public List<DoctorPositionDto> findAllDtoByPage(Integer page) {
        return repository.findAll(PageRequest.of(page,10))
                .map(mapper::toDto)
                .toList();
    }

    public DoctorPositionDto getEmpty() {
        return DoctorPositionDto.createEmpty();
    }

    public void addPosition(DoctorPositionDto positionDto) {
        if(!isDoctorPositionValid(positionDto)) {
            return;
        }
        if(repository.findDoctorPositionByNameIgnoreCase(positionDto.getName())
                .isEmpty()) {
            repository.save(mapper.toEntity(positionDto));
        }
    }
}
