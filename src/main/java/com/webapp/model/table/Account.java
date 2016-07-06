package com.webapp.model.table;

import com.webapp.dao.SystemDao;
import com.webapp.model.model.UserModel;
import com.webapp.utils.JsonUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zjm on 15-12-8.
 */
@Entity
@Table(name = "account")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class Account {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "systemUUID")
    private String id;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Integer role;

    @Column(name = "registerDate")
    private Date registerDate;
    @Column(name = "realName")
    private String realName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "headPortrait")
    private String headPortrait;
    @Column(name = "schoolId")
    private String schoolId;
    @Column(name = "classId")
    private String classId;
    @Column(name = "parentId")
    private String parentId;
    @Column(name = "subjectId")
    private String subjectId;
    @Column(name = "courseId")
    private String courseId;
    @Column(name = "childId")
    private String childId;

    public Account() {}


    public String getId() {
        return id;
    }

    public void fillByUserModel(UserModel userModel){
        role = userModel.getRole();
        if(registerDate == null){
            registerDate = new Date();
        }
        realName = userModel.getRealName();
        gender = userModel.getGender();
        age = userModel.getAge();
        address = userModel.getAddress();
        contact = userModel.getContact();
        headPortrait = userModel.getHeadPortrait();
        schoolId = userModel.getSchoolId();
        classId = userModel.getClassId();
        parentId = userModel.getParentId();
        subjectId = userModel.getSubjectId();
        courseId = userModel.getCourseId();
        childId = userModel.getChildId();
    }

    public UserModel toUserModel(SystemDao dao){
        System.out.println("start to UserModel");
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setRole(role);
        userModel.setRealName(realName);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setAddress(address);
        userModel.setContact(contact);
        if(headPortrait  == null || headPortrait.length() == 0){
            userModel.setHeadPortrait(null);
        }else{
            userModel.setHeadPortrait("");
        }
        System.out.println("schoolId"+schoolId);
        System.out.println("schoolId"+(schoolId==null));
        userModel.setSchoolId(schoolId);
        if(schoolId !=null && !schoolId.equals("")){
            userModel.setSchoolName(dao.getSchool(schoolId).getSchool());
        }
        System.out.println("classId");
        userModel.setClassId(classId);
        if(classId != null && !classId.equals("")){
            userModel.setClassName(dao.getClazz(classId).getGrade()+dao.getClazz(classId).getCls());
        }
        System.out.println("parentId");
        userModel.setParentId(parentId);
        if(parentId !=null && !parentId.equals("")){
            userModel.setParentName(dao.getAccount(parentId).getRealName());
        }
        System.out.println("subjectId");
        userModel.setSubjectId(subjectId);
        System.out.println("courseId");
        userModel.setCourseId(courseId);
        System.out.println("childId");
        userModel.setChildId(childId);
        if(childId != null && !childId.equals("")){
            userModel.setChildName(dao.getAccount(childId).getRealName());
        }
        System.out.println("end to UserModel"+ JsonUtils.toJson(userModel));
        return userModel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
