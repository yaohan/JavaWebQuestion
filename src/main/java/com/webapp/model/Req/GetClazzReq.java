package com.webapp.model.Req;

/**
 * Created by yao_han on 2015/12/25.
 */
public class GetClazzReq extends BaseReq{
    private String schoolId;
    public GetClazzReq() {
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
