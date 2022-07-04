package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhukova.victoria.kursovaya.model.dto.SecurityUser;
import zhukova.victoria.kursovaya.model.dto.UserDto;
import zhukova.victoria.kursovaya.model.entity.Doctor;
import zhukova.victoria.kursovaya.model.mapper.impl.DoctorModelMapper;
import zhukova.victoria.kursovaya.repository.DoctorRepo;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final DoctorRepo doctorRepo;
    private final UserService userService;
    private final DoctorModelMapper doctorModelMapper;

    @Autowired
    public AuthenticationService(DoctorRepo doctorRepo, UserService userService, DoctorModelMapper doctorModelMapper) {
        this.doctorRepo = doctorRepo;
        this.userService = userService;
        this.doctorModelMapper = doctorModelMapper;
    }

    public SecurityUser findUserByEmail(String email) {
        UserDto user = userService.findUserDtoByEmail(email);
        Optional<Doctor> doctor = doctorRepo.findDoctorByUser_Email(user.getEmail());
        return doctor.isPresent() ? doctorModelMapper.toDto(doctor.get()) : user;
    }
}
