package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.GroupUpdIn;
import kr.co.goms.web.oss.biz.service.dto.GroupUpdInDto;

@Mapper(componentModel="spring")
public interface GroupUpdDtoMapper extends EntityMapper<GroupUpdInDto, GroupUpdIn>{}
