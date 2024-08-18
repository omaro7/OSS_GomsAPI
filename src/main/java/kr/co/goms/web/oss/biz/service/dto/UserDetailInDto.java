package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Member UserDetailInDto")
public class UserDetailInDto {
    private Long mbIdx;
    private String mbId;
    private String mbPwd;
    private String mbName;
    private String mbRole;
    private Date regdate;
}
