package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Component
@Data
@Schema(description = "Dummy OutDTO")
public class DummyOutDto {

	public DummyOutDto() {
	}

	@Schema(description = "Dummy 내용")
	private String dummy;
	
}
