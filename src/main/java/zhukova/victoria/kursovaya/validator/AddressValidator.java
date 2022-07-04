package zhukova.victoria.kursovaya.validator;

import zhukova.victoria.kursovaya.model.dto.AddressDto;
import zhukova.victoria.kursovaya.model.dto.CityDto;

import static zhukova.victoria.kursovaya.validator.UserValidator.isStringNotEmpty;

public class AddressValidator {
    public static boolean isAddressValid(AddressDto address) {
        return address != null &&
                isStringNotEmpty(address.getStreet()) &&
                isStringNotEmpty(address.getFlat()) &&
                isStringNotEmpty(address.getHouse()) &&
                isCityValid(address.getCity());
    }
    public static boolean isCityValid(CityDto city) {
        return city != null && isStringNotEmpty(city.getName());
    }
}
