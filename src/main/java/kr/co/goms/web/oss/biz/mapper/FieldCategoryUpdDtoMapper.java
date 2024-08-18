package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldCategoryUpdIn;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryUpdInDto;

@Mapper(componentModel="spring")
public interface FieldCategoryUpdDtoMapper extends EntityMapper<FieldCategoryUpdInDto, FieldCategoryUpdIn>{}
