package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 题目收藏
 * Created by zjm on 15-11-26.
 */

@Entity
@Table(name = "collect_question")
@GenericGenerator(name = "systemUUID", strategy = "uuid")
public class CollectQuestion {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id")
    private String id;

    @Column(name = "psId")
    private String psId;

    @Column(name = "qId")
    private String qId;

    @Column(name = "collect_time")
    private Integer collectTime;

    @Column(name = "my_answer")
    private String myAnswer;

    public CollectQuestion() {
    }

    public CollectQuestion(String psId, String qId, Integer collectTime) {
        this.psId = psId;
        this.qId = qId;
        this.collectTime = collectTime;
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

    public Integer getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Integer collectTime) {
        this.collectTime = collectTime;
    }


    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }
}
