package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Member MemberInDto")
public class MemberInDto {
    private Long mbIdx;
    private String mbId;
    private String mbPwd;
    private String mbName;
    private String mbRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private String regdate;
}
