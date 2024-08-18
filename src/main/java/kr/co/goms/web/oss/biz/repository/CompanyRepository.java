package kr.co.goms.web.oss.biz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goms.web.oss.biz.service.dto.CompanyDelInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyInsInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyUpdInDto;

@Mapper 
@Repository
public interface  CompanyRepository {
    public List<CompanyOutDto> selectAllCompany(CompanyInDto inDto);
    public CompanyIdxOutDto  getCompany(CompanyIdxInDto inDto);
    public boolean  delCompany(CompanyDelInDto inDto);
    public boolean  updCompany(CompanyUpdInDto inDto);
    public boolean  insCompany(CompanyInsInDto inDto);
}
