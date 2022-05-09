package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.MedCenter;

@Repository
public interface MedCenterRepo extends JpaRepository<MedCenter,Integer> {
}
