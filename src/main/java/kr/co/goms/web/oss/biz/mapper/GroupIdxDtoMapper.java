package  kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.GroupIdxIn;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxInDto;

@Mapper(componentModel="spring")
public interface GroupIdxDtoMapper extends EntityMapper<GroupIdxInDto, GroupIdxIn>{}
