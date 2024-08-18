package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldDetailUpdIn;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailUpdInDto;

@Mapper(componentModel="spring")
public interface FieldDetailUpdDtoMapper extends EntityMapper<FieldDetailUpdInDto, FieldDetailUpdIn>{}
