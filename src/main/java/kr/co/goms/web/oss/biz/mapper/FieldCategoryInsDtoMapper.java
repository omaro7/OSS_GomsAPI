package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldCategoryInsIn;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInsInDto;

@Mapper(componentModel="spring")
public interface FieldCategoryInsDtoMapper extends EntityMapper<FieldCategoryInsInDto, FieldCategoryInsIn>{}
