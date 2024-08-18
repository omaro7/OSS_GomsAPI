package kr.co.goms.web.oss.core.constant;

public enum EnumHttpContentType {
	
	APPLICATION_XML	("Application/xml"),
	APPLICATION_JSON("Application/json"),
	TEXT_HTML		("text/html"),
	TEXT_XML		("text/xml"),
	TEXT_PLAIN		("text/plain"),
	URL_ENCODING	("application/x-www-form-urlencoded;charset=euc-kr"),
	;
	
	private final String contextType;
	
	public String getContextType() {
		return contextType;	
	}
	
	EnumHttpContentType(String contextType){
		this.contextType = contextType;
	}
	
}
