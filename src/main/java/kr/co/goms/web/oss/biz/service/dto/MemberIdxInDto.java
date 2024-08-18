package kr.co.goms.web.oss.biz.service.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Member MemberIdxInDto")
public class MemberIdxInDto {
    private Long mbIdx;
}
