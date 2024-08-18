package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Field FieldUpdInDto")
public class FieldUpdInDto {
    private Long fieldIdx;
    private int mbIdx;
    private int groupIdx;
    private String fieldTitle;
    private String fieldLocal;
    private Boolean useflag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regdate;
}
