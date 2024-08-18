package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.CompanyIn;
import kr.co.goms.web.oss.biz.service.dto.CompanyInDto;

@Mapper(componentModel="spring")
public interface CompanyDtoMapper extends EntityMapper<CompanyInDto, CompanyIn>{}
