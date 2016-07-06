package com.webapp.model.Resp;

import com.webapp.model.model.UserModel;
import com.webapp.model.table.Account;

import java.util.List;

/**
 * Created by yao_han on 2016/4/5.
 */
public class SearchPersonResp extends BaseResp{
    public static final String DESC_SUCCESS = "获取成功";
    private List<UserModel> list;

    public SearchPersonResp() {
    }

    public List<UserModel> getList() {
        return list;
    }

    public void setList(List<UserModel> list) {
        this.list = list;
    }
}
