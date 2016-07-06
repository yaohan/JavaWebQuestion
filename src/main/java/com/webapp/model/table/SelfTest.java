package com.webapp.model.table;

import com.webapp.utils.JsonUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zjm on 15-11-26.
 */
@Entity
@Table(name = "self_test")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class SelfTest {

    @Id
    @GeneratedValue(generator = "systemUUID")
    private String id;

    @Column(name = "psId")
    private String psId;

    @Column(name = "qId")
    private String qId;

    @Column(name = "answers")
    private String answers;

    @Column(name = "grade")
    private double grade;

    @Column(name = "name")
    private String name;

    @Column(name = "result")
    private String result;

    @Column(name = "state")
    private int state;

    @Column(name = "newDate")
    private Date newTime;
    public SelfTest() {}

    public SelfTest(String psId, double grade, String name, int state,List<Question> questionList, Date newTime) {
        List<String> qIds = new ArrayList<String>();
        List<String> answers = new ArrayList<String>();
        List<Boolean> results = new ArrayList<Boolean>();
        for(int i=0;i<questionList.size();i++){
            qIds.add(questionList.get(i).getqId());
            answers.add("");
            results.add(false);
        }
        this.psId = psId;
        this.grade = grade;
        this.name = name;
        this.state = state;
        this.qId = JsonUtils.toJson(qIds);
        this.answers = JsonUtils.toJson(answers);
        this.result = JsonUtils.toJson(results);
        this.newTime = newTime;
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

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }
}
