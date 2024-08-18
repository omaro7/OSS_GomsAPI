package kr.co.goms.web.oss.biz.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserDetailOut {
    private Long mbIdx;
    private String mbId;
    private String mbPwd;
    private String mbName;
    private String mbRole;
    private Date regdate;
}
