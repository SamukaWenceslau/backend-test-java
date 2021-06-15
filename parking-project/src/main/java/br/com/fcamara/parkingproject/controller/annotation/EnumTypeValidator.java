package br.com.fcamara.parkingproject.controller.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTypeValidator implements ConstraintValidator<EnumTypeIsValid, CharSequence>{

    private List<String> acceptedValues;

    @Override
    public void initialize(EnumTypeIsValid annotation) {
        // Pegar os valores da classe enum e add em uma lista
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString().toUpperCase());
    }

}
