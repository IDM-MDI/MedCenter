package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto implements SecurityUser {
    private int id;
    private UserDto user;
    private DoctorInfoDto info;

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public static DoctorDto createEmpty() {
        DoctorDto doctor = new DoctorDto();
        doctor.setInfo(DoctorInfoDto.createEmpty());
        doctor.setUser(UserDto.createEmpty());
        return doctor;
    }
}
