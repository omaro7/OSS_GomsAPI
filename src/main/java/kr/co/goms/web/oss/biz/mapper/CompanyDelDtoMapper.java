package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.CompanyDelIn;
import kr.co.goms.web.oss.biz.service.dto.CompanyDelInDto;

@Mapper(componentModel="spring")
public interface CompanyDelDtoMapper extends EntityMapper<CompanyDelInDto, CompanyDelIn>{}
