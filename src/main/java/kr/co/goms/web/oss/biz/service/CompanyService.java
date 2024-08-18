package kr.co.goms.web.oss.biz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.goms.web.oss.biz.repository.CompanyRepository;
import kr.co.goms.web.oss.biz.service.dto.CompanyDelInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyInsInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyUpdInDto;

@Service
@Transactional
public class  CompanyService {

   private final Logger log = LoggerFactory.getLogger(CompanyService.class);

   @Autowired CompanyRepository companyRepository;

    public List<CompanyOutDto> selectAllCompany(){
        CompanyInDto inDto = new CompanyInDto();
        List<CompanyOutDto> list = null;
        list = companyRepository.selectAllCompany(inDto);
        return list;
   }

    public CompanyIdxOutDto getCompany(CompanyIdxInDto inDto){
        CompanyIdxOutDto company = null;
        company = companyRepository.getCompany(inDto);
        return company;
   }

    public boolean delCompany(CompanyDelInDto inDto){
        boolean isDel = companyRepository.delCompany(inDto);
        return isDel;
   }

    public boolean updCompany(CompanyUpdInDto inDto){
        boolean isUpd = companyRepository.updCompany(inDto);
        return isUpd;
   }

    public boolean insCompany(CompanyInsInDto inDto){
        boolean isIns = companyRepository.insCompany(inDto);
        return isIns;
   }
}
