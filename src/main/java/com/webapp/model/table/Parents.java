package com.webapp.model.table;

import com.webapp.model.model.UserModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zjm on 15-11-23.
 */

@Entity
@Table(name = "parents")
public class Parents {
    @Id
    @Column(name = "pId")
    private String pId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "head_portrait")
    private  String headPortrait;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "child_id")
    private String childId;

    public Parents() {}

    public Parents(String pId, String realName, Integer age, String gender, String address, String headPortrait, Date registerDate, String childId) {
        this.pId = pId;
        this.realName = realName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.headPortrait = headPortrait;
        this.registerDate = registerDate;
        this.childId = childId;
    }

    public void setAllVar(UserModel userModel){
        this.pId = userModel.getId();
        this.realName = userModel.getRealName();
        this.age = userModel.getAge();
        this.gender = userModel.getGender();
        this.address = userModel.getAddress();
        this.headPortrait = userModel.getHeadPortrait();
        this.registerDate = new Date();
        this.childId = userModel.getChildId();
    }

    public void setAllVar(String realName, Integer age, String gender, String address, String headPortrait, Date registerDate, String childId){
        this.realName = realName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.headPortrait = headPortrait;
        this.registerDate = registerDate;
        this.childId = childId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
