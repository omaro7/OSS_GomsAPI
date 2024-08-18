package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class MemberInsertOutDto {
	private Long mbIdx;
    private String mbId;
    private String mbName;
    private String mbRole;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;
}
