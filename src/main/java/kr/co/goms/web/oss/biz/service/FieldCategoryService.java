package kr.co.goms.web.oss.biz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.goms.web.oss.biz.repository.FieldCategoryRepository;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryUpdInDto;


@Service
@Transactional
public class  FieldCategoryService {

   private final Logger log = LoggerFactory.getLogger(FieldCategoryService.class);

   @Autowired FieldCategoryRepository fieldCategoryRepository;

    public List<FieldCategoryOutDto> selectAllFieldCategory(){
        FieldCategoryInDto inDto = new FieldCategoryInDto();
        List<FieldCategoryOutDto> list = null;
        list = fieldCategoryRepository.selectAllField_category(inDto);
        return list;
   }

    public FieldCategoryIdxOutDto getFieldCategory(FieldCategoryIdxInDto inDto){
        FieldCategoryIdxOutDto field_category = null;
        field_category = fieldCategoryRepository.getField_category(inDto);
        return field_category;
   }

    public boolean delFieldCategory(FieldCategoryDelInDto inDto){
        boolean isDel = fieldCategoryRepository.delField_category(inDto);
        return isDel;
   }

    public boolean updFieldCategory(FieldCategoryUpdInDto inDto){
        boolean isUpd = fieldCategoryRepository.updField_category(inDto);
        return isUpd;
   }

    public boolean insFieldCategory(FieldCategoryInsInDto inDto){
        boolean isIns = fieldCategoryRepository.insField_category(inDto);
        return isIns;
   }
}
