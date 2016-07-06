package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 题目表
 * Created by zjm on 15-11-24.
 */
@Entity
@Table(name = "question")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class Question {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "qId")
    private String qId;

    @Column(name = "casual_working",nullable = false)
    private String casualWorking;

    @Column(name = "options",nullable = false)
    private String options;

    @Column(name = "answer",nullable = false)
    private String answer;

    //解析
    @Column(name = "resolve",nullable = false)
    private String resolve;

    //考点
    @Column(name = "test_sites")
    private String testSites;

    public Question() {}

    public Question(String casualWorking, String options, String answer, String resolve, String testSites) {
        this.casualWorking = casualWorking;
        this.options = options;
        this.answer = answer;
        this.resolve = resolve;
        this.testSites = testSites;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getCasualWorking() {
        return casualWorking;
    }

    public void setCasualWorking(String casualWorking) {
        this.casualWorking = casualWorking;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
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

    public String getTestSites() {
        return testSites;
    }

    public void setTestSites(String testSites) {
        this.testSites = testSites;
    }
}
