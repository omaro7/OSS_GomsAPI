package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.CompanyUpdIn;
import kr.co.goms.web.oss.biz.service.dto.CompanyUpdInDto;

@Mapper(componentModel="spring")
public interface CompanyUpdDtoMapper extends EntityMapper<CompanyUpdInDto, CompanyUpdIn>{}
