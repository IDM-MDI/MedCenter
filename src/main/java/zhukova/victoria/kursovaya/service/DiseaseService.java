package zhukova.victoria.kursovaya.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.DiseaseCategoryDto;
import zhukova.victoria.kursovaya.model.dto.DiseaseDto;
import zhukova.victoria.kursovaya.model.entity.Disease;
import zhukova.victoria.kursovaya.model.mapper.impl.DiseaseModelMapper;
import zhukova.victoria.kursovaya.repository.DiseaseRepo;

import java.util.List;

@Service
public class DiseaseService implements CrudService<Disease, DiseaseDto>{
    private final DiseaseRepo repository;
    private final DiseaseModelMapper mapper;

    @Autowired
    public DiseaseService(DiseaseRepo repository, DiseaseModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Disease save(DiseaseDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public Disease update(DiseaseDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(DiseaseDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @SneakyThrows
    @Override
    public Disease findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Disease> findAll() {
        return repository.findAll();
    }

    public DiseaseDto getEmpty() {
        DiseaseDto disease = new DiseaseDto();
        disease.setCategory(new DiseaseCategoryDto());
        return disease;
    }
}
