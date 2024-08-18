package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.MemberDelInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberInsInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberUpdInDto;


@Mapper 
@Repository
public interface  MemberRepository {
    public List<MemberOutDto> selectAllMember(MemberInDto inDto);
    public MemberIdxOutDto  getMember(MemberIdxInDto inDto);
    public boolean delMember(MemberDelInDto inDto);
    public boolean updMember(MemberUpdInDto inDto);
    public boolean insMember(MemberInsInDto inDto);
}