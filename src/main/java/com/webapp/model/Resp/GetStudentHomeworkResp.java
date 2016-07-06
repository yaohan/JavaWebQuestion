package com.webapp.model.Resp;

import com.webapp.model.model.TestHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by yao_han on 2016/4/16.
 */
public class GetStudentHomeworkResp extends BaseResp{
    public static final String DESC_SUCCESS = "获取成功";
    List<TestHistory> homeworkList;

    public GetStudentHomeworkResp() {
    }

    public List<TestHistory> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<TestHistory> homeworkList) {
        this.homeworkList = homeworkList;
    }
}
