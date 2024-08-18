package kr.co.goms.web.oss.core.security.service.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Access(AccessType.FIELD)
@Table(name = "member")
@SequenceGenerator(
		name="MEMBER_SEQ_GEN",
		sequenceName="MEMBER_SEQ",
		initialValue=1,
		allocationSize=1
)
//public class Member  implements Persistable<Long> {
public class Member implements Persistable<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mb_idx")
    private Long mbIdx;
    
    @Column(name="mb_id", unique = true, nullable = false)
    private String mbId;

    @Column(name="mb_pwd", nullable = false)
    private String mbPwd;

    @Column(name="mb_name")
    private String mbName;

    @Column(name="mb_role")
    private String mbRole;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="regdate")
    private Date regdate;
    
    @Builder
    public Member(Long mbIdx, String mbId, String mbPwd, String mbName, String mbRole) {
    	this.mbIdx = mbIdx;
        this.mbId = mbId;
        this.mbPwd = mbPwd;
        this.mbName = mbName;
        this.mbRole = mbRole;
        this.regdate = new Date(); // Set the regdate to the current date
    }
    
 // Getters and setters
    public Long getMbIdx() {
        return mbIdx;
    }

    public void setMbIdx(Long mbIdx) {
        this.mbIdx = mbIdx;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getMbPwd() {
        return mbPwd;
    }

    public void setMbPwd(String mbPwd) {
        this.mbPwd = mbPwd;
    }

    public String getMbName() {
        return mbName;
    }

    public void setMbName(String mbName) {
        this.mbName = mbName;
    }

    public String getMbRole() {
        return mbRole;
    }

    public void setMbRole(String mbRole) {
        this.mbRole = mbRole;
    }
    
    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Transient // 해당 데이터를 테이블의 컬럼과 매핑 시키지 않는다.
    @Builder.Default
	private boolean isNew = true;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.mbIdx;
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}
	
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	@PrePersist
	@PostLoad
	void markNotNew() {
		this.isNew = false;
	}


}
