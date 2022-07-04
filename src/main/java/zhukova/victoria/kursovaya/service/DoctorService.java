package zhukova.victoria.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhukova.victoria.kursovaya.model.dto.*;
import zhukova.victoria.kursovaya.model.entity.Doctor;
import zhukova.victoria.kursovaya.model.mapper.impl.DoctorInfoModelMapper;
import zhukova.victoria.kursovaya.model.mapper.impl.DoctorModelMapper;
import zhukova.victoria.kursovaya.repository.DoctorRepo;
import zhukova.victoria.kursovaya.util.HashGenerator;

import java.util.List;
import java.util.Optional;

import static zhukova.victoria.kursovaya.validator.DoctorValidator.isDoctorValid;

@Service
public class DoctorService implements CrudService<Doctor, DoctorDto>{
    private final DoctorRepo repository;
    private final DoctorInfoModelMapper infoModelMapper;
    private final DoctorModelMapper mapper;
    private final CityService cityService;
    private final MedCenterService medCenterService;
    private final DoctorPositionService doctorPositionService;
    private final UserService userService;

    @Autowired
    public DoctorService(DoctorRepo repository, DoctorInfoModelMapper infoModelMapper, DoctorModelMapper mapper, CityService cityService, MedCenterService medCenterService, DoctorPositionService doctorPositionService, UserService userService) {
        this.repository = repository;
        this.infoModelMapper = infoModelMapper;
        this.mapper = mapper;
        this.cityService = cityService;
        this.medCenterService = medCenterService;
        this.doctorPositionService = doctorPositionService;
        this.userService = userService;
    }

    @Override
    public Doctor save(DoctorDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public Doctor update(DoctorDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(DoctorDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public Doctor findById(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    public Optional<DoctorDto> findDoctorByEmail(String email) {
        return repository.findDoctorByUser_Email(email).map(mapper::toDto);
    }
    public DoctorDto findDtoById(int id) {
        return mapper.toDto(findById(id));
    }
    public List<DoctorDto> findByPage(Integer page) {
        return mapper.toDtoList(repository
                .findAll(PageRequest.of(page, 10))
                .toList());
    }

    public DoctorInfoDto findInfoByEmail(String name) {
        return repository.findDoctorByUser_Email(name)
                .map(mapper::toDto)
                .orElseThrow()
                .getInfo();
    }

    public void addDoctorInfo(String email, DoctorInfoDto info) {
        Doctor doctor = repository.findDoctorByUser_Email(email)
                .orElseThrow();
        doctor.setInfo(infoModelMapper.toEntity(info));
       repository.save(doctor);
    }

    @Transactional
    public void saveDoctor(DoctorDto admin) {
        if(!isDoctorValid(admin) || userService.isUserExist(admin.getUser())) {
            return;
        }
        cityService.setCityFromDB(admin.getUser().getInfo().getAddress());
        admin.getUser().setPassword(HashGenerator.generatePassHash(admin.getUser().getPassword()));
        admin.setUser(userService.saveWithDto(admin.getUser()));
        admin.getInfo().setMedCenter(medCenterService.findByName(admin.getInfo().getMedCenter().getName()));
        admin.getInfo().setPosition(doctorPositionService.findDtoByName(admin.getInfo().getPosition().getName()));
        save(admin);
    }

    public DoctorDto getEmptyDoctor() {
        return DoctorDto.createEmpty();
    }
}
