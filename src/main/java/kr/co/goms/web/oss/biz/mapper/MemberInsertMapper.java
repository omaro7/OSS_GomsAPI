package kr.co.goms.web.oss.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.goms.web.oss.biz.domain.MemberInsertIn;
import kr.co.goms.web.oss.biz.domain.MemberOut;
import kr.co.goms.web.oss.biz.domain.MemberUpdateIn;

@Mapper
public interface MemberInsertMapper {
	
	@Insert("INSERT INTO member (mb_id, mb_pwd, mb_name, mb_role, regdate) VALUES (#{mbId}, #{mbPwd}, #{mbName}, #{mbRole}, #{regdate})")
	@Options(useGeneratedKeys = true, keyProperty = "mbIdx")
	Long insertMember(MemberInsertIn memberInsertIn);
	
	@Delete("DELETE FROM member WHERE mb_idx = #{mbIdx}")
    void deleteMember(Long mbIdx);
	
	//DATE_FORMAT(regdate, '%Y-%m-%d') as regdate
	@Select("SELECT "
			+ "mb_idx as mbIdx, mb_id as mbId, mb_pwd as mbPwd, mb_name as mbName, "
            + "mb_role as mbRole, regdate "
            + "FROM member")
	List<MemberOut> selectAllMembers();

	@Select("SELECT "
			+ "mb_idx as mbIdx, mb_id as mbId, mb_pwd as mbPwd, mb_name as mbName, "
            + "mb_role as mbRole, regdate "
            + "FROM member "
            + "WHERE mb_idx =  #{mbIdx} LIMIT 1")
	MemberOut selectMember(Long mbIdx);
	
	@Update("UPDATE member SET mb_pwd = #{mbPwd}, mb_name = #{mbName}, mb_role = #{mbRole} " +
            "WHERE mb_idx = #{mbIdx}")
    void updateMember(MemberUpdateIn memberUpdateIn);
}
