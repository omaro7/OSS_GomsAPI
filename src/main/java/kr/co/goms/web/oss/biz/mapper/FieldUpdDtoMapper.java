package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldUpdIn;
import kr.co.goms.web.oss.biz.service.dto.FieldUpdInDto;

@Mapper(componentModel="spring")
public interface FieldUpdDtoMapper extends EntityMapper<FieldUpdInDto, FieldUpdIn>{}
