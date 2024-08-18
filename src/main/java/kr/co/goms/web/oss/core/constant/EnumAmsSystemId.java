package kr.co.goms.web.oss.core.constant;

public enum EnumAmsSystemId {
	
	IAM("iam"),
	EAM("eam"),
	MAM("mam"),
	;
	
	private final String systemId;
	
	public String getSystemId() {
		return systemId;	
	}
	
	EnumAmsSystemId(String systemId){
		this.systemId = systemId;
	}
	
}
