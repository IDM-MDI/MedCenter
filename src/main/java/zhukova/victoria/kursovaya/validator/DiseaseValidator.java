package zhukova.victoria.kursovaya.validator;

import zhukova.victoria.kursovaya.model.dto.DiseaseCategoryDto;
import zhukova.victoria.kursovaya.model.dto.DiseaseDto;

public class DiseaseValidator {
    public static boolean isDiseaseValid(DiseaseDto disease) {
        return disease != null &&
                !disease.getName().isBlank() &&
                isDiseaseCategoryValid(disease.getCategory());
    }
    public static boolean isDiseaseCategoryValid(DiseaseCategoryDto category) {
        return category != null && !category.getName().isBlank();
    }
}
