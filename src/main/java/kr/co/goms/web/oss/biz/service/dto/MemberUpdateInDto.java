package kr.co.goms.web.oss.biz.service.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberUpdateInDto{
    private Long mbIdx;
    private String mbId;
    private String mbPwd;
    private String mbName;
    private String mbRole;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;

    // Default constructor
    public MemberUpdateInDto() {
        this.regdate = new Date(); // Set default regdate to current date
    }

    // Getters and setters
    public Long getMbIdx() {
        return mbIdx;
    }

    public void setMbIdx(Long mbIdx) {
        this.mbIdx = mbIdx;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getMbPwd() {
        return mbPwd;
    }

    public void setMbPwd(String mbPwd) {
        this.mbPwd = mbPwd;
    }

    public String getMbName() {
        return mbName;
    }

    public void setMbName(String mbName) {
        this.mbName = mbName;
    }

    public String getMbRole() {
        return mbRole;
    }

    public void setMbRole(String mbRole) {
        this.mbRole = mbRole;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
