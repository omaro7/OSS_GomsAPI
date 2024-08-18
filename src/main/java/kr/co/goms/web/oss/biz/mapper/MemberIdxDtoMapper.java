package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.MemberIdxIn;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxInDto;

@Mapper(componentModel="spring")
public interface MemberIdxDtoMapper extends EntityMapper<MemberIdxInDto, MemberIdxIn>{}
