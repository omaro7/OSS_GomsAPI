package kr.co.goms.web.oss.biz.service.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Company CompanyDelInDto")
public class CompanyDelInDto {
    private Long comIdx;
}
