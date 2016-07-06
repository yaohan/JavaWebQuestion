package com.webapp.model.Resp;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by yao_han on 2015/12/23.
 */
public class LoginResp extends BaseResp {
    public static int ROLE_NULL = -1;
    public static int ROLE_PUPILS = 1;
    public static int ROLE_TEACHER = 2;
    public static int ROLE_PARENT = 3;


    public static String DESC_SUCCESS = "登录成功";
    public static String DESC_ERROR = "手机号或密码错误";
    private int type;
    private String id;

    public LoginResp() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
