package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.MedCenter;

import java.util.Optional;

@Repository
public interface MedCenterRepo extends JpaRepository<MedCenter,Integer> {
    Optional<MedCenter> findMedCenterByNameIgnoreCase(String name);
}
