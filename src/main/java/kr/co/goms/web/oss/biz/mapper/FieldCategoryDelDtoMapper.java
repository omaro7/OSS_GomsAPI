package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldCategoryDelIn;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryDelInDto;

@Mapper(componentModel="spring")
public interface FieldCategoryDelDtoMapper extends EntityMapper<FieldCategoryDelInDto, FieldCategoryDelIn>{}
