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
import kr.co.goms.web.oss.biz.service.FieldDetailService;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailDelInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailInsInDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailOutDto;
import kr.co.goms.web.oss.biz.service.dto.FieldDetailUpdInDto;
import kr.co.goms.web.oss.core.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
public class FieldDetailController {

   private static final Logger log = LoggerFactory.getLogger(FieldDetailController.class);

   @Autowired
   private FieldDetailService field_detailService;

   @Tag(name = "GOMS API > FieldDetail", description = "GOMS API FieldDetail입니다. 전체 조회입니다.")
   @GetMapping("/field_detail/selectAllFieldDetail")
   public ResponseEntity<ApiResponse<List<FieldDetailOutDto>>> selectAllFieldDetail() {
        List<FieldDetailOutDto> list = field_detailService.selectAllFieldDetail();
        ApiResponse<List<FieldDetailOutDto>> response = new ApiResponse<>(true, list, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldDetail", description = "GOMS API FieldDetail입니다. 개별 조회입니다.")
   @PostMapping("/field_detail/getFieldDetail")
   public ResponseEntity<ApiResponse<FieldDetailIdxOutDto>> getFieldDetail(FieldDetailIdxInDto inDto) {
        FieldDetailIdxOutDto  field_detail = field_detailService.getFieldDetail(inDto);
        ApiResponse<FieldDetailIdxOutDto> response = new ApiResponse<>(true, field_detail, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldDetail", description = "GOMS API FieldDetail입니다. 개별 삭제입니다.")
   @PostMapping("/field_detail/delFieldDetail")
   public ResponseEntity<ApiResponse<Void>> delFieldDetail(FieldDetailDelInDto inDto) {
        boolean isDel = field_detailService.delFieldDetail(inDto);
        ApiResponse<Void> response;
        if (isDel) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldDetail", description = "GOMS API FieldDetail입니다. 개별 업데이트입니다.")
   @PostMapping("/field_detail/updFieldDetail")
   public ResponseEntity<ApiResponse<Void>> updFieldDetail(FieldDetailUpdInDto inDto) {
        boolean isUpd = field_detailService.updFieldDetail(inDto);
        ApiResponse<Void> response;
        if (isUpd) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > FieldDetail", description = "GOMS API FieldDetail입니다. 저장입니다.")
   @PostMapping("/field_detail/insFieldDetail")
   public ResponseEntity<ApiResponse<Void>> insFieldDetail(FieldDetailInsInDto inDto) {
        boolean isIns= field_detailService.insFieldDetail(inDto);
        ApiResponse<Void> response;
        if (isIns) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
