package kr.co.goms.web.oss.core.constant;

public enum EnumStreamChannelType {
	
	IAM			("iam"),
	EAM			("eam"),
	MAM			("mam"),
	PUBLIC		("public"),
	;
	
	private final String channelType;
	
	public String getChannelType() {
		return channelType;	
	}
	
	EnumStreamChannelType(String channelType){
		this.channelType = channelType;
	}
	
}
