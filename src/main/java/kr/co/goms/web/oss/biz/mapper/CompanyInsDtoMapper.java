package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.CompanyInsIn;
import kr.co.goms.web.oss.biz.service.dto.CompanyInsInDto;

@Mapper(componentModel="spring")
public interface CompanyInsDtoMapper extends EntityMapper<CompanyInsInDto, CompanyInsIn>{}
