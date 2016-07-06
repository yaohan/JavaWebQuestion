package com.webapp.model.model;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.webapp.model.table.Question;
import com.webapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2015/12/29.
 */
public class QuestionModel {
    private String qId;
    private String stem;
    private List<String> options;
    private String answer;
    private String resolve;
    private List<String> testSites;
    private String myAnswer;
    private Boolean isCollection;

    public QuestionModel() {

    }

    public QuestionModel(Question question){
        this.qId = question.getqId();
        this.stem = question.getCasualWorking();
        String[] options = JsonUtils.readValue(question.getOptions(),String[].class);
        this.options = new ArrayList<String>();
        for(String s:options){
            this.options.add(s);
        }
        this.answer = question.getAnswer();
        this.resolve = question.getResolve();
        String[] testSites = JsonUtils.readValue(question.getTestSites(),String[].class);
        this.testSites = new ArrayList<String>();
        for(String s:testSites){
            this.testSites.add(s);
        }
    }

    public QuestionModel(Question question, String myAnswer, Boolean isCollection){
        this(question);
        this.setMyAnswer(myAnswer);
        this.setCollection(isCollection);
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    public List<String> getTestSites() {
        return testSites;
    }

    public void setTestSites(List<String> testSites) {
        this.testSites = testSites;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public Boolean getCollection() {
        return isCollection;
    }

    public void setCollection(Boolean collection) {
        this.isCollection = collection;
    }
}
