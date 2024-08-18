package kr.co.goms.web.oss.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@ToString
@Configuration
@PropertySource(value = "classpath:/config/oss_config.yml", factory = YamlPropertySourceFactory.class)
public class GomsProperties {

    public GomsProperties(){
        log.debug("### AmsProperties Instance Created ###");
    }
    // AES 사용 여부
    @Value("${enc.use}")
    private boolean useAES;

    // Encrypt/Decrypt용 psk
    @Value("${enc.psk}")
    private String encPsk;

    // Encrypt/Decrypt용 alg
    @Value("${enc.alg}")
    private String encAlg;

    // Encrypt/Decrypt용 cipher
    @Value("${enc.cipher}")
    private String encCipher;

    // Encrypt/Decrypt용 cipher
    @Value("${enc.algcipher}")
    private String encAlgCipher;

    // JWT 
    @Value("${jwt.secretkey}")
    private String jwtSecretKey;
}
