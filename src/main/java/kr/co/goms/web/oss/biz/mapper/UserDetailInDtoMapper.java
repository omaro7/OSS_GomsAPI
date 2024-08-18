package kr.co.goms.web.oss.biz.mapper;

import org.mapstruct.Mapper;

import kr.co.goms.web.oss.biz.domain.UserDetailIn;
import kr.co.goms.web.oss.biz.service.dto.UserDetailInDto;

@Mapper(componentModel="spring")
public interface UserDetailInDtoMapper extends EntityMapper<UserDetailInDto, UserDetailIn>{
}
