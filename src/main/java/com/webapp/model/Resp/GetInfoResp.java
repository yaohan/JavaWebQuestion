package com.webapp.model.Resp;

import com.webapp.model.model.UserModel;
import com.webapp.model.table.Parents;
import com.webapp.model.table.Pupils;
import com.webapp.model.table.Teacher;

/**
 * Created by yao_han on 2016/3/24.
 */
public class GetInfoResp extends BaseResp{
    public static String DESC_SUCCESS = "获取成功";
    public static String DESC_FAILED = "获取失败";
    private UserModel userModel;

    public GetInfoResp() {
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
