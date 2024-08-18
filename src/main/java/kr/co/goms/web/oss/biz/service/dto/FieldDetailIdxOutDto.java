package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "FieldDetail FieldDetailIdxOutDto")
public class FieldDetailIdxOutDto {
    private Long fieldDetailIdx;
    private Long fieldIdx;
    private int mbIdx;
    private int fieldCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String fieldDate;
    private String fieldCoordinate;
    private String fieldAddress;
    private String mhDepth;
    private String mhInflow;
    private String mhOutflow;
    private String mhDrainage;
    private String mhStandard;
    private String mhMaterial;
    private String mhSize;
    private String mhLidDamageYn;
    private String mhLidCrackYn;
    private String mhLidWaterLms;
    private String mhLidDamageLms;
    private String mhLidCrackLms;
    private String mhOuterDamageYn;
    private String mhOuterCrackYn;
}
