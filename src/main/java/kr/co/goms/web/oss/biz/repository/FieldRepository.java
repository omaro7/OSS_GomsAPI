package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.FieldDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldUpdInDto;


@Mapper 
@Repository
public interface  FieldRepository {
    public List<FieldOutDto> selectAllField(FieldInDto inDto);
    public FieldIdxOutDto  getField(FieldIdxInDto inDto);
    public boolean  delField(FieldDelInDto inDto);
    public boolean  updField(FieldUpdInDto inDto);
    public boolean  insField(FieldInsInDto inDto);
}
