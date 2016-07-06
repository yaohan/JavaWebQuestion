package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zjm on 15-11-24.
 */

@Entity
@Table(name = "class")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class Clazz {
    @Id
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "cId")
    private String cId;

    @Column(name = "schoolId")
    private String schoolId;

    @Column(name = "grade")
    private String grade;

    @Column(name = "class")
    private String cls;
    @Column(name = "teacherId")
    private String teacherId;
    @Column(name = "studentIds")
    private String studentIds;

    //空构造函数
    public Clazz() {}

    public Clazz(String schoolId, String grade, String cls) {
        this.schoolId = schoolId;
        this.grade = grade;
        this.cls = cls;
    }

    public String getcId() {
        return cId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public String getGrade() {
        return grade;
    }

    public String getCls() {
        return cls;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCls(String cls) {
        this.cls = cls;
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
}
