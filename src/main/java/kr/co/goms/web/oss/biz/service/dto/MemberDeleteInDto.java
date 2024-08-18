package kr.co.goms.web.oss.biz.service.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@Data
public class MemberDeleteInDto{
    private Long mbIdx;
}
