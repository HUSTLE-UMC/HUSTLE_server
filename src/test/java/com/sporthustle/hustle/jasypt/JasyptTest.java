package com.sporthustle.hustle.jasypt;

import static org.assertj.core.api.Assertions.assertThat;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

  @Value("${jasypt.encryptor.password}")
  private String encryptKey;

  @Test
  void jasypt_테스트() {
    String plainKey = "";
    StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
    jasypt.setPassword(encryptKey);
    String encryptedText = jasypt.encrypt(plainKey);
    String decryptedText = jasypt.decrypt(encryptedText);

    assertThat(plainKey).isEqualTo(decryptedText);
  }
}
