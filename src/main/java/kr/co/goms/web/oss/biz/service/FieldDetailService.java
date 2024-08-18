package kr.co.goms.web.oss.biz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.goms.web.oss.biz.repository.FieldDetailRepository;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailUpdInDto;


@Service
@Transactional
public class  FieldDetailService {

   private final Logger log = LoggerFactory.getLogger(FieldDetailService.class);

   @Autowired FieldDetailRepository field_detailRepository;

    public List<FieldDetailOutDto> selectAllFieldDetail(){
        FieldDetailInDto inDto = new FieldDetailInDto();
        List<FieldDetailOutDto> list = null;
        list = field_detailRepository.selectAllFieldDetail(inDto);
        return list;
   }

    public FieldDetailIdxOutDto getFieldDetail(FieldDetailIdxInDto inDto){
        FieldDetailIdxOutDto field_detail = null;
        field_detail = field_detailRepository.getFieldDetail(inDto);
        return field_detail;
   }

    public boolean delFieldDetail(FieldDetailDelInDto inDto){
        boolean isDel = field_detailRepository.delFieldDetail(inDto);
        return isDel;
   }

    public boolean updFieldDetail(FieldDetailUpdInDto inDto){
        boolean isUpd = field_detailRepository.updFieldDetail(inDto);
        return isUpd;
   }

    public boolean insFieldDetail(FieldDetailInsInDto inDto){
        boolean isIns = field_detailRepository.insFieldDetail(inDto);
        return isIns;
   }
}
