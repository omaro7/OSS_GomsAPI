package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDetailIdxIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxInDto;

@Mapper(componentModel="spring")
public interface FieldDetailIdxDtoMapper extends EntityMapper<FieldDetailIdxInDto, FieldDetailIdxIn>{}
