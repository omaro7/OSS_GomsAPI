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
import kr.co.goms.web.oss.biz.service.MemberService;
import kr.co.goms.web.oss.biz.service.dto.MemberDelInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberInsInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberUpdInDto;
import kr.co.goms.web.oss.core.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class MemberController {

   private static final Logger log = LoggerFactory.getLogger(MemberController.class);

   @Autowired
   private MemberService memberService;

   @Tag(name = "GOMS API > Member", description = "GOMS API Member입니다. 전체 조회입니다.")
   @GetMapping("/member/selectAllMember")
   public ResponseEntity<ApiResponse<List<MemberOutDto>>> selectAllMember() {
        List<MemberOutDto> list = memberService.selectAllMember();
        ApiResponse<List<MemberOutDto>> response = new ApiResponse<>(true, list, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Member", description = "GOMS API Member입니다. 개별 조회입니다.")
   @PostMapping("/member/getMember")
   public ResponseEntity<ApiResponse<MemberIdxOutDto>> getMember(MemberIdxInDto inDto) {
        MemberIdxOutDto  member = memberService.getMember(inDto);
        ApiResponse<MemberIdxOutDto> response = new ApiResponse<>(true, member, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Member", description = "GOMS API Member입니다. 개별 삭제입니다.")
   @PostMapping("/member/delMember")
   public ResponseEntity<ApiResponse<Void>> delMember(MemberDelInDto inDto) {
        boolean isDel = memberService.delMember(inDto);
        ApiResponse<Void> response;
        if (isDel) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Member", description = "GOMS API Member입니다. 개별 업데이트입니다.")
   @PostMapping("/member/updMember")
   public ResponseEntity<ApiResponse<Void>> updMember(MemberUpdInDto inDto) {
        boolean isUpd = memberService.updMember(inDto);
        ApiResponse<Void> response;
        if (isUpd) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Member", description = "GOMS API Member입니다. 저장입니다.")
   @PostMapping("/member/insMember")
   public ResponseEntity<ApiResponse<Void>> insMember(MemberInsInDto inDto) {
        boolean isIns= memberService.insMember(inDto);
        ApiResponse<Void> response;
        if (isIns) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
