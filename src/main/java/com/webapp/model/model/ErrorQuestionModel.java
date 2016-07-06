package com.webapp.model.model;

import com.webapp.model.table.Question;

/**
 * Created by LENOVO on 2016/1/26.
 */
public class ErrorQuestionModel {
    private int errorCount;
    private QuestionModel questionModel;

    public ErrorQuestionModel() {
    }

    public ErrorQuestionModel(QuestionModel questionModel,int errorCount) {
        this.questionModel = questionModel;
        this.errorCount = errorCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void setQuestionModel(QuestionModel questionModel) {
        this.questionModel = questionModel;
    }
}
