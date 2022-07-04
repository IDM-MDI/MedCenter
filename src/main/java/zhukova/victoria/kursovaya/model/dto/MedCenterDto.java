package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedCenterDto {
    private int id;
    private String name;
    private AddressDto address;

    public static MedCenterDto createEmpty() {
        MedCenterDto med = new MedCenterDto();
        med.setAddress(AddressDto.createEmpty());
        return med;
    }
}
