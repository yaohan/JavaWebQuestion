package com.webapp.model.table;

import com.webapp.model.model.UserModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zjm on 15-11-23.
 */

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "tId")
    String tId;

    @Column(name = "real_name")
    private String realName;

    @Column(name  = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "subject")
    private String subject;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "head_portrait")
    private String headPortrait;

    @Column(name = "sId")
    private String sId;

    @Column(name = "cId")
    private String cId;

    @Column(name = "schoolName")
    private String schoolName;

    @Column(name = "className")
    private String className;

    public Teacher() {}

    public Teacher(String tId, String realName, String gender, Integer age, String address, String subject, Date registerDate, String headPortrait, String sId, String cId, String schoolName, String className) {
        this.tId = tId;
        this.realName = realName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.subject = subject;
        this.registerDate = registerDate;
        this.headPortrait = headPortrait;
        this.sId = sId;
        this.cId = cId;
        this.schoolName = schoolName;
        this.className = className;
    }

    public void setAllVar(UserModel userModel){

    }

    public void setAllVar(String realName, String gender, Integer age, String address, String subject, Date registerDate, String headPortrait, String sId, String cId){
        this.realName = realName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.subject = subject;
        this.registerDate = registerDate;
        this.headPortrait = headPortrait;
        this.sId = sId;
        this.cId = cId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
