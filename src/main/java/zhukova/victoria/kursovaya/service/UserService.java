package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.entity.City;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.model.entity.UserInfo;
import zhukova.victoria.kursovaya.repository.CityRepo;
import zhukova.victoria.kursovaya.repository.UserInfoRepo;
import zhukova.victoria.kursovaya.repository.UserRepo;

@Service
public class UserService {
    private final UserRepo repository;
    private final UserInfoRepo userInfoRepo;
    private final CityRepo cityRepo;

    @Autowired
    public UserService(UserRepo repository, UserInfoRepo userInfoRepo, CityRepo cityRepo) {
        this.repository = repository;
        this.userInfoRepo = userInfoRepo;
        this.cityRepo = cityRepo;
    }

    public void addUser(User user) {
        User userFromDB = repository.findByEmail(user.getEmail());
        if(userFromDB == null) {
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            repository.save(user);
        }
    }

    public boolean login(User user) {
        boolean result = false;
        User userFromDB = repository.findByEmail(user.getEmail());
        if(userFromDB != null) {
            result = userFromDB.getPassword().equals(user.getPassword());
        }
        return result;
    }

    public void addUserInfo(User user,UserInfo info) {
        City city = info.getAddress().getCity();
        City cityFromDB = cityRepo.findByName(city.getName());

        if(cityFromDB == null) {
            cityRepo.save(city);
            info.getAddress().setCity(cityRepo.findByName(city.getName()));
        }
        else {
            info.getAddress().setCity(cityFromDB);
        }

        User userFromDB = repository.findByEmail(user.getEmail());
        UserInfo infoFromDB = userFromDB.getInfo();
        if(infoFromDB == null) {
            userInfoRepo.save(info);
            user.setInfo(userInfoRepo.getById(info.getId()));
        }
        else {
            info.setId(infoFromDB.getId());
            userInfoRepo.save(info);
        }
        user.setInfo(info);
    }
}
