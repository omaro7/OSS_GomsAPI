package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldIdxIn;
import kr.co.goms.web.oss.biz.service.dto.FieldIdxInDto;

@Mapper(componentModel="spring")
public interface FieldIdxDtoMapper extends EntityMapper<FieldIdxInDto, FieldIdxIn>{}
