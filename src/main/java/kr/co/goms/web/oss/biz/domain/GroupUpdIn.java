package kr.co.goms.web.oss.biz.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class GroupUpdIn {
    private Long groupIdx;
    private int mbIdx;
    private String groupName;
    private Boolean useflag;
    private Date regdate;
}
