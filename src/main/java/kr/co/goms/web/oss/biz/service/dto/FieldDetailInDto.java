package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "FieldDetail FieldDetailInDto")
public class FieldDetailInDto {
    private Long fieldDetailIdx;
    private Long fieldIdx;
    private int mbIdx;
    private int fieldCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String fieldDate;
    private String fieldCoordinate;
    private String fieldAddress;
    private String mhDepth;
    private String mhInflow;
    private String mhOutflow;
    private String mhDrainage;
}
