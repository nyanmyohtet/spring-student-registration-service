package com.nyanmyohtet.studentregistrationservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class DatabaseEncryptionConfig {
    @Value("${db-encryption.secret}")
    private String secret;

    @Value("${db-encryption.algorithm}")
    private String algorithm;

}
