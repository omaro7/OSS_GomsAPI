package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.MemberIn;
import kr.co.goms.web.oss.biz.service.dto.MemberInDto;

@Mapper(componentModel="spring")
public interface MemberDtoMapper extends EntityMapper<MemberInDto, MemberIn>{}
