package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.GroupIn;
import kr.co.goms.web.oss.biz.service.dto.GroupInDto;

@Mapper(componentModel="spring")
public interface GroupDtoMapper extends EntityMapper<GroupInDto, GroupIn>{}
