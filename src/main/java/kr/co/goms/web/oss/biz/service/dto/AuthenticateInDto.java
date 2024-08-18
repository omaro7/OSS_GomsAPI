package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
@Schema(description = "Authenticate InDTO > 토큰생성 요청 DTO")
public class AuthenticateInDto {

	public AuthenticateInDto() {
	}

	@Schema(description = "username 내용")
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

	@Schema(description = "password 내용")
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

	@Schema(description = "rememberMe 여부")
    private boolean rememberMe;
	
}
