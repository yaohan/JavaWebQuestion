package com.webapp.dao;

import com.webapp.model.table.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yao_han on 2016/4/5.
 */
public interface SystemDao {
    List<Account> queryAccount(String phoneNumber, String password);
    List<Account> queryAccount(int role, String name);
    List<Account> queryAccount(int role, String schoolId, String classId);
    List<Account> queryAccount(String phoneNumber);
    void updateAccount(Account accout);
    void saveAccount(Account account);
    Account getAccount(String id);

    School getSchool(String id);
    List<School> querySchool(String province, String city, String area, String school);
    List<School> querySchool();
    void saveSchool(School school);

    Clazz getClazz(String id);
    List<Clazz> queryClazz(String schoolId);
    List<Clazz> queryClazz(String schoolId, String grade, String clazz);
    void saveClazz(Clazz clazz);
    void updateClazz(Clazz clazz);

    List<Question> queryQuestion();
    Question getQuestion(String id);

    void saveSelfTest(SelfTest selfTest);
    List<SelfTest> querySelfTest(String studentId);
    SelfTest getSelfTest(String id);
    void updateSelfTest(SelfTest selfTest);

    List<ErrorQuestion> queryErrorQuestion(String studentId);
    List<ErrorQuestion> queryErrorQuestion(String studentId, String questionId);
    void saveErrorQuestion(ErrorQuestion errorQuestion);
    void updateErrorQuestion(ErrorQuestion errorQuestion);

    List<CollectQuestion> queryCollectQuestion(String studentId);
    List<CollectQuestion> queryCollectQuestion(String studentId,String quesitonId);
    void saveCollectQuestion(CollectQuestion collectQuestion);
    void deleteCollectQuestion(CollectQuestion collectQuestion);

    void saveHomeWork(HomeWork homeWork);
    List<HomeWork> queryHomework(String teacherId);
    HomeWork getHomework(String id);

    StudentHomework getStudentHomework(String id);
    List<StudentHomework> queryStudentHomeworkById(String homeworkId);
    List<StudentHomework> queryStudentHomework(String studentId);
    void updateStudentHomework(StudentHomework studentHomework);
    void saveStudentHomework(StudentHomework studentHomework);

}
