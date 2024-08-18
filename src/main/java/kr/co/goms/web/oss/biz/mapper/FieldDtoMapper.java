package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldIn;
import kr.co.goms.web.oss.biz.service.dto.FieldInDto;

@Mapper(componentModel="spring")
public interface FieldDtoMapper extends EntityMapper<FieldInDto, FieldIn>{}
