package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
    Optional<Doctor> findDoctorByUser_Email(String email);
}
