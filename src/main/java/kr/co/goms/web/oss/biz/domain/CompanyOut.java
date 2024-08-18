package kr.co.goms.web.oss.biz.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CompanyOut {
    private Long comIdx;
    private String comName;
    private Boolean useflag;
    private Date regdate;
}
