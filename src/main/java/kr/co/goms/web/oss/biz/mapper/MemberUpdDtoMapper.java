package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.MemberUpdIn;
import kr.co.goms.web.oss.biz.service.dto.MemberUpdInDto;

@Mapper(componentModel="spring")
public interface MemberUpdDtoMapper extends EntityMapper<MemberUpdInDto, MemberUpdIn>{}
