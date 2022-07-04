package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPositionDto {
    private int id;
    private String name;

    public static DoctorPositionDto createEmpty() {
        return new DoctorPositionDto();
    }
}
