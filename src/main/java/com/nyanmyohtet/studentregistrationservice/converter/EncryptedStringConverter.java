package com.nyanmyohtet.studentregistrationservice.converter;

import com.nyanmyohtet.studentregistrationservice.service.DatabaseEncryptionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    @Autowired
    private DatabaseEncryptionService databaseEncryptionService;

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return databaseEncryptionService.encrypt(attribute);
    }

    @SneakyThrows
    @Override
    public String convertToEntityAttribute(String dbData) {
        return databaseEncryptionService.decrypt(dbData);
    }
}
