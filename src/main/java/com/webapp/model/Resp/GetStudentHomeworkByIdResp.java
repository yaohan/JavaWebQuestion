package com.webapp.model.Resp;

import com.webapp.model.model.StudentHomeworkModel;
import com.webapp.model.table.StudentHomework;

/**
 * Created by yao_han on 2016/4/16.
 */
public class GetStudentHomeworkByIdResp extends BaseResp {
    public static final String DESC_SUCCESS = "获取成功";
    private StudentHomeworkModel studentHomeworkModel;

    public GetStudentHomeworkByIdResp() {
    }

    public StudentHomeworkModel getStudentHomeworkModel() {
        return studentHomeworkModel;
    }

    public void setStudentHomeworkModel(StudentHomeworkModel studentHomeworkModel) {
        this.studentHomeworkModel = studentHomeworkModel;
    }
}
