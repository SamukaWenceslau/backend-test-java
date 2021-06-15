package br.com.fcamara.parkingproject.controller.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { EnumTypeValidator.class }) // Classe de validação
public @interface EnumTypeIsValid {

    Class<? extends Enum<?>> enumClass(); // Recebendo uma classe do tipo enum

    String message() default "Esse tipo não é valido"; //message se não for valido

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

