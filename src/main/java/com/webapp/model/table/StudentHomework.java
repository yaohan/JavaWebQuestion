package com.webapp.model.table;

import com.webapp.model.model.CommentInfo;
import com.webapp.model.model.ModelConfig;
import com.webapp.model.model.StudentHomeworkModel;
import com.webapp.utils.JsonUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjmvic on 2015/12/12.
 */
@Entity
@Table(name = "pupils_homework")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class StudentHomework {
    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id",nullable = false)
    private String id;

    //学生ID
    @Column(name = "studentId")
    private String studentId;

    //作业ID
    @Column(name = "homeworkId")
    private String homeworkId;

    //答案
    @Column(name = "answers")
    private String answers;

    //题目正误
    @Column(name = "results")
    private String results;

    //评价
    @Column(name = "comments")
    private String comments;

    //成绩
    @Column(name = "grade")
    private double grade;

    @Column(name = "state")
    private int state;


    public StudentHomework() {
    }

    public StudentHomework(String studentId, String homeworkId, int count) {
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        List<String> answerList = new ArrayList<String>();
        List<Boolean> resultList = new ArrayList<Boolean>();
        List<CommentInfo> commentInfos= new ArrayList<CommentInfo>();
        for(int i=0;i<count;i++){
            answerList.add("");
            resultList.add(false);
        }
        answers = JsonUtils.toJson(answerList);
        results = JsonUtils.toJson(resultList);
        comments = JsonUtils.toJson(commentInfos);
        grade = 0;
        state = ModelConfig.STATE_NEW;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
