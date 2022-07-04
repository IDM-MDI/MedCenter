package zhukova.victoria.kursovaya.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhukova.victoria.kursovaya.model.dto.*;
import zhukova.victoria.kursovaya.model.entity.Doctor;
import zhukova.victoria.kursovaya.model.entity.User;
import zhukova.victoria.kursovaya.model.mapper.impl.CityModelMapper;
import zhukova.victoria.kursovaya.model.mapper.impl.UserModelMapper;
import zhukova.victoria.kursovaya.repository.CityRepo;
import zhukova.victoria.kursovaya.repository.DoctorRepo;
import zhukova.victoria.kursovaya.repository.UserInfoRepo;
import zhukova.victoria.kursovaya.repository.UserRepo;
import zhukova.victoria.kursovaya.util.HashGenerator;

import java.util.List;
import java.util.Optional;

import static zhukova.victoria.kursovaya.validator.DiseaseValidator.isDiseaseValid;

@Service
public class UserService implements CrudService<User,UserDto>{
    private final UserRepo repository;
    private final UserInfoRepo userInfoRepo;
    private final CityRepo cityRepo;
    private final UserModelMapper mapper;
    private final AddressService addressService;
    private final DoctorRepo doctorRepo;
    private final DiseaseCategoryService diseaseCategoryService;
    private final CityModelMapper cityModelMapper;

    @Autowired
    public UserService(UserRepo repository, UserInfoRepo userInfoRepo, CityRepo cityRepo, UserModelMapper mapper, AddressService addressService, DoctorRepo doctorRepo, DiseaseCategoryService diseaseCategoryService, CityModelMapper cityModelMapper) {
        this.repository = repository;
        this.userInfoRepo = userInfoRepo;
        this.cityRepo = cityRepo;
        this.mapper = mapper;
        this.addressService = addressService;
        this.doctorRepo = doctorRepo;
        this.diseaseCategoryService = diseaseCategoryService;
        this.cityModelMapper = cityModelMapper;
    }

    @Transactional
    public void addUser(AuthenticationDto user) throws Exception {
        UserDto dto = fillUser(user);
        isUserExist(dto);
        saveWithBeforeProcess(dto);
    }


    public void addUserInfo(String email, UserInfoDto info) {
        UserDto dto = findUserDtoByEmail(email);
        if(dto.getInfo() == null) {
            dto.setInfo(info);
        }
        else {
            dto.setInfo(updateUniteInfo(dto.getInfo(),info));
        }
        repository.save(mapper.toEntity(dto));
    }

    public UserDto findUserDtoByEmail(String email) {
        return mapper.toDto(findUserByEmail(email));
    }

    private User findUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("SecurityUser by email: " + email + " not found"));
    }

    public User saveWithBeforeProcess(UserDto dto) {
        beforeSaveProcess(dto);
        return save(dto);
    }

    @Override
    public User save(UserDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    public UserDto saveWithDto(UserDto dto) {
        return mapper.toDto(save(dto));
    }

    @Override
    public User update(UserDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(UserDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @SneakyThrows
    @Override
    public User findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    public UserInfoDto findInfoByEmail(String name) {
        UserInfoDto info = findUserDtoByEmail(name).getInfo();
        if(info == null) {
            info = createClearInfo();
        }
        return info;
    }

    public boolean isAdmin(String name) {
        Optional<Doctor> doctor = doctorRepo.findDoctorByUser_Email(name);
        return doctor.isPresent();
    }

    public List<UserDto> findAllDtoByPage(Integer page) {
        return repository.findAll(PageRequest.of(page,10))
                .map(mapper::toDto)
                .toList();
    }
    private void beforeSaveProcess(UserDto dto) {
        dto.setPassword(HashGenerator.generatePassHash(dto.getPassword()));
        dto.setInfo(UserInfoDto.createEmpty());
        dto.getInfo()
                .getAddress()
                .setCity(cityRepo.findCityByNameIgnoreCase("Минск")
                        .map(cityModelMapper::toDto)
                        .orElseThrow());
    }
    private UserInfoDto updateUniteInfo(UserInfoDto fromDB, UserInfoDto update) {
        UserInfoDto result = new UserInfoDto();
        result.setId(fromDB.getId());
        result.setName((update.getName() == null || update.getName().isBlank())? fromDB.getName() : update.getName());
        result.setSurname((update.getSurname() == null || update.getSurname().isBlank())? fromDB.getSurname() : update.getSurname());
        result.setAddress(addressService.updateUniteAddress(fromDB.getAddress(),update.getAddress()));
        return result;
    }

    private UserInfoDto createClearInfo() {
        UserInfoDto info = new UserInfoDto();
        AddressDto address = new AddressDto();
        address.setCity(new CityDto());
        info.setAddress(address);
        return info;
    }

    private UserDto fillUser(AuthenticationDto user) {
        UserDto result = new UserDto();
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        return result;
    }

    public boolean isUserExist(UserDto user) {
        return repository.findByEmail(user.getEmail()).isPresent();
    }

    public UserDto findDtoById(int id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public void addDisease(int id,DiseaseDto disease) {
        if(!isDiseaseValid(disease)) {
            return;
        }
        UserDto user = findDtoById(id);
        findDiseaseCategory(disease);
        user.setDiseaseBook(List.of(disease));
        repository.save(mapper.toEntity(user));
    }
    private void findDiseaseCategory(DiseaseDto disease) {
        disease.setId(0);
        disease.setCategory(diseaseCategoryService.findDtoByName(disease.getCategory().getName()));
    }
}
