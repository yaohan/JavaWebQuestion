package com.webapp.model.Resp;

import com.webapp.model.model.SelfTestModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LENOVO on 2016/1/4.
 */
public class GetSelfTestByIdResp extends BaseResp{
    public static String DESC_SUCCESS = "获取成功";
    private SelfTestModel selfTestModel;

    public GetSelfTestByIdResp() {
    }

    public SelfTestModel getSelfTestModel() {
        return selfTestModel;
    }

    public void setSelfTestModel(SelfTestModel selfTestModel) {
        this.selfTestModel = selfTestModel;
    }
}
