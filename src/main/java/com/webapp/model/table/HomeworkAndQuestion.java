package com.webapp.model.table;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 作业题目表
 * Created by zjm on 15-11-25.
 */

@Entity
@Table(name = "homework_question")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class HomeworkAndQuestion {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id")
    private String id;

    @Column(name = "hId")
    private String hId;

    @Column(name = "qId")
    private String qId;

    public HomeworkAndQuestion(String hId,String qId) {
        this.hId = hId;
        this.qId = qId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }
}
