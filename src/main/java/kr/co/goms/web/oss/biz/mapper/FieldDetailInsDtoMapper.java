package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDetailInsIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInsInDto;

@Mapper(componentModel="spring")
public interface FieldDetailInsDtoMapper extends EntityMapper<FieldDetailInsInDto, FieldDetailInsIn>{}
