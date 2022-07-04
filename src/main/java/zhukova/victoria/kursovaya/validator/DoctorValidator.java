package zhukova.victoria.kursovaya.validator;

import zhukova.victoria.kursovaya.model.dto.DoctorDto;
import zhukova.victoria.kursovaya.model.dto.DoctorInfoDto;
import zhukova.victoria.kursovaya.model.dto.DoctorPositionDto;

import static zhukova.victoria.kursovaya.validator.MedCenterValidator.isMedValid;
import static zhukova.victoria.kursovaya.validator.MedCenterValidator.isMedValidWithoutAddress;
import static zhukova.victoria.kursovaya.validator.UserValidator.isStringNotEmpty;
import static zhukova.victoria.kursovaya.validator.UserValidator.isUserValid;

public class DoctorValidator {
    public static boolean isDoctorValid(DoctorDto doctor) {
        return doctor != null &&
                isDoctorInfoValid(doctor.getInfo()) &&
                isUserValid(doctor.getUser());
    }
    public static boolean isDoctorInfoValid(DoctorInfoDto doctorInfo) {
        return doctorInfo != null &&
                isDoctorPositionValid(doctorInfo.getPosition()) &&
                isMedValidWithoutAddress(doctorInfo.getMedCenter());
    }

    public static boolean isDoctorPositionValid(DoctorPositionDto position) {
        return position != null && isStringNotEmpty(position.getName());
    }
}
