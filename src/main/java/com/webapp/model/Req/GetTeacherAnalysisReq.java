package com.webapp.model.Req;

/**
 * Created by yao_han on 2016/5/27.
 */
public class GetTeacherAnalysisReq extends BaseReq {
    private String teacherId;

    public GetTeacherAnalysisReq() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
