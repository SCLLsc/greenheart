package entity;

public class StatusCode {
    public static final int SUCESS=2000;//成功
    public static final int ERROR=2001;//失败
    public static final int LOGINERROR=2002;//登录失败（用户名或密码错误）
    public static final int ACCESSEERROR=2003;//权限不足（未登录）
    public static final int REMOTEERROR=2004;//远程调用失败
    public static final int REPERROR=2005;//重复操作
}
