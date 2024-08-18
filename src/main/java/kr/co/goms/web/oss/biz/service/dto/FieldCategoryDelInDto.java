package kr.co.goms.web.oss.biz.service.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Field_category Field_categoryDelInDto")
public class FieldCategoryDelInDto {
    private Long fieldCategoryIdx;
}
