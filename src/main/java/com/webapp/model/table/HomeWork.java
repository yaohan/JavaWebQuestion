package com.webapp.model.table;

import com.webapp.model.model.ModelConfig;
import com.webapp.utils.JsonUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * 作业表
 * Created by zjm on 15-11-24.
 */
@Entity
@Table(name = "homework")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class HomeWork {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id")
    private String id;

    @Column(name = "teacherId")
    private String teacherId;

    @Column(name = "studentIds")
    private String studentIds;

    @Column(name = "questionIds")
    private String questionIds;

    @Column(name = "state")
    private Integer state;

    @Column(name = "name")
    private String name;

    @Column(name = "newDate")
    private Date newDate;

    public HomeWork() {
    }

    public HomeWork(String teacherId, String questionIds, String name, Date newDate) {
        this.teacherId = teacherId;
        this.studentIds = JsonUtils.toJson(new ArrayList<String>());
        this.questionIds = questionIds;
        this.state = ModelConfig.STATE_NEW;
        this.name = name;
        this.newDate = newDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

}
