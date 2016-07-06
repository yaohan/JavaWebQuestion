package com.webapp.model.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yao_han on 2016/5/27.
 */
public class AnalysisInfo{
    private String testSite;
    private int right;
    private int wrong;
    private List<QuestionModel> questionModelList;

    public AnalysisInfo() {
        questionModelList = new ArrayList<QuestionModel>();
    }

    public void addQuestion(QuestionModel questionModel){
        questionModelList.add(questionModel);
    }

    public void addRight(){
        right++;
    }
    public void addWrong(){
        wrong++;
    }

    public String getTestSite() {
        return testSite;
    }

    public void setTestSite(String testSite) {
        this.testSite = testSite;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public List<QuestionModel> getQuestionModelList() {
        return questionModelList;
    }

    public void setQuestionModelList(List<QuestionModel> questionModelList) {
        this.questionModelList = questionModelList;
    }
}
