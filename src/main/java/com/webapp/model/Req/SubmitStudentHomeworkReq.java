package com.webapp.model.Req;

import com.webapp.model.model.StudentHomeworkModel;
import com.webapp.model.table.StudentHomework;

/**
 * Created by yao_han on 2016/4/18.
 */
public class SubmitStudentHomeworkReq extends BaseReq {
    private StudentHomeworkModel studentHomeworkModel;

    public SubmitStudentHomeworkReq() {
    }

    public StudentHomeworkModel getStudentHomeworkModel() {
        return studentHomeworkModel;
    }

    public void setStudentHomeworkModel(StudentHomeworkModel studentHomeworkModel) {
        this.studentHomeworkModel = studentHomeworkModel;
    }
}
