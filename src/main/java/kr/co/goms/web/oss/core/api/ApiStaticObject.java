package kr.co.goms.web.oss.core.api;

public class ApiStaticObject<E> {
	private E mObject;
	
	public E get() {
		return this.mObject;
	}
	
	public void set(E object) {
		this.mObject = object;
	}
}
