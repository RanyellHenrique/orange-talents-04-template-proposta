package br.com.zupperacademy.ranyell.proposta.compartilhado;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CriptografaAtributo implements AttributeConverter<String, String> {

    @Value("{password.atributo.converter}")
    private String password;


    @Override
    public String convertToDatabaseColumn(String attribute) {
        TextEncryptor criptografia = Encryptors.text(password, "ab1d47f85b");
        return criptografia.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        TextEncryptor criptografia = Encryptors.text(password, "ab1d47f85b");
        return criptografia.decrypt(dbData);
    }

}
