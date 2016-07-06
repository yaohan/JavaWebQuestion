package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 错题
 * Created by zjm on 15-11-26.
 */
@Entity
@Table(name = "error_question")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class ErrorQuestion {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id")
    private String id;

    //小学生ID
    @Column(name = "psId")
    private String psId;

    //题目ID
    @Column(name = "qId")
    private String qId;

    @Column(name = "my_answer")
    private String myAnswer;

    @Column(name = "error_time")
    private Integer errorTime;

    public ErrorQuestion() {
    }

    public ErrorQuestion(String psId, String qId, int errorTime, String myAnswer) {
        this.psId = psId;
        this.qId = qId;
        this.errorTime = errorTime;
        this.myAnswer = myAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public Integer getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Integer errorTime) {
        this.errorTime = errorTime;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }
}
