package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.DiseaseCategory;

import java.util.Optional;

@Repository
public interface DiseaseCategoryRepo extends JpaRepository<DiseaseCategory,Integer> {
    Optional<DiseaseCategory> findDiseaseCategoryByNameIgnoreCase(String name);
}
