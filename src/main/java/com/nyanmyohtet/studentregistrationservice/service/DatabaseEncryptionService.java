package com.nyanmyohtet.studentregistrationservice.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface DatabaseEncryptionService {
    String encrypt(String originalMessage) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException;

    String decrypt(String encryptedBase64Message) throws InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException;
}
