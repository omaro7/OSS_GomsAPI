package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldInsIn;
import kr.co.goms.web.oss.biz.service.dto.FieldInsInDto;

@Mapper(componentModel="spring")
public interface FieldInsDtoMapper extends EntityMapper<FieldInsInDto, FieldInsIn>{}
