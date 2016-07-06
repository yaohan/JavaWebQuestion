package com.webapp.model.model;

import com.webapp.model.table.Question;
import com.webapp.model.table.SelfTest;
import com.webapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LENOVO on 2015/12/29.
 */
public class SelfTestModel {

    //自测ID
    private String id;
    //自测名称
    private String name;
    //学生ID
    private String psId;
    //自测状态
    private int state;
    //问题列表
    private List<QuestionModel> questionList;
    //答案列表
    private List<String> answerList;
    //答案正误
    private List<Boolean> resultList;
    //自测成绩
    private double grade;
    //知识点列表
    private List<String> testSitesList;

    private Date newTime;

    public SelfTestModel() {
    }


    public SelfTestModel(SelfTest selfTest,List<Question> questions,List<String> collectQIdList){
        id = selfTest.getId();
        name = selfTest.getName();
        psId = selfTest.getPsId();
        state = selfTest.getState();
        newTime = selfTest.getNewTime();
        int qNumber = questions.size();
        String[] answers = JsonUtils.readValue(selfTest.getAnswers(),String[].class);
        Boolean[] answerRights = JsonUtils.readValue(selfTest.getResult(),Boolean[].class);
        answerList = new ArrayList<String>();
        resultList = new ArrayList<Boolean>();
        questionList = new ArrayList<QuestionModel>();
        testSitesList = new ArrayList<String>();
        for(int i=0;i<qNumber;i++){
            QuestionModel questionModel= new QuestionModel(questions.get(i));
            questionModel.setCollection(collectQIdList.contains(selfTest.getqId()));
            System.out.println("answer:"+answers[i]);
            questionModel.setMyAnswer(answers[i]);
            questionList.add(questionModel);
            answerList.add(answers[i]);
            resultList.add(answerRights[i]);
            String[] sites = JsonUtils.readValue(questionModel.getTestSites(),String[].class);
            for(String s:sites){
                if(!testSitesList.contains(s)){
                    testSitesList.add(s);
                }
            }
        }
        grade = selfTest.getGrade();
    }

    public SelfTest toSelfTest(SelfTest selfTest){
//        selfTest.setNewTime(selfTest.getNewTime());
        selfTest.setAnswers(JsonUtils.toJson(this.getAnswerList()));
        selfTest.setResult(JsonUtils.toJson(this.getResultList()));
        selfTest.setGrade(this.getGrade());
        selfTest.setState(this.getState());
        return selfTest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<QuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public List<Boolean> getResultList() {
        return resultList;
    }

    public void setResultList(List<Boolean> resultList) {
        this.resultList = resultList;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<String> getTestSitesList() {
        return testSitesList;
    }

    public void setTestSitesList(List<String> testSitesList) {
        this.testSitesList = testSitesList;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }
}
