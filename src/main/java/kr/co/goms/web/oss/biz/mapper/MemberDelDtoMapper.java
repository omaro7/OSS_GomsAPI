package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.MemberDelIn;
import kr.co.goms.web.oss.biz.service.dto.MemberDelInDto;

@Mapper(componentModel="spring")
public interface MemberDelDtoMapper extends EntityMapper<MemberDelInDto, MemberDelIn>{}
