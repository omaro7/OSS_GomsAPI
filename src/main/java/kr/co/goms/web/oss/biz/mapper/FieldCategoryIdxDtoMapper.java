package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.FieldCategoryIdxIn;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxInDto;

@Mapper(componentModel="spring")
public interface FieldCategoryIdxDtoMapper extends EntityMapper<FieldCategoryIdxInDto, FieldCategoryIdxIn>{}
