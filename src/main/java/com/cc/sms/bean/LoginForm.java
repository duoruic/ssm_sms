package com.cc.sms.bean;

/**
 * @project: ssm_sms
 * @description: 用户登录表单信息
 * @version: 1.0
 * @website: https://yubuntu0109.github.io/
 */
public class LoginForm {

    private String username;
    private String password;
    private String verifyCode;
    private Integer userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getverifyCode() {
        return verifyCode;
    }

    public void setverifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
