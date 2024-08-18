package kr.co.goms.web.oss.core.api;
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    // 생성자
    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // 기본 생성자
    public ApiResponse() {}

    // Getter and Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
