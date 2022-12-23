package entity;


import lombok.Data;

//用于控制器类返回结果
@Data
public class JsonResult<T> {

    public boolean flag;//是否成功
    private int code;//返回码
    private String message;//返回信息
    private Object data;//返回数据

    public JsonResult() {
    }

    public JsonResult(boolean flag, int code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public JsonResult(boolean flag, int code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static JsonResult<String> fail(int code,String message){
        JsonResult<String> stringJsonResult = new JsonResult<String>();
        stringJsonResult.setMessage(message);
        stringJsonResult.setCode(code);
        return stringJsonResult;
    }
}
