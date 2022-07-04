package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AddressDto {
    private int id;
    private String street;
    private String house;
    private String flat;
    private CityDto city;

    public static AddressDto createEmpty() {
        AddressDto result = new AddressDto();
        result.setFlat("");
        result.setStreet("");
        result.setHouse("");
        result.setCity(CityDto.createEmpty());
        return result;
    }
}
