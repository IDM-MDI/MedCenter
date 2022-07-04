package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private int id;
    private String name;

    public static CityDto createEmpty() {
        return new CityDto(0,"");
    }
}
