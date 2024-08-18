package kr.co.goms.web.oss.core.constant;

public enum EnumResponseType {
	
	STRING			("String"),
	JSON			("Json"),
	XML				("Xml"),
	OBJECT			("Object"),
	RESPONSE_ENTITY	("ResponseEntity"),
	RESPONSE		("Response"),
	RESPONSE_LIST	("ResponseList"),
	RESPONSE_MAP	("ResponseMap"),
	;
	
	private final String responseType;
	
	public String getResponseType() {
		return responseType;	
	}
	
	EnumResponseType(String responseType){
		this.responseType = responseType;
	}
	
}
