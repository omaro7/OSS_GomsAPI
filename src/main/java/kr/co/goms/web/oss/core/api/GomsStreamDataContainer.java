package kr.co.goms.web.oss.core.api;

import org.springframework.context.ApplicationEvent;

import kr.co.goms.web.oss.core.constant.EnumStreamChannelType;

public class GomsStreamDataContainer extends ApplicationEvent{

	private static final long serialVersionUID = -2414352333403596539L;
	private String guid = "";
	private String publisher = "";
	private String profile = "";
	private String consumer = EnumStreamChannelType.PUBLIC.getChannelType();
	private String eventId = "";
	private String publishTime = "";
	private String payload = null;

	protected GomsStreamDataContainer(Object source) {
		super(source);
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	/*
	 * public <T> T getPayload(Class<T> clz) { AmsObjectUtils amsObjectUtils =
	 * ApiStatics.getAmsBizUtils().object(); return
	 * amsObjectUtils.jsonStringToObject(this.payload, clz); }
	 */

}
