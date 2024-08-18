package kr.co.goms.web.oss.biz.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class FieldDetailIdxOut {
    private Long fieldDetailIdx;
    private Long fieldIdx;
    private int mbIdx;
    private int fieldCode;
    private Date fieldDate;
    private String fieldCoordinate;
    private String fieldAddress;
    private String mhDepth;
    private String mhInflow;
    private String mhOutflow;
    private String mhDrainage;
}
