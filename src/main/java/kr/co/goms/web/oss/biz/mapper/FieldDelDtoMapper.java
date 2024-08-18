package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDelIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDelInDto;

@Mapper(componentModel="spring")
public interface FieldDelDtoMapper extends EntityMapper<FieldDelInDto, FieldDelIn>{}
