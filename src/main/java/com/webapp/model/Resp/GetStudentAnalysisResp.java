package com.webapp.model.Resp;

import com.webapp.model.model.AnalysisInfo;
import com.webapp.model.model.QuestionModel;
import com.webapp.model.table.SelfTest;
import com.webapp.model.table.StudentHomework;
import com.webapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao_han on 2016/5/25.
 */
public class GetStudentAnalysisResp extends BaseResp {
    private List<AnalysisInfo> analysisInfos;

    public GetStudentAnalysisResp() {
    }

    public List<AnalysisInfo> getAnalysisInfos() {
        return analysisInfos;
    }

    public void setAnalysisInfos(List<AnalysisInfo> analysisInfos) {
        this.analysisInfos = analysisInfos;
    }
}
