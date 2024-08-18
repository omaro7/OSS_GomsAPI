package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDetailDelIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailDelInDto;

@Mapper(componentModel="spring")
public interface FieldDetailDelDtoMapper extends EntityMapper<FieldDetailDelInDto, FieldDetailDelIn>{}
