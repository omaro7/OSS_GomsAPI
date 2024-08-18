package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.GroupInsIn;
import kr.co.goms.web.oss.biz.service.dto.GroupInsInDto;

@Mapper(componentModel="spring")
public interface GroupInsDtoMapper extends EntityMapper<GroupInsInDto, GroupInsIn>{}
