package kr.co.goms.web.oss.biz.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserDetailIn {
    private Long mbIdx;
    private String mbId;
    private String mbPwd;
    private String mbName;
    private String mbRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private Date regdate;
}
