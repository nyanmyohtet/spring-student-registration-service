package com.nyanmyohtet.studentregistrationservice.converter;

import com.nyanmyohtet.studentregistrationservice.service.DatabaseEncryptionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    @Autowired
    private DatabaseEncryptionService databaseEncryptionService;

//    private final DatabaseEncryptionService databaseEncryptionService;
//
//    public EncryptedStringConverter(DatabaseEncryptionService databaseEncryptionService) {
//        this.databaseEncryptionService = databaseEncryptionService;
//    }

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
