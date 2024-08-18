package kr.co.goms.web.oss.core.security.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.goms.web.oss.core.security.service.domain.Member;


 
public interface  UserDetailsRepository extends JpaRepository<Member, Long> {
	Member findMemberByMbId(String mbId);
}
