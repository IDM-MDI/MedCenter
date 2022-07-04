package zhukova.victoria.kursovaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhukova.victoria.kursovaya.model.entity.City;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City,Integer> {
    Optional<City> findCityByNameIgnoreCase(String name);
}
