package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.DiseaseCategoryDto;
import zhukova.victoria.kursovaya.model.entity.DiseaseCategory;
import zhukova.victoria.kursovaya.model.mapper.impl.DiseaseCategoryModelMapper;
import zhukova.victoria.kursovaya.repository.DiseaseCategoryRepo;

import java.util.List;

import static zhukova.victoria.kursovaya.validator.DiseaseValidator.isDiseaseCategoryValid;

@Service
public class DiseaseCategoryService implements CrudService<DiseaseCategory, DiseaseCategoryDto>{
    private final DiseaseCategoryRepo repository;
    private final DiseaseCategoryModelMapper mapper;

    @Autowired
    public DiseaseCategoryService(DiseaseCategoryRepo repository, DiseaseCategoryModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DiseaseCategory save(DiseaseCategoryDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public DiseaseCategory update(DiseaseCategoryDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(DiseaseCategoryDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public DiseaseCategory findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public DiseaseCategory findByName(String name) {
        return repository.findDiseaseCategoryByNameIgnoreCase(name)
                .orElseThrow();
    }

    public DiseaseCategoryDto findDtoByName(String name) {
        return mapper.toDto(findByName(name));
    }

    @Override
    public List<DiseaseCategory> findAll() {
        return repository.findAll();
    }

    public DiseaseCategoryDto createEmpty() {
        return new DiseaseCategoryDto();
    }

    public void addCategory(DiseaseCategoryDto category) {
        if(!isDiseaseCategoryValid(category)) {
            return;
        }
        if(repository.findDiseaseCategoryByNameIgnoreCase(category.getName()).isPresent()) {
            return;
        }
        save(category);
    }

    public List<DiseaseCategoryDto> findAllDtoByPage(Integer page) {
        return repository.findAll(PageRequest.of(page,10))
                .map(mapper::toDto)
                .toList();
    }
}
