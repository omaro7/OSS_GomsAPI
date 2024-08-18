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
import kr.co.goms.web.oss.biz.service.CompanyService;
import kr.co.goms.web.oss.biz.service.dto.CompanyDelInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyInsInDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyOutDto;
import kr.co.goms.web.oss.biz.service.dto.CompanyUpdInDto;
import kr.co.goms.web.oss.core.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CompanyController {

   private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

   @Autowired
   private CompanyService companyService;

   @Tag(name = "GOMS API > Company", description = "GOMS API Company입니다. 전체 조회입니다.")
   @GetMapping("/company/selectAllCompany")
   public ResponseEntity<ApiResponse<List<CompanyOutDto>>> selectAllCompany() {
        List<CompanyOutDto> list = companyService.selectAllCompany();
        ApiResponse<List<CompanyOutDto>> response = new ApiResponse<>(true, list, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Company", description = "GOMS API Company입니다. 개별 조회입니다.")
   @PostMapping("/company/getCompany")
   public ResponseEntity<ApiResponse<CompanyIdxOutDto>> getCompany(CompanyIdxInDto inDto) {
        CompanyIdxOutDto  company = companyService.getCompany(inDto);
        ApiResponse<CompanyIdxOutDto> response = new ApiResponse<>(true, company, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Company", description = "GOMS API Company입니다. 개별 삭제입니다.")
   @PostMapping("/company/delCompany")
   public ResponseEntity<ApiResponse<Void>> delCompany(CompanyDelInDto inDto) {
        boolean isDel = companyService.delCompany(inDto);
        ApiResponse<Void> response;
        if (isDel) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Company", description = "GOMS API Company입니다. 개별 업데이트입니다.")
   @PostMapping("/company/updCompany")
   public ResponseEntity<ApiResponse<Void>> updCompany(CompanyUpdInDto inDto) {
        boolean isUpd = companyService.updCompany(inDto);
        ApiResponse<Void> response;
        if (isUpd) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Company", description = "GOMS API Company입니다. 저장입니다.")
   @PostMapping("/company/insCompany")
   public ResponseEntity<ApiResponse<Void>> insCompany(CompanyInsInDto inDto) {
        boolean isIns= companyService.insCompany(inDto);
        ApiResponse<Void> response;
        if (isIns) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
