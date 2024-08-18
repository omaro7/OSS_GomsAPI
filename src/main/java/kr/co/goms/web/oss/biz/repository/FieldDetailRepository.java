package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.FieldDetailDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailUpdInDto;


@Mapper 
@Repository
public interface  FieldDetailRepository {
    public List<FieldDetailOutDto> selectAllFieldDetail(FieldDetailInDto inDto);
    public FieldDetailIdxOutDto  getFieldDetail(FieldDetailIdxInDto inDto);
    public boolean  delFieldDetail(FieldDetailDelInDto inDto);
    public boolean  updFieldDetail(FieldDetailUpdInDto inDto);
    public boolean  insFieldDetail(FieldDetailInsInDto inDto);
}
