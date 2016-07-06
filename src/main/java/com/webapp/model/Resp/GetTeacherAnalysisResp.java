package com.webapp.model.Resp;

import com.webapp.model.model.AnalysisInfo;
import com.webapp.model.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao_han on 2016/5/27.
 */
public class GetTeacherAnalysisResp extends BaseResp {
    private List<AnalysisInfo> analysisInfos;

    public GetTeacherAnalysisResp() {
    }

    public List<AnalysisInfo> getAnalysisInfos() {
        return analysisInfos;
    }

    public void setAnalysisInfos(List<AnalysisInfo> analysisInfos) {
        this.analysisInfos = analysisInfos;
    }
}
