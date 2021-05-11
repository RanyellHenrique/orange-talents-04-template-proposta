package br.com.zupperacademy.ranyell.proposta.compartilhado;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Valid implements ConstraintValidator<IsBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            Base64.getDecoder().decode(value);
            return true;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }
}
