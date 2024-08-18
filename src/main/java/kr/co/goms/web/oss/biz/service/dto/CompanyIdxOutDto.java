package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Company CompanyIdxOutDto")
public class CompanyIdxOutDto {
    private Long comIdx;
    private String comName;
    private Boolean useflag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regdate;
}
