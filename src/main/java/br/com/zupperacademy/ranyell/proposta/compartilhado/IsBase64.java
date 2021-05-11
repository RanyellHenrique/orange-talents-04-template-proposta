package br.com.zupperacademy.ranyell.proposta.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsBase64Valid.class)
public @interface IsBase64 {

    String message() default "Biometria não está em BASE64";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}