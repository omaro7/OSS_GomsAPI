package kr.co.goms.web.oss.core.api;

import kr.co.goms.web.oss.shared.util.GomsBizUtils;

public class ApiStatics {

	private static ApiStaticObject<GomsBizUtils> amsBizUtils = new ApiStaticObject<GomsBizUtils>();
	//private static ApiStaticObject<AsyncInvoker> examAsyncInvoker = new ApiStaticObject<AsyncInvoker>();
	
	public static GomsBizUtils getAmsBizUtils() {
		return amsBizUtils.get();
	}

	public static void setAmsBizUtils(GomsBizUtils _amsBizUtils) {
		amsBizUtils.set(_amsBizUtils);
	}
}
