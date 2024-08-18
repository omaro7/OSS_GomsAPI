package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Component
@Data
@Schema(description = "Authenticate OutDTO > 토큰")
public class AuthenticateOutDto {

	public AuthenticateOutDto() {
	}

	@Schema(description = "발행된 token")
	private String idToken;
	
    @JsonProperty("id_token")
    String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
	
}
