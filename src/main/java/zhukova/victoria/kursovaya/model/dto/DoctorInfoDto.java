package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfoDto {
    private int id;
    private DoctorPositionDto position;
    private MedCenterDto medCenter;

    public static DoctorInfoDto createEmpty() {
        DoctorInfoDto info = new DoctorInfoDto();
        info.setMedCenter(MedCenterDto.createEmpty());
        info.setPosition(DoctorPositionDto.createEmpty());
        return info;
    }
}
