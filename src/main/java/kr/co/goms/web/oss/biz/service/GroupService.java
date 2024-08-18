package kr.co.goms.web.oss.biz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.goms.web.oss.biz.repository.GroupRepository;
import kr.co.goms.web.oss.biz.service.dto.GroupDelInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupInsInDto;
import kr.co.goms.web.oss.biz.service.dto.GroupOutDto;
import kr.co.goms.web.oss.biz.service.dto.GroupUpdInDto;


@Service
@Transactional
public class  GroupService {

   private final Logger log = LoggerFactory.getLogger(GroupService.class);

   @Autowired GroupRepository groupRepository;

    public List<GroupOutDto> selectAllGroup(){
        GroupInDto inDto = new GroupInDto();
        List<GroupOutDto> list = null;
        list = groupRepository.selectAllGroup(inDto);
        return list;
   }

    public GroupIdxOutDto getGroup(GroupIdxInDto inDto){
        GroupIdxOutDto group = null;
        group = groupRepository.getGroup(inDto);
        return group;
   }

    public boolean delGroup(GroupDelInDto inDto){
        boolean isDel = groupRepository.delGroup(inDto);
        return isDel;
   }

    public boolean updGroup(GroupUpdInDto inDto){
        boolean isUpd = groupRepository.updGroup(inDto);
        return isUpd;
   }

    public boolean insGroup(GroupInsInDto inDto){
        boolean isIns = groupRepository.insGroup(inDto);
        return isIns;
   }
}
