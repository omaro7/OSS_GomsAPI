package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDetailIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInDto;

@Mapper(componentModel="spring")
public interface FieldDetailDtoMapper extends EntityMapper<FieldDetailInDto, FieldDetailIn>{}
