package com.webapp.model.model;

import com.webapp.dao.SystemDao;
import com.webapp.model.table.CollectQuestion;
import com.webapp.model.table.HomeWork;
import com.webapp.model.table.Question;
import com.webapp.model.table.StudentHomework;
import com.webapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yao_han on 2016/4/16.
 */
public class StudentHomeworkModel {
    //作业ID
    private String id;
    //作业名称
    private String name;
    //学生ID
    private String studentId;

    private String studentName;
    //作业状态
    private int state;
    //问题列表
    private List<QuestionModel> questionList;
    //答案列表
    private List<String> answerList;
    //答案正误
    private List<Boolean> resultList;
    //评论列表
    private List<CommentInfo> commentList;
    //作业成绩
    private double grade;
    //知识点列表
    private List<String> testSitesList;
    //创建时间
    private Date newTime;

    public StudentHomeworkModel() {

    }
    public StudentHomework toStudentHomeWork(StudentHomework studentHomework){
        studentHomework.setState(getState());
        studentHomework.setAnswers(JsonUtils.toJson(getAnswerList()));
        studentHomework.setGrade(getGrade());
        studentHomework.setResults(JsonUtils.toJson(getResultList()));
        System.out.print("comment:"+JsonUtils.toJson(getCommentList()));
        studentHomework.setComments(JsonUtils.toJson(getCommentList()));
        return studentHomework;
    }
    public StudentHomeworkModel(HomeWork homeWork, StudentHomework studentHomework, SystemDao dao){
        id = studentHomework.getId();
        name = homeWork.getName();
        studentId = studentHomework.getStudentId();
        studentName = dao.getAccount(studentId).getRealName();
        state = studentHomework.getState();
        answerList = JsonUtils.readValue(studentHomework.getAnswers(), List.class);
        resultList = JsonUtils.readValue(studentHomework.getResults(), List.class);
        commentList = JsonUtils.readValue(studentHomework.getComments(), List.class);

        questionList = new ArrayList<QuestionModel>();
        testSitesList = new ArrayList<String>();

        List<CollectQuestion> collectQuestionList = dao.queryCollectQuestion(studentId);
        List<String> collectionId = new ArrayList<String>();
        for(CollectQuestion collectQuestion:collectQuestionList){
            collectionId.add(collectQuestion.getqId());
        }
        List<String> questionIds = JsonUtils.readValue(homeWork.getQuestionIds(),List.class);
        for(int i=0;i<questionIds.size();i++){
            String questionId = questionIds.get(i);
            Question question = dao.getQuestion(questionId);
            QuestionModel questionModel = new QuestionModel(question,answerList.get(i),collectionId.contains(questionId));
            for(String point:(List<String>)JsonUtils.readValue(question.getTestSites(),List.class)){
                if(!testSitesList.contains(point)){
                    testSitesList.add(point);
                }
            }
            questionList.add(questionModel);
        }
        grade = studentHomework.getGrade();
        newTime = homeWork.getNewDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public List<Boolean> getResultList() {
        return resultList;
    }

    public void setResultList(List<Boolean> resultList) {
        this.resultList = resultList;
    }

    public List<CommentInfo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentInfo> commentList) {
        this.commentList = commentList;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
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


}
