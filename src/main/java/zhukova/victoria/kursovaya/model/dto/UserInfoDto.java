package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private int id;
    private String name;
    private String surname;
    private AddressDto address;

    public static UserInfoDto createEmpty() {
        UserInfoDto result = new UserInfoDto();
        result.setName("");
        result.setSurname("");
        result.setAddress(AddressDto.createEmpty());
        return result;
    }
}
