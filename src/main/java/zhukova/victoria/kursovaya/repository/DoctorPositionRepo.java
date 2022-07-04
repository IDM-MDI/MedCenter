package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.DoctorPosition;

import java.util.Optional;

@Repository
public interface DoctorPositionRepo extends JpaRepository<DoctorPosition,Integer> {
    Optional<DoctorPosition> findDoctorPositionByNameIgnoreCase(String name);
}
