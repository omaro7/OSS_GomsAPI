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
import kr.co.goms.web.oss.biz.service.GroupService;
import kr.co.goms.web.oss.biz.service.dto.GroupDelInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupInsInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupUpdInDto;
import kr.co.goms.web.oss.core.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
public class GroupController {

   private static final Logger log = LoggerFactory.getLogger(GroupController.class);

   @Autowired
   private GroupService groupService;

   @Tag(name = "GOMS API > Group", description = "GOMS API Group입니다. 전체 조회입니다.")
   @GetMapping("/group/selectAllGroup")
   public ResponseEntity<ApiResponse<List<GroupOutDto>>> selectAllGroup() {
        List<GroupOutDto> list = groupService.selectAllGroup();
        ApiResponse<List<GroupOutDto>> response = new ApiResponse<>(true, list, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Group", description = "GOMS API Group입니다. 개별 조회입니다.")
   @PostMapping("/group/getGroup")
   public ResponseEntity<ApiResponse<GroupIdxOutDto>> getGroup(GroupIdxInDto inDto) {
        GroupIdxOutDto  group = groupService.getGroup(inDto);
        ApiResponse<GroupIdxOutDto> response = new ApiResponse<>(true, group, "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Group", description = "GOMS API Group입니다. 개별 삭제입니다.")
   @PostMapping("/group/delGroup")
   public ResponseEntity<ApiResponse<Void>> delGroup(GroupDelInDto inDto) {
        boolean isDel = groupService.delGroup(inDto);
        ApiResponse<Void> response;
        if (isDel) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Group", description = "GOMS API Group입니다. 개별 업데이트입니다.")
   @PostMapping("/group/updGroup")
   public ResponseEntity<ApiResponse<Void>> updGroup(GroupUpdInDto inDto) {
        boolean isUpd = groupService.updGroup(inDto);
        ApiResponse<Void> response;
        if (isUpd) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @Tag(name = "GOMS API > Group", description = "GOMS API Group입니다. 저장입니다.")
   @PostMapping("/group/insGroup")
   public ResponseEntity<ApiResponse<Void>> insGroup(GroupInsInDto inDto) {
        boolean isIns= groupService.insGroup(inDto);
        ApiResponse<Void> response;
        if (isIns) {
            response = new ApiResponse<>(true, null, "success");
        } else {
            response = new ApiResponse<>(false, null, "failure");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
