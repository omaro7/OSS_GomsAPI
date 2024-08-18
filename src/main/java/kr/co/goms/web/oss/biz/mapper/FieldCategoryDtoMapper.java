package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldCategoryIn;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInDto;

@Mapper(componentModel="spring")
public interface FieldCategoryDtoMapper extends EntityMapper<FieldCategoryInDto, FieldCategoryIn>{}
