package zhukova.victoria.kursovaya.validator;

import zhukova.victoria.kursovaya.model.dto.MedCenterDto;

import static zhukova.victoria.kursovaya.validator.AddressValidator.isAddressValid;
import static zhukova.victoria.kursovaya.validator.UserValidator.isStringNotEmpty;

public class MedCenterValidator {
    public static boolean isMedValid(MedCenterDto center) {
        return isMedValidWithoutAddress(center) &&
                isAddressValid(center.getAddress());
    }
    public static boolean isMedValidWithoutAddress(MedCenterDto center) {
        return center != null &&
                isStringNotEmpty(center.getName());
    }
}
