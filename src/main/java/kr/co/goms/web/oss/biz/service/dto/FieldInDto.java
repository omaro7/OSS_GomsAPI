package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Field FieldInDto")
public class FieldInDto {
    private Long fieldIdx;
    private int mbIdx;
    private int groupIdx;
    private String fieldTitle;
    private String fieldLocal;
    private Boolean useflag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regdate;
}
