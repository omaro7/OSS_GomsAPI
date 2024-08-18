package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.GroupDelInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupInsInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupUpdInDto;


@Mapper 
@Repository
public interface  GroupRepository {
    public List<GroupOutDto> selectAllGroup(GroupInDto inDto);
    public GroupIdxOutDto  getGroup(GroupIdxInDto inDto);
    public boolean  delGroup(GroupDelInDto inDto);
    public boolean  updGroup(GroupUpdInDto inDto);
    public boolean  insGroup(GroupInsInDto inDto);
}
