package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.GroupDelIn;
import kr.co.goms.web.oss.biz.service.dto.GroupDelInDto;

@Mapper(componentModel="spring")
public interface GroupDelDtoMapper extends EntityMapper<GroupDelInDto, GroupDelIn>{}
