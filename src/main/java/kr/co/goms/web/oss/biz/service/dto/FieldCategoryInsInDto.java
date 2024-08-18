package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Field_category Field_categoryInsInDto")
public class FieldCategoryInsInDto {
    private Long fieldCategoryIdx;
    private String fieldCategoryName;
    private Boolean useflag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regdate;
}
