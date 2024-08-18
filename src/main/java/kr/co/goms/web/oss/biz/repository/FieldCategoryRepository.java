package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.FieldCategoryDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryUpdInDto;

@Mapper 
@Repository
public interface  FieldCategoryRepository {
    public List<FieldCategoryOutDto> selectAllField_category(FieldCategoryInDto inDto);
    public FieldCategoryIdxOutDto  getField_category(FieldCategoryIdxInDto inDto);
    public boolean  delField_category(FieldCategoryDelInDto inDto);
    public boolean  updField_category(FieldCategoryUpdInDto inDto);
    public boolean  insField_category(FieldCategoryInsInDto inDto);
}
