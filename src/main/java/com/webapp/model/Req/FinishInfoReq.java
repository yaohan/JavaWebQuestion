package com.webapp.model.Req;

import com.webapp.model.model.UserModel;

/**
 * Created by yao_han on 2015/12/23.
 */
public class FinishInfoReq extends BaseReq{
    private UserModel userModel;

    public FinishInfoReq() {
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
