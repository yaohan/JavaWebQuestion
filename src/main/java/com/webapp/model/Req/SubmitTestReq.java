package com.webapp.model.Req;

import com.webapp.model.model.SelfTestModel;
import com.webapp.model.table.Question;

import java.util.List;

/**
 * Created by LENOVO on 2015/12/26.
 */
public class SubmitTestReq extends BaseReq {

    private SelfTestModel selfTestModel;
    public SubmitTestReq() {
    }

    public SelfTestModel getSelfTestModel() {
        return selfTestModel;
    }

    public void setSelfTestModel(SelfTestModel selfTestModel) {
        this.selfTestModel = selfTestModel;
    }
}
