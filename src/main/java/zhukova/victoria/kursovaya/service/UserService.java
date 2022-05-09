package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        user.setInfo(info);
        repository.save(user);
    }
}
