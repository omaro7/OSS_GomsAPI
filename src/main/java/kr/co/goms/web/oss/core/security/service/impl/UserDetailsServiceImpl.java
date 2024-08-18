package kr.co.goms.web.oss.core.security.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.goms.web.oss.core.security.service.domain.Member;
import kr.co.goms.web.oss.core.security.service.repository.UserDetailsRepository;


@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserDetailsRepository repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 스프링 시큐리티 인증 동작방식은 아이디/패스워드를 한 번에 조회하는 방식이 아닌
        // 아이디만 이용해서 사용자 정보를 로딩하고 이후 패스워드를 검증하는 방식이다.
        log.trace("username : " + username);

        // security: 사용자 계정 먼저 확인 (username)
        Member member = repo.findMemberByMbId(username);

        if(member ==  null) {
            throw new UsernameNotFoundException(username);
        }

        // 인증 거치는 과정 (사용자가 폼에 입력한 정보가 맞는지 확인)
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(member.getMbId());
        userDetails.setPassword(passwordEncoder.encode(member.getMbPwd()));
        //        .roles("USER");
		
        return userDetails;

    }
    
    
}
