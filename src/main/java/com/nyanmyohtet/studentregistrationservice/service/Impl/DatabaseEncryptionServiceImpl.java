package com.nyanmyohtet.studentregistrationservice.service.Impl;

import com.nyanmyohtet.studentregistrationservice.config.DatabaseEncryptionConfig;
import com.nyanmyohtet.studentregistrationservice.service.DatabaseEncryptionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
@Service
public class DatabaseEncryptionServiceImpl implements DatabaseEncryptionService {

    private final DatabaseEncryptionConfig databaseEncryptionConfig;

    private final SecretKeySpec secretKey;

    public DatabaseEncryptionServiceImpl(DatabaseEncryptionConfig databaseEncryptionConfig) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
        this.databaseEncryptionConfig = databaseEncryptionConfig;
        StringBuilder secretStringBuilder = new StringBuilder(this.databaseEncryptionConfig.getSecret());
        int length = 16;
        if (this.databaseEncryptionConfig.getSecret().length() < length) {
            int missingLength = length - this.databaseEncryptionConfig.getSecret().length();
            secretStringBuilder.append(" ".repeat(missingLength));
        }
        byte[] key = secretStringBuilder.toString().getBytes(StandardCharsets.UTF_8);

        this.secretKey = new SecretKeySpec(key, this.databaseEncryptionConfig.getAlgorithm());
    }

    @Override
    public String encrypt(String originalMessage) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(this.databaseEncryptionConfig.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        byte[] output = cipher.doFinal(originalMessage.getBytes());
        return Base64.getEncoder().encodeToString(output);
    }

    @Override
    public String decrypt(String encryptedBase64Message) throws InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(this.databaseEncryptionConfig.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        byte[] input = Base64.getDecoder().decode(encryptedBase64Message.getBytes(StandardCharsets.UTF_8));
        byte[] output = cipher.doFinal(input);
        return new String(output);
    }
}
