package com.webapp.model.model;

import com.webapp.dao.SystemDao;
import com.webapp.model.table.HomeWork;
import com.webapp.model.table.Question;
import com.webapp.model.table.StudentHomework;
import com.webapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by aass on 2016/4/7.
 */
public class TeacherHomeworkModel {
    public static int STATE_UNFINISHED = 0;
    public static int STATE_FINISHED = 1;
    //作业ID
    private String homeworkId;
    //作业名称
    private String homeworkName;
    //老师ID
    private String teacherId;
    //学生列表
    private List<String> studentList;
    //作业状态
    private int state;
    //问题列表
    private List<QuestionModel> questionList;
    //知识点列表
    private List<String> testSitesList;
    //创建时间
    private Date newTime;

    private List<StudentHomeworkModel> studentHomeworkModels = new ArrayList<StudentHomeworkModel>();

    public TeacherHomeworkModel() {
    }
    public TeacherHomeworkModel(HomeWork homeWork, SystemDao dao){
        System.out.println("teacherHomework");
        homeworkId = homeWork.getId();
        homeworkName = homeWork.getName();
        teacherId = homeWork.getTeacherId();
        if(homeWork.getStudentIds() == null){
            studentList = new ArrayList<String>();
        }else{
            studentList = JsonUtils.readValue(homeWork.getStudentIds(),List.class);
        }
        state = homeWork.getState();
        questionList = new ArrayList<QuestionModel>();
        testSitesList = new ArrayList<String>();
        System.out.println("teacherHomework id" );
        List<String> ids = JsonUtils.readValue(homeWork.getQuestionIds(),List.class);
        for(String id:ids){
            Question question = dao.getQuestion(id);
            System.out.println("teacherHomework id"+id );
            for(String point:(List<String>)JsonUtils.readValue(question.getTestSites(),List.class)){
                if(!testSitesList.contains(point)){
                    testSitesList.add(point);
                }
            }
            QuestionModel questionModel = new QuestionModel(dao.getQuestion(id));
            questionList.add(questionModel);
        }
        for(String studentId:studentList){
            List<StudentHomework> list = dao.queryStudentHomework(studentId);
            StudentHomework studentHomework = null;
            for(StudentHomework studentHomework1:list){
                if(studentHomework1.getHomeworkId().equals(homeWork.getId())){
                    studentHomework = studentHomework1;
                }
            }
            if(studentHomework != null){
                if(studentHomework.getState() == ModelConfig.STATE_FINISH){
                    studentHomeworkModels.add(new StudentHomeworkModel(homeWork,studentHomework,dao));
                }
            }
        }
        System.out.println("teacherHomework finish");

        newTime = homeWork.getNewDate();
    }

    public static int getStateUnfinished() {
        return STATE_UNFINISHED;
    }

    public static void setStateUnfinished(int stateUnfinished) {
        STATE_UNFINISHED = stateUnfinished;
    }

    public static int getStateFinished() {
        return STATE_FINISHED;
    }

    public static void setStateFinished(int stateFinished) {
        STATE_FINISHED = stateFinished;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkame) {
        this.homeworkName = homeworkName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<QuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    public List<String> getTestSitesList() {
        return testSitesList;
    }

    public void setTestSitesList(List<String> testSitesList) {
        this.testSitesList = testSitesList;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public List<StudentHomeworkModel> getStudentHomeworkModels() {
        return studentHomeworkModels;
    }

    public void setStudentHomeworkModels(List<StudentHomeworkModel> studentHomeworkModels) {
        this.studentHomeworkModels = studentHomeworkModels;
    }
}
