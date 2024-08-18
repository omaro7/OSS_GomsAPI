package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Component
@Data
@Schema(description = "Dummy InDTO")
public class DummyInDto {

	public DummyInDto() {
	}

	@Schema(description = "Dummy 내용")
	private String dummy;
	
}
