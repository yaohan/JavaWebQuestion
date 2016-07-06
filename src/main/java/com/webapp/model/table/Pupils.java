package com.webapp.model.table;

import com.webapp.model.model.UserModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by zjm on 15-11-23.
 */

@Entity
@Table(name = "pupils")
public class Pupils {

    @Id
    @Column(name = "ps_id",nullable = false)
    private String psId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "head_portrait")
    private String headPortrait;

    @Column(name="pId")
    private String pId;

    @Column(name="cId")
    private String cId;

    @Column(name="sId")
    private String sId;

    @Column(name="schoolName")
    private String schoolName;

    @Column(name = "className")
    private String className;

    public Pupils() {}

    public Pupils(String psId, String realName, Integer age, String address, String gender, Date registerDate, String headPortrait, String pId, String cId, String sId, String schoolName, String className) {
        this.psId = psId;
        this.realName = realName;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.registerDate = registerDate;
        this.headPortrait = headPortrait;
        this.pId = pId;
        this.cId = cId;
        this.sId = sId;
        this.schoolName = schoolName;
        this.className = className;
    }



    public void setAllVar(String realName, Integer age, String address, String gender, Date registerDate, String headPortrait, String pId, String cId, String sId, String schoolName, String className){
        this.realName = realName;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.registerDate = registerDate;
        this.headPortrait = headPortrait;
        this.pId = pId;
        this.cId = cId;
        this.sId = sId;
        this.className = className;
        this.schoolName = schoolName;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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

    @Override
    public String toString() {
        return " psId = " + psId + " realName = " + realName + " age = "
                + age + " address = " + address + " gender = " + gender
                + " registerDate = " + registerDate + " headPortrait = " + headPortrait
                + " pId = " + pId + " cId = " + cId + " sId = "
                + sId ;
    }

}
