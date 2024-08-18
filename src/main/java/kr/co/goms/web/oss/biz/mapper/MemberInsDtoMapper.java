package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.MemberInsIn;
import kr.co.goms.web.oss.biz.service.dto.MemberInsInDto;

@Mapper(componentModel="spring")
public interface MemberInsDtoMapper extends EntityMapper<MemberInsInDto, MemberInsIn>{}
