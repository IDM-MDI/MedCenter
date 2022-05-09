package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.DoctorInfo;

@Repository
public interface DoctorInfoRepo extends JpaRepository<DoctorInfo,Integer> {
}
