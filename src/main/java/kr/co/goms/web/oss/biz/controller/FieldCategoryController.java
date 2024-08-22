package kr.co.goms.web.oss.biz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.goms.web.oss.biz.service.FieldCategoryService;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldCategoryUpdInDto;
import kr.co.goms.web.oss.core.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class FieldCategoryController {

   private static final Logger log = LoggerFactory.getLogger(FieldCategoryController.class);

   @Autowired
   private FieldCategoryService fieldCategoryService;

   @Tag(name = "GOMS API > FieldCategory", description = "GOMS API FieldCategory입니다. 전체 조회입니다.")
   @GetMapping("/fieldCategory/selectAllFieldCategory")
   public ResponseEntity<ApiResponse<List<FieldCategoryOutDto>>> selectAllFieldCategory() {
        List<FieldCategoryOutDto> list = fieldCategoryService.selectAllFieldCategory();
        ApiResponse<List<FieldCategoryOutDto>> response = new ApiResponse<>(true, list, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldCategory", description = "GOMS API Field_category입니다. 개별 조회입니다.")
   @PostMapping("/fieldCategory/getFieldCategory")
   public ResponseEntity<ApiResponse<FieldCategoryIdxOutDto>> getFieldCategory(FieldCategoryIdxInDto inDto) {
        FieldCategoryIdxOutDto  field_category = fieldCategoryService.getFieldCategory(inDto);
        ApiResponse<FieldCategoryIdxOutDto> response = new ApiResponse<>(true, field_category, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldCategory", description = "GOMS API Field_category입니다. 개별 삭제입니다.")
   @PostMapping("/fieldCategory/delFieldCategory")
   public ResponseEntity<ApiResponse<Void>> delFieldCategory(FieldCategoryDelInDto inDto) {
        boolean isDel = fieldCategoryService.delFieldCategory(inDto);
        ApiResponse<Void> response;
        if (isDel) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldCategory", description = "GOMS API FieldCategory입니다. 개별 업데이트입니다.")
   @PostMapping("/fieldCategory/updFieldCategory")
   public ResponseEntity<ApiResponse<Void>> updFieldCategory(FieldCategoryUpdInDto inDto) {
        boolean isUpd = fieldCategoryService.updFieldCategory(inDto);
        ApiResponse<Void> response;
        if (isUpd) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldCategory", description = "GOMS API FieldCategory입니다. 저장입니다.")
   @PostMapping("/fieldCategory/insFieldCategory")
   public ResponseEntity<ApiResponse<Void>> insFieldCategory(FieldCategoryInsInDto inDto) {
        boolean isIns= fieldCategoryService.insFieldCategory(inDto);
        ApiResponse<Void> response;
        if (isIns) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
