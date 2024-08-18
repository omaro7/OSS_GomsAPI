package kr.co.goms.web.oss.biz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.goms.web.oss.biz.repository.MemberRepository;
import kr.co.goms.web.oss.biz.service.dto.MemberDelInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberIdxOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberInsInDto;
import kr.co.goms.web.oss.biz.service.dto.MemberOutDto;
import kr.co.goms.web.oss.biz.service.dto.MemberUpdInDto;


@Service
@Transactional
public class  MemberService {

   private final Logger log = LoggerFactory.getLogger(MemberService.class);

   @Autowired MemberRepository memberRepository;

    public List<MemberOutDto> selectAllMember(){
        MemberInDto inDto = new MemberInDto();
        List<MemberOutDto> list = null;
        list = memberRepository.selectAllMember(inDto);
        return list;
   }

    public MemberIdxOutDto getMember(MemberIdxInDto inDto){
        MemberIdxOutDto member = null;
        member = memberRepository.getMember(inDto);
        return member;
   }

    public boolean delMember(MemberDelInDto inDto){
        boolean isDel = memberRepository.delMember(inDto);
        return isDel;
   }

    public boolean updMember(MemberUpdInDto inDto){
    	boolean isUpd = memberRepository.updMember(inDto);
        return isUpd;
   }

    public boolean insMember(MemberInsInDto inDto){
    	boolean isIns = memberRepository.insMember(inDto);
        return isIns;
   }
}
