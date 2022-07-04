package zhukova.victoria.kursovaya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements SecurityUser {
    private int id;
    private String email;
    private String password;
    private UserInfoDto info;
    private List<DiseaseDto> diseaseBook;
    private List<UserComplaintDto> complaints;
    private List<UserRequestDto> requests;

    public static UserDto createEmpty() {
        UserDto user = new UserDto();
        user.setInfo(UserInfoDto.createEmpty());
        return user;
    }
}
