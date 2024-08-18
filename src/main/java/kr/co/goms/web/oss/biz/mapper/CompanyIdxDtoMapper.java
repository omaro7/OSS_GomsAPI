package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.CompanyIdxIn;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxInDto;

@Mapper(componentModel="spring")
public interface CompanyIdxDtoMapper extends EntityMapper<CompanyIdxInDto, CompanyIdxIn>{}
