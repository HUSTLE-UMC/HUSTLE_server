package com.sporthustle.hustle.jasypt;

import com.sporthustle.hustle.config.JasyptConfig;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JasyptTest extends JasyptConfig {

    @Test
    public void 평문_암호화_결과_출력() {
        String encryptKey = System.getProperty("jasypt.encryptor.password");

        // 암호화할 평문 작성하기
        String plainText = "";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(encryptKey);

        String encryptedText = jasypt.encrypt(plainText);
        String decryptedText = jasypt.decrypt(encryptedText);

        // 테스트 결과에서 암호문 확인 가능
        System.out.println(encryptedText);

        assertThat(plainText).isEqualTo(decryptedText);
    }
}
