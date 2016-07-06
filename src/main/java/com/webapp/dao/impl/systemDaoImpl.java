package com.webapp.dao.impl;

import com.webapp.dao.SystemDao;
import com.webapp.model.table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yao_han on 2016/4/5.
 */
@Transactional
@Repository
public class systemDaoImpl implements SystemDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    private HibernateTemplate hibernateTemplate;
//************************************************Account*************************************************************//
    public List<Account> queryAccount(String phoneNumber, String password) {
        return (List<Account>)hibernateTemplate.find("from Account where phoneNumber = ? and password = ?",phoneNumber,password);
    }

    public List<Account> queryAccount(int role, String name) {
        return (List<Account>)hibernateTemplate.find("from Account where role = ? and realName = ?",role,name);
    }

    public List<Account> queryAccount(int role, String schoolId, String classId) {
        return (List<Account>)hibernateTemplate.find("from Account where role = ? and schoolId = ? and classId = ?",role,schoolId,classId);
    }

    public List<Account> queryAccount(String phoneNumber) {
        return (List<Account>)hibernateTemplate.find("from Account where phoneNumber = ?",phoneNumber);
    }

    public void updateAccount(Account accout) {
        hibernateTemplate.update(accout);
    }

    public void saveAccount(Account account) {
        hibernateTemplate.save(account);
    }

    public Account getAccount(String id) {
        return hibernateTemplate.get(Account.class,id);
    }

//************************************************School*************************************************************//
    public School getSchool(String id) {
        return hibernateTemplate.get(School.class,id);
    }

    public List<School> querySchool(String province, String city, String area, String school) {
        return (List<School>)hibernateTemplate.find("from School where province = ? and city = ? and area = ? and school = ?",province,city,area,school);
    }

    public void saveSchool(School school) {
        hibernateTemplate.save(school);
    }

    public List<School> querySchool(){
        return (List<School>)hibernateTemplate.find("from School");
    }

//************************************************class***************************************************************//
    public Clazz getClazz(String id) {
        return hibernateTemplate.get(Clazz.class,id);
    }

    public List<Clazz> queryClazz(String schoolId) {
        return (List<Clazz>) hibernateTemplate.find("from Clazz where schoolId= ?",schoolId);
    }

    public List<Clazz> queryClazz(String schoolId, String grade, String clazz) {
        return (List<Clazz>) hibernateTemplate.find("from Clazz where schoolId = ? and grade = ? and cls = ?",schoolId,grade,clazz);
    }

    public void saveClazz(Clazz clazz) {
        hibernateTemplate.save(clazz);
    }

    public void updateClazz(Clazz clazz) {
        hibernateTemplate.update(clazz);
    }


//************************************************Question*************************************************************//

    public List<Question> queryQuestion() {
        return (List<Question>)hibernateTemplate.find("from Question");
    }

    public Question getQuestion(String id) {
        return hibernateTemplate.get(Question.class,id);
    }

//************************************************SelfTest*************************************************************//
    public void saveSelfTest(SelfTest selfTest) {
        hibernateTemplate.save(selfTest);
    }

    public List<SelfTest> querySelfTest(String studentId) {
        return (List<SelfTest>) hibernateTemplate.find("from SelfTest where psId = ?",studentId);
    }

    public SelfTest getSelfTest(String id) {
        return hibernateTemplate.get(SelfTest.class,id);
    }

    public void updateSelfTest(SelfTest selfTest) {
        hibernateTemplate.update(selfTest);
    }
//************************************************ErrorQuestion*******************************************************//

    public List<ErrorQuestion> queryErrorQuestion(String studentId) {
        return (List<ErrorQuestion>) hibernateTemplate.find("from ErrorQuestion where psId = ?",studentId);
    }

    public List<ErrorQuestion> queryErrorQuestion(String studentId, String questionId) {
        return (List<ErrorQuestion>) hibernateTemplate.find("from ErrorQuestion where psId = ? and qId = ?",studentId,questionId);
    }

    public void saveErrorQuestion(ErrorQuestion errorQuestion) {
        hibernateTemplate.save(errorQuestion);
    }

    public void updateErrorQuestion(ErrorQuestion errorQuestion) {
        hibernateTemplate.update(errorQuestion);
    }

//************************************************CollectQuestion*******************************************************//

    public List<CollectQuestion> queryCollectQuestion(String studentId) {
        return (List<CollectQuestion>) hibernateTemplate.find("from CollectQuestion where psId = ?",studentId);
    }

    public List<CollectQuestion> queryCollectQuestion(String studentId, String quesitonId) {
        return (List<CollectQuestion>) hibernateTemplate.find("from CollectQuestion where psId = ? and qId = ?",studentId,quesitonId);
    }

    public void saveCollectQuestion(CollectQuestion collectQuestion) {
        hibernateTemplate.save(collectQuestion);
    }

    public void deleteCollectQuestion(CollectQuestion collectQuestion) {
        hibernateTemplate.delete(collectQuestion);
    }

//***************************************************Homework***********************************************************//
    public void saveHomeWork(HomeWork homeWork) {
        hibernateTemplate.save(homeWork);
    }

    public List<HomeWork> queryHomework(String teacherId) {
        return (List<HomeWork>) hibernateTemplate.find("from HomeWork where teacherId = ?",teacherId);
    }

        public HomeWork getHomework(String id) {
            return hibernateTemplate.get(HomeWork.class,id);
        }

//**************************************************StudentHomework******************************************************//
    public StudentHomework getStudentHomework(String id) {
        return hibernateTemplate.get(StudentHomework.class,id);
    }

    public List<StudentHomework> queryStudentHomeworkById(String homeworkId) {
        return (List<StudentHomework>)hibernateTemplate.find("from StudentHomework where homeworkId = ?",homeworkId);
    }

    public List<StudentHomework> queryStudentHomework(String studentId) {
        return (List<StudentHomework>)hibernateTemplate.find("from StudentHomework where studentId = ?",studentId);
    }

    public void updateStudentHomework(StudentHomework studentHomework) {
        hibernateTemplate.save(studentHomework);
    }

    public void saveStudentHomework(StudentHomework studentHomework) {
        hibernateTemplate.save(studentHomework);
    }
}
