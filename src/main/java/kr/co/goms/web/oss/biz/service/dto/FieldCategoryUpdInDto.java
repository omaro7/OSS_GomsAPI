package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "FieldCategory FieldCategoryUpdInDto")
public class FieldCategoryUpdInDto {
    private Long fieldCategoryIdx;
    private String fieldCategoryName;
    private Boolean useflag;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private String regdate;
}
