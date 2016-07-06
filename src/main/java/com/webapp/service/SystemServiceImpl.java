package com.webapp.service;

import com.webapp.dao.SystemDao;
import com.webapp.model.Req.*;
import com.webapp.model.Resp.*;
import com.webapp.model.model.*;
import com.webapp.model.table.*;
import com.webapp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by zjm on 15-12-7.
 */
@Transactional
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemDao dao;

    public LoginResp login(LoginReq loginReq) {
        LoginResp loginResp = new LoginResp();
        List<Account> list = dao.queryAccount(loginReq.getPhoneNumber(), loginReq.getPassword());
        if (list.size() == 0) {
            loginResp.setType(LoginResp.ROLE_NULL);
            loginResp.setDesc(LoginResp.DESC_ERROR);
            loginResp.setResult(LoginResp.RESULT_FAILED);
        } else {
            Account account = list.get(0);
            if (account.getRole() == null) {
                loginResp.setType(LoginResp.ROLE_NULL);
            } else {
                loginResp.setType(account.getRole());
            }
            loginResp.setDesc(LoginResp.DESC_SUCCESS);
            loginResp.setResult(LoginResp.RESULT_SUCCESS);
            loginResp.setId(account.getId());
        }
        return loginResp;
    }

    public ForgetPasswordResp forgetPassword(ForgetPasswordReq forgetPasswordReq) {
        //TODO CheckCode
        ForgetPasswordResp forgetPasswordResp = new ForgetPasswordResp();
        List<Account> list = dao.queryAccount(forgetPasswordReq.getPhoneNumber());
        if (list.size() == 0) {
            forgetPasswordResp.setResult(ForgetPasswordResp.RESULT_FAILED);
            forgetPasswordResp.setDesc(ForgetPasswordResp.DESC_NOT_FOUND);
        } else {
            Account account = list.get(0);
            account.setPassword(forgetPasswordReq.getPassword());
            dao.updateAccount(account);

            forgetPasswordResp.setResult(ForgetPasswordResp.RESULT_SUCCESS);
            forgetPasswordResp.setDesc(ForgetPasswordResp.DESC_SUCCESS);
        }
        return forgetPasswordResp;

    }

    public RegisterResp register(RegisterReq registerReq) {
        //TODO CheckCode
        RegisterResp registerResp = new RegisterResp();
        List<Account> list = dao.queryAccount(registerReq.getPhoneNumber());
        if (list.size() == 0) {//未注册用户
            Account account = new Account();
            account.setPhoneNumber(registerReq.getPhoneNumber());
            account.setPassword(registerReq.getPassword());
            dao.saveAccount(account);
            registerResp.setResult(RegisterResp.RESULT_SUCCESS);
            registerResp.setDesc(RegisterResp.DESC_SUCCESS);
            registerResp.setPsId(account.getId());
        } else {//手机号已存在
            registerResp.setResult(RegisterResp.RESULT_FAILED);
            registerResp.setDesc(RegisterResp.DESC_HAS_REGISTER);
        }
        return registerResp;
    }

    public FinishInfoResp finishInfo(FinishInfoReq finishInfoReq) {
        UserModel userModel = finishInfoReq.getUserModel();
        int role = userModel.getRole();
        String id = userModel.getId();
        FinishInfoResp finishInfoResp = new FinishInfoResp();
        System.out.println("id:" + id);
        Account account = dao.getAccount(id);
        System.out.println("account = null?:" + account);
        if (account == null) {//用户不存在，首次注册
            finishInfoResp.setResult(FinishInfoResp.RESULT_FAILED);
            finishInfoResp.setDesc(ForgetPasswordResp.DESC_NOT_FOUND);
            return finishInfoResp;
        } else {//用户存在，完善信息
            account.fillByUserModel(finishInfoReq.getUserModel());
            dao.saveAccount(account);
            finishInfoResp.setResult(FinishInfoResp.RESULT_SUCCESS);
            finishInfoResp.setDesc(FinishInfoResp.DESC_SUCCESS);
            if (role == ModelConfig.ROLE_PUPILS) {
                Clazz clazz = dao.getClazz(finishInfoReq.getUserModel().getClassId());
                List<String> students;
                if (clazz.getStudentIds() == null) {
                    students = new ArrayList<String>();
                } else {
                    students = JsonUtils.readValue(clazz.getStudentIds(), List.class);
                }
                if (!students.contains(id)) {
                    students.add(id);
                    clazz.setStudentIds(JsonUtils.toJson(students));
                    dao.updateClazz(clazz);
                }
                if (userModel.getParentId() != null && userModel.getParentId().length() > 0) {
                    Account account1 = dao.getAccount(userModel.getParentId());
                    if (account1.getChildId() == null || account1.getChildId().length() == 0) {
                        account1.setChildId(id);
                        dao.saveAccount(account1);
                    }
                }
            } else if (role == ModelConfig.ROLE_TEACHER) {
                Clazz clazz = dao.getClazz(finishInfoReq.getUserModel().getClassId());
                clazz.setTeacherId(finishInfoReq.getUserModel().getId());
                dao.updateClazz(clazz);
            } else if (role == ModelConfig.ROLE_PARENT) {
                Account account1 = dao.getAccount(userModel.getChildId());
                if (account1.getParentId() == null || account1.getParentId().length() == 0) {
                    account1.setParentId(id);
                }
            }
        }
        return finishInfoResp;
    }


    public GetInfoResp getInfo(GetInfoReq getInfoReq) {
        GetInfoResp getInfoResp = new GetInfoResp();
        Account account = dao.getAccount(getInfoReq.getId());
        if (account != null) {
            getInfoResp.setResult(GetInfoResp.RESULT_SUCCESS);
            getInfoResp.setDesc(GetInfoResp.DESC_SUCCESS);
            getInfoResp.setUserModel(account.toUserModel(dao));
        } else {
            getInfoResp.setResult(GetInfoResp.RESULT_FAILED);
            getInfoResp.setDesc(GetInfoResp.DESC_FAILED);
        }
        return getInfoResp;
    }


    public GetSchoolResp getSchoolList(GetSchoolReq getSchoolReq) {
        GetSchoolResp getSchoolResp = new GetSchoolResp();
        List<School> list = dao.querySchool();
        getSchoolResp.setResult(GetSchoolResp.RESULT_SUCCESS);
        getSchoolResp.setDesc(GetSchoolResp.DESC_SUCCESS);
        getSchoolResp.setSchoolList(list);

        return getSchoolResp;
    }

    public AddSchoolResp addSchool(AddSchoolReq addSchoolReq) {
        AddSchoolResp addSchoolResp = new AddSchoolResp();
        List<School> list = dao.querySchool(addSchoolReq.getProvince(), addSchoolReq.getCity(), addSchoolReq.getArea(), addSchoolReq.getSchool());
        if (list.size() == 0) {
            School school = new School(addSchoolReq.getProvince(), addSchoolReq.getCity(),
                    addSchoolReq.getArea(), addSchoolReq.getSchool());
            dao.saveSchool(school);
            addSchoolResp.setResult(AddSchoolResp.RESULT_SUCCESS);
            addSchoolResp.setDesc(AddSchoolResp.DESC_SUCCESS);
        } else {
            addSchoolResp.setResult(AddSchoolResp.RESULT_FAILED);
            addSchoolResp.setDesc(AddSchoolResp.DESC_HAS_EXIST);
        }
        return addSchoolResp;
    }

    public GetClazzResp getClazzList(GetClazzReq getClazzReq) {
        GetClazzResp getClazzResp = new GetClazzResp();
        List<Clazz> list = dao.queryClazz(getClazzReq.getSchoolId());

        getClazzResp.setResult(GetClazzResp.RESULT_SUCCESS);
        getClazzResp.setDesc(GetClazzResp.DESC_SUCCESS);
        getClazzResp.setClassList(list);
        return getClazzResp;
    }

    public AddClazzResp addClazz(AddClazzReq addClazzReq) {
        AddClazzResp addClazzResp = new AddClazzResp();
        List<Clazz> list = dao.queryClazz(addClazzReq.getSchoolId(), addClazzReq.getGrade(), addClazzReq.getClazz());
        if (list.size() == 0) {
            Clazz clazz = new Clazz(addClazzReq.getSchoolId(), addClazzReq.getGrade(), addClazzReq.getClazz());
            dao.saveClazz(clazz);
            addClazzResp.setResult(addClazzResp.RESULT_SUCCESS);
            addClazzResp.setDesc(addClazzResp.DESC_SUCCESS);
        } else {
            addClazzResp.setResult(addClazzResp.RESULT_FAILED);
            addClazzResp.setDesc(addClazzResp.DESC_HAS_EXIST);
        }
        return addClazzResp;
    }

    public GetTestSitesResp getTestSites(GetTestSitesReq getTestSitesReq) {
        GetTestSitesResp getTestSitesResp = new GetTestSitesResp();
        List<Question> list = dao.queryQuestion();
        List<String> reqList = new ArrayList<String>();
        String strTest;
        for (Question l : list) {
            strTest = l.getTestSites();
            String[] sites = JsonUtils.readValue(strTest, String[].class);
            for (String site : sites) {
                if (!reqList.contains(site)) {
                    reqList.add(site);
                }
            }
        }
        getTestSitesResp.setTestSites(reqList);
        getTestSitesResp.setResult(GetTestSitesResp.RESULT_SUCCESS);
        getTestSitesResp.setDesc(GetTestSitesResp.DESC_SUCCESS);
        return getTestSitesResp;
    }

    private List<Question> getNoRepeatQuestion(int n, int target, List<Question> questionsList) {
        List<Question> list = new ArrayList<Question>();
        int[] seed = new int[n];
        for (int i = 0; i < n; i++) {
            seed[i] = i;
        }
        Random ran = new Random();
        for (int i = 0; i < target; i++) {
            int j = ran.nextInt(n - i);
            Question question = questionsList.get(seed[j]);
            seed[j] = seed[n - 1 - i];
            list.add(question);
        }
        return list;
    }

    public GetNewTestResp getNewTest(GetNewTestReq getNewTestReq) {
        GetNewTestResp getNewTestResp = new GetNewTestResp();
        List<Question> list = new ArrayList<Question>();
        if (getNewTestReq.getSelect() == GetNewTestReq.SELECT_RANDOM) {
            list = dao.queryQuestion();
        } else if (getNewTestReq.getSelect() == GetNewTestReq.SELECT_BY_ERROR) {
            //TODO by error
            list = dao.queryQuestion();
        } else {
            List<Question> allList = dao.queryQuestion();
            List<String> testSitesList = getNewTestReq.getTestSites();
            boolean breakFlag = false;
            for (Question question : allList) {
                for (String testSites : testSitesList) {
                    String[] sites = JsonUtils.readValue(question.getTestSites(), String[].class);
                    for (String site : sites) {
                        if (testSites.equals(site)) {
                            list.add(question);
                            breakFlag = true;
                            break;
                        }
                    }
                    if (breakFlag) {
                        breakFlag = false;
                        break;
                    }
                }
            }
        }
        if (getNewTestReq.getNumber() > list.size()) {
            getNewTestResp.setResult(GetNewTestResp.RESULT_FAILED);
            getNewTestResp.setDesc(GetNewTestResp.DESC_NOT_ENOUGH);
            getNewTestResp.setSelfTestModel(new SelfTestModel());
            return getNewTestResp;
        } else {
            List<Question> questionList = getNoRepeatQuestion(list.size(), getNewTestReq.getNumber(), list);
            SelfTest selfTest = new SelfTest(getNewTestReq.getPsId(), 0, getNewTestReq.getName(), ModelConfig.STATE_NOT_FINISH, questionList, getNewTestReq.getNewTime());
            dao.saveSelfTest(selfTest);
            getNewTestResp.setResult(GetNewTestResp.RESULT_SUCCESS);
            getNewTestResp.setDesc(GetNewTestResp.DESC_SUCCESS);
            getNewTestResp.setSelfTestModel(new SelfTestModel(selfTest, questionList, getCollectQIdList(getNewTestReq.getPsId())));
        }
        return getNewTestResp;
    }

    public GetMyTestResp getMyTest(GetMyTestReq getMyTestReq) {

        GetMyTestResp getMyTestResp = new GetMyTestResp();
        List<SelfTest> list = dao.querySelfTest(getMyTestReq.getPsId());
        List<TestHistory> testInfoList = new ArrayList<TestHistory>();
        for (int i = 0; i < list.size(); i++) {
            SelfTest selfTest = list.get(i);
            TestHistory testHistory = new TestHistory();
            testHistory.setId(selfTest.getId());
            testHistory.setName(selfTest.getName());
            testHistory.setState(selfTest.getState());
            testHistory.setNewTime(selfTest.getNewTime());
            testInfoList.add(testHistory);
        }
        getMyTestResp.setTestInfoList(testInfoList);
        getMyTestResp.setResult(GetMyTestResp.RESULT_SUCCESS);
        getMyTestResp.setDesc(GetMyTestResp.DESC_SUCCESS);
        return getMyTestResp;
    }


    public GetSelfTestByIdResp getSelfTestById(GetSelfTestByIdReq getSelfTestByIdReq) {
        GetSelfTestByIdResp getSelfTTestByIdResp = new GetSelfTestByIdResp();
        SelfTest selfTest = dao.getSelfTest(getSelfTestByIdReq.getId());
        String[] idList = JsonUtils.readValue(selfTest.getqId(), String[].class);
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        for (int j = 0; j < idList.length; j++) {
            question = dao.getQuestion(idList[j]);
            questionList.add(question);
        }
        SelfTestModel selfTestModel = new SelfTestModel(selfTest, questionList, getCollectQIdList(getSelfTestByIdReq.getId()));

        getSelfTTestByIdResp.setSelfTestModel(selfTestModel);
        getSelfTTestByIdResp.setResult(GetSelfTestByIdResp.RESULT_SUCCESS);
        getSelfTTestByIdResp.setDesc(GetSelfTestByIdResp.DESC_SUCCESS);
        return getSelfTTestByIdResp;
    }

    public GetErrorsResp getErrors(GetErrorsReq getErrorsReq) {
        GetErrorsResp getErrorsResp = new GetErrorsResp();
        List<String> pointList = new ArrayList<String>();
        Map<String, List<ErrorQuestionModel>> questionList = new HashMap<String, List<ErrorQuestionModel>>();
        List<ErrorQuestion> list = dao.queryErrorQuestion(getErrorsReq.getId());
        List<String> qIdList = getCollectQIdList(getErrorsReq.getId());
        if (list.size() != 0) {
            for (ErrorQuestion errorQuestion : list) {
                Question question = dao.getQuestion(errorQuestion.getqId());
                if (question == null) {
                    System.out.println(question.getqId() + "question 不存在");
                } else {
                    String[] tests = JsonUtils.readValue(question.getTestSites(), String[].class);
                    for (String test : tests) {
                        if (!pointList.contains(test)) {
                            pointList.add(test);
                            questionList.put(test, new ArrayList<ErrorQuestionModel>());
                        }
                        questionList.get(test).add(new ErrorQuestionModel(new QuestionModel(question, errorQuestion.getMyAnswer(), qIdList.contains(question.getqId())), errorQuestion.getErrorTime()));
                    }
                }
            }
        } else {
            System.out.println("list is 0 Id = " + getErrorsReq.getId());

        }

        getErrorsResp.setDesc(GetErrorsResp.DESC_SUCCESS);
        getErrorsResp.setResult(GetErrorsResp.RESULT_SUCCESS);
        getErrorsResp.setPointList(pointList);
        getErrorsResp.setQuestionList(questionList);
        return getErrorsResp;
    }

    public GetCollectionsResp getCollections(GetCollectionsReq getCollectionsReq) {
        GetCollectionsResp getCollectionsResp = new GetCollectionsResp();
        List<String> pointList = new ArrayList<String>();
        Map<String, List<QuestionModel>> questionList = new HashMap<String, List<QuestionModel>>();

        List<CollectQuestion> list = dao.queryCollectQuestion(getCollectionsReq.getId());
        if (list.size() != 0) {
            System.out.println(list);
            for (CollectQuestion collectQuestion : list) {
                Question question = dao.getQuestion(collectQuestion.getqId());
                if (question == null) {
                    System.out.println("qId = " + collectQuestion.getqId() + "  not found");
                } else {
                    String[] tests = JsonUtils.readValue(question.getTestSites(), String[].class);
                    for (String test : tests) {
                        if (!pointList.contains(test)) {
                            pointList.add(test);
                        }
                        if (!questionList.containsKey(test)) {
                            questionList.put(test, new ArrayList<QuestionModel>());
                        }
                        questionList.get(test).add(new QuestionModel(question, collectQuestion.getMyAnswer(), true));
                    }
                }
            }
        } else {
            System.out.println("list is 0 Id = " + getCollectionsReq.getId());

        }

        getCollectionsResp.setDesc(GetErrorsResp.DESC_SUCCESS);
        getCollectionsResp.setResult(GetErrorsResp.RESULT_SUCCESS);
        getCollectionsResp.setPointList(pointList);
        getCollectionsResp.setQuestionList(questionList);
        return getCollectionsResp;
    }

    public SearchPersonResp searchPerson(SearchPersonReq searchPersonReq) {
        System.out.println("service start");
        SearchPersonResp searchPersonResp = new SearchPersonResp();
        List<Account> accounts = dao.queryAccount(searchPersonReq.getRole(), searchPersonReq.getName());
        System.out.println("accounts" + JsonUtils.toJson(accounts));
        List<UserModel> userModelList = new ArrayList<UserModel>();
        for (Account account : accounts) {
            userModelList.add(account.toUserModel(dao));
        }
        System.out.println("userModel" + JsonUtils.toJson(userModelList));
        searchPersonResp.setResult(SearchPersonResp.RESULT_SUCCESS);
        searchPersonResp.setDesc(SearchPersonResp.DESC_SUCCESS);
        searchPersonResp.setList(userModelList);
        return searchPersonResp;
    }

    public GetClazzDetailResp getClazzDetail(GetClazzDetailReq getClazzDetailReq) {
        GetClazzDetailResp getClazzDetailResp = new GetClazzDetailResp();
        List<Account> students = dao.queryAccount(LoginResp.ROLE_PUPILS, getClazzDetailReq.getSchoolId(), getClazzDetailReq.getClassId());
        List<UserModel> studentList = new ArrayList<UserModel>();
        for (Account account : students) {
            studentList.add(account.toUserModel(dao));
        }
        List<Account> teachers = dao.queryAccount(LoginResp.ROLE_TEACHER, getClazzDetailReq.getSchoolId(), getClazzDetailReq.getClassId());
        List<UserModel> teacherList = new ArrayList<UserModel>();
        for (Account account : teachers) {
            teacherList.add(account.toUserModel(dao));
        }
        getClazzDetailResp.setResult(GetClazzDetailResp.RESULT_SUCCESS);
        getClazzDetailResp.setDesc(GetClazzDetailResp.DESC_SUCCESS);
        getClazzDetailResp.setStudents(studentList);
        getClazzDetailResp.setTeachers(teacherList);
        return getClazzDetailResp;
    }

    public PostHomeworkResp postHomework(PostHomeworkReq postHomeworkReq) {
        PostHomeworkResp postHomeworkResp = new PostHomeworkResp();
        List<Question> list = new ArrayList<Question>();
        if (postHomeworkReq.getPointType() == GetNewTestReq.SELECT_RANDOM) {
            list = dao.queryQuestion();
        } else if (postHomeworkReq.getPointType() == GetNewTestReq.SELECT_BY_ERROR) {
            //TODO by error
            list = dao.queryQuestion();
        } else {
            List<Question> allList = dao.queryQuestion();
            List<String> testSitesList = postHomeworkReq.getPointList();
            boolean breakFlag = false;
            for (Question question : allList) {
                for (String testSites : testSitesList) {
                    String[] sites = JsonUtils.readValue(question.getTestSites(), String[].class);
                    for (String site : sites) {
                        if (testSites.equals(site)) {
                            list.add(question);
                            breakFlag = true;
                            break;
                        }
                    }
                    if (breakFlag) {
                        breakFlag = false;
                        break;
                    }
                }
            }
        }
        System.out.println("posthomework get list");
        if (postHomeworkReq.getNumber() > list.size()) {
            postHomeworkResp.setResult(PostHomeworkResp.RESULT_FAILED);
            postHomeworkResp.setDesc(PostHomeworkResp.DESC_NOT_ENOUGH);
            postHomeworkResp.setHomeworkModel(new TeacherHomeworkModel());
            return postHomeworkResp;
        } else {
            List<Question> questionList = getNoRepeatQuestion(list.size(), postHomeworkReq.getNumber(), list);
            HomeWork homeWork = new HomeWork(postHomeworkReq.getId(), questionToString(questionList), postHomeworkReq.getHomeworkName(), new Date());
            dao.saveHomeWork(homeWork);
            System.out.println("save homework");
            postHomeworkResp.setResult(GetNewTestResp.RESULT_SUCCESS);
            postHomeworkResp.setDesc(GetNewTestResp.DESC_SUCCESS);
            postHomeworkResp.setHomeworkModel(new TeacherHomeworkModel(homeWork, dao));
        }

        return postHomeworkResp;
    }

    public GetTeacherHomeworkResp getTeacherHomework(GetTeacherHomeworkReq getTeacherHomeworkReq) {
        GetTeacherHomeworkResp getTeacherHomeworkResp = new GetTeacherHomeworkResp();
        List<GetTeacherHomeworkResp.TeacherHomework> list = new ArrayList<GetTeacherHomeworkResp.TeacherHomework>();
        List<HomeWork> homeWorkList = dao.queryHomework(getTeacherHomeworkReq.getId());
        for (HomeWork homeWork : homeWorkList) {
            GetTeacherHomeworkResp.TeacherHomework teacherHomework = getTeacherHomeworkResp.new TeacherHomework();
            teacherHomework.setId(homeWork.getId());
            teacherHomework.setName(homeWork.getName());
            teacherHomework.setNewDate(homeWork.getNewDate());
            teacherHomework.setState(homeWork.getState());
            int count = JsonUtils.readValue(homeWork.getStudentIds(), List.class).size();
            teacherHomework.setCounts(count);
            if (homeWork.getState() != ModelConfig.STATE_NOT_FINISH) {
                teacherHomework.setFinishes(count);
            } else {
                List<StudentHomework> studentHomeworkList = dao.queryStudentHomeworkById(homeWork.getId());
                int finishs = 0;
                for (StudentHomework studentHomework : studentHomeworkList) {
                    if (studentHomework.getState() == ModelConfig.STATE_FINISH) {
                        finishs++;
                    }
                }
                if (finishs == count) {
                    homeWork.setState(ModelConfig.STATE_FINISH);
                    dao.saveHomeWork(homeWork);
                }
                teacherHomework.setFinishes(finishs);
                teacherHomework.setState(ModelConfig.STATE_FINISH);
            }
            list.add(teacherHomework);
        }
        getTeacherHomeworkResp.setResult(GetTeacherHomeworkResp.RESULT_SUCCESS);
        getTeacherHomeworkResp.setDesc(GetClazzDetailResp.DESC_SUCCESS);
        getTeacherHomeworkResp.setList(list);
        return getTeacherHomeworkResp;
    }

    public GetHomeworkByIdResp getHomeworkById(GetHomeworkByIdReq getHomeworkByIdReq) {
        GetHomeworkByIdResp getHomeworkByIdResp = new GetHomeworkByIdResp();
        HomeWork homeWork = dao.getHomework(getHomeworkByIdReq.getId());
        if (homeWork == null) {
            getHomeworkByIdResp.setResult(GetHomeworkByIdResp.RESULT_FAILED);
            getHomeworkByIdResp.setDesc(GetHomeworkByIdResp.DESC_NOT_FOUND);
        } else {
            TeacherHomeworkModel teacherHomeworkModel = new TeacherHomeworkModel(homeWork, dao);
            getHomeworkByIdResp.setResult(GetHomeworkByIdResp.RESULT_SUCCESS);
            getHomeworkByIdResp.setDesc(GetHomeworkByIdResp.DESC_SUCCESS);
            getHomeworkByIdResp.setTeacherHomeworkModel(teacherHomeworkModel);
        }
        return getHomeworkByIdResp;
    }

    public SubmitStudentResp submitStudent(SubmitStudentReq submitStudentReq) {
        SubmitStudentResp submitStudentResp = new SubmitStudentResp();
        HomeWork homeWork = dao.getHomework(submitStudentReq.getId());
        if (homeWork == null) {
            submitStudentResp.setResult(SubmitStudentResp.RESULT_FAILED);
            submitStudentResp.setDesc(SubmitStudentResp.DESC_NOT_FOUND);
        } else {
            homeWork.setStudentIds(JsonUtils.toJson(submitStudentReq.getStudentIds()));
            homeWork.setState(ModelConfig.STATE_NOT_FINISH);
            dao.saveHomeWork(homeWork);
            for (String id : submitStudentReq.getStudentIds()) {
                StudentHomework studentHomework = new StudentHomework(id, submitStudentReq.getId(), JsonUtils.readValue(homeWork.getQuestionIds(), List.class).size());
                dao.saveStudentHomework(studentHomework);
            }

            submitStudentResp.setResult(SubmitStudentResp.RESULT_SUCCESS);
            submitStudentResp.setDesc(SubmitStudentResp.DESC_SUCCESS);
        }
        return submitStudentResp;
    }

    public GetStudentHomeworkResp getStudentHomework(GetStudentHomeworkReq getStudentHomeworkReq) {
        GetStudentHomeworkResp getStudentHomeworkResp = new GetStudentHomeworkResp();
        List<StudentHomework> studentHomeworkList = dao.queryStudentHomework(getStudentHomeworkReq.getId());
        System.out.println("studentHomeworkList:" + studentHomeworkList.size());
        List<TestHistory> testHistoryList = new ArrayList<TestHistory>();
        for (StudentHomework studentHomework : studentHomeworkList) {
            HomeWork homeWork = dao.getHomework(studentHomework.getHomeworkId());
            TestHistory testHistory = new TestHistory();
            testHistory.setId(studentHomework.getId());
            testHistory.setName(homeWork.getName());
            testHistory.setNewTime(homeWork.getNewDate());
            testHistory.setState(studentHomework.getState());
            testHistoryList.add(testHistory);
        }
        getStudentHomeworkResp.setResult(GetStudentHomeworkResp.RESULT_SUCCESS);
        getStudentHomeworkResp.setDesc(GetStudentHomeworkResp.DESC_SUCCESS);
        getStudentHomeworkResp.setHomeworkList(testHistoryList);
        return getStudentHomeworkResp;
    }

    public GetStudentHomeworkByIdResp getStudentHomeworkById(GetStudentHomeworkByIdReq getStudentHomeworkByIdReq) {
        GetStudentHomeworkByIdResp getStudentHomeworkByIdResp = new GetStudentHomeworkByIdResp();
        StudentHomework studentHomework = dao.getStudentHomework(getStudentHomeworkByIdReq.getId());
        HomeWork homeWork = dao.getHomework(studentHomework.getHomeworkId());
        getStudentHomeworkByIdResp.setResult(GetStudentHomeworkByIdResp.RESULT_SUCCESS);
        getStudentHomeworkByIdResp.setDesc(GetStudentHomeworkByIdResp.DESC_SUCCESS);
        getStudentHomeworkByIdResp.setStudentHomeworkModel(new StudentHomeworkModel(homeWork, studentHomework, dao));
        return getStudentHomeworkByIdResp;
    }

    public SubmitStudentHomeworkResp submitStudentHomework(SubmitStudentHomeworkReq submitStudentHomeworkReq) {
        SubmitStudentHomeworkResp submitStudentHomeworkResp = new SubmitStudentHomeworkResp();
        StudentHomework studentHomework = dao.getStudentHomework(submitStudentHomeworkReq.getStudentHomeworkModel().getId());
        if (studentHomework == null) {
            submitStudentHomeworkResp.setResult(SubmitStudentHomeworkResp.RESULT_FAILED);
            submitStudentHomeworkResp.setDesc(SubmitStudentHomeworkResp.DESC_NOT_FOUND);
        } else {
            studentHomework = submitStudentHomeworkReq.getStudentHomeworkModel().toStudentHomeWork(studentHomework);
            dao.updateStudentHomework(studentHomework);
            submitStudentHomeworkResp.setResult(SubmitStudentHomeworkResp.RESULT_SUCCESS);
            submitStudentHomeworkResp.setDesc(SubmitStudentHomeworkResp.DESC_SUCCESS);
        }
        return submitStudentHomeworkResp;
    }

    public GetGroupsResp getGroups(GetGroupsReq getGroupsReq) {
        return null;
    }

    public AddGroupsResp addGroups(AddGroupsReq addGroupsReq) {
        return null;
    }

    public GetStudentAnalysisResp getStudentAnalysis(GetStudentAnalysisReq getStudentAnalysisReq) {
        GetStudentAnalysisResp getStudentAnalysisResp = new GetStudentAnalysisResp();
        String userId = getStudentAnalysisReq.getUserId();
        List<AnalysisInfo> analysisInfos = new ArrayList<AnalysisInfo>();
        for (SelfTest selfTest : dao.querySelfTest(userId)) {
            List<String> questions = JsonUtils.readValue(selfTest.getqId(), List.class);
            List<Boolean> results = JsonUtils.readValue(selfTest.getResult(), List.class);
            List<String> answers = JsonUtils.readValue(selfTest.getAnswers(), List.class);
            for (int i = 0; i < questions.size(); i++) {
                Question question = dao.getQuestion(questions.get(i));
                List<String> testSites = JsonUtils.readValue(question.getTestSites(), List.class);
                for (String testSite : testSites) {
                    AnalysisInfo analysisInfo = contains(analysisInfos, testSite);
                    if (analysisInfo == null) {
                        analysisInfo = new AnalysisInfo();
                        analysisInfo.setTestSite(testSite);
                        analysisInfos.add(analysisInfo);
                    }
                    analysisInfo.addQuestion(new QuestionModel(question, answers.get(i), results.get(i)));
                    if (results.get(i)) {
                        analysisInfo.addRight();
                    } else {
                        analysisInfo.addWrong();
                    }
                }
            }

        }
        List<StudentHomework> homeWorks = dao.queryStudentHomework(userId);
        for (StudentHomework studentHomework : dao.queryStudentHomework(userId)) {
            if (studentHomework.getState() == ModelConfig.STATE_FINISH) {
                List<String> questions = JsonUtils.readValue(dao.getHomework(studentHomework.getHomeworkId()).getQuestionIds(), List.class);
                List<Boolean> results = JsonUtils.readValue(studentHomework.getResults(), List.class);
                List<String> answers = JsonUtils.readValue(studentHomework.getAnswers(), List.class);
                for (int i = 0; i < questions.size(); i++) {
                    Question question = dao.getQuestion(questions.get(i));
                    List<String> testSites = JsonUtils.readValue(question.getTestSites(), List.class);
                    for (String testSite : testSites) {
                        AnalysisInfo analysisInfo = contains(analysisInfos, testSite);
                        if (analysisInfo == null) {
                            analysisInfo = new AnalysisInfo();
                            analysisInfo.setTestSite(testSite);
                            analysisInfos.add(analysisInfo);
                        }
                        analysisInfo.addQuestion(new QuestionModel(question, answers.get(i), results.get(i)));
                        if (results.get(i)) {
                            analysisInfo.addRight();
                        } else {
                            analysisInfo.addWrong();
                        }
                    }
                }
            }
        }
        getStudentAnalysisResp.setAnalysisInfos(analysisInfos);
        getStudentAnalysisResp.setResult(GetStudentAnalysisResp.RESULT_SUCCESS);
        getStudentAnalysisResp.setDesc(GetStudentHomeworkByIdResp.DESC_SUCCESS);
        return getStudentAnalysisResp;
    }

    public GetHeadResp getHead(GetHeadReq getHeadReq) {
        GetHeadResp getHeadResp = new GetHeadResp();
        getHeadResp.setHead(dao.getAccount(getHeadReq.getUserId()).getHeadPortrait());
        getHeadResp.setResult(GetHeadResp.RESULT_SUCCESS);
        getHeadResp.setDesc(GetHeadResp.DESC_SUCCESS);
        return getHeadResp;
    }

    public GetTeacherAnalysisResp getTeacherAnalysis(GetTeacherAnalysisReq getTeacherAnalysisReq) {
        GetTeacherAnalysisResp getTeacherAnalysisResp = new GetTeacherAnalysisResp();
        List<HomeWork> homeWorks = dao.queryHomework(getTeacherAnalysisReq.getTeacherId());
        List<AnalysisInfo> analysisInfos = new ArrayList<AnalysisInfo>();
        for (HomeWork homeWork : homeWorks) {
            List<String> studentIds = JsonUtils.readValue(homeWork.getStudentIds(), List.class);
            for (String id : studentIds) {
                for (StudentHomework studentHomework : dao.queryStudentHomework(id)) {
                    if (studentHomework.getState() == ModelConfig.STATE_FINISH) {
                        List<String> questions = JsonUtils.readValue(dao.getHomework(studentHomework.getHomeworkId()).getQuestionIds(), List.class);
                        List<Boolean> results = JsonUtils.readValue(studentHomework.getResults(), List.class);
                        List<String> answers = JsonUtils.readValue(studentHomework.getAnswers(), List.class);
                        for (int i = 0; i < questions.size(); i++) {
                            Question question = dao.getQuestion(questions.get(i));
                            List<String> testSites = JsonUtils.readValue(question.getTestSites(), List.class);
                            for (String testSite : testSites) {
                                AnalysisInfo analysisInfo = contains(analysisInfos, testSite);
                                if (analysisInfo == null) {
                                    analysisInfo = new AnalysisInfo();
                                    analysisInfo.setTestSite(testSite);
                                    analysisInfos.add(analysisInfo);
                                }
                                analysisInfo.addQuestion(new QuestionModel(question, answers.get(i), results.get(i)));
                                if (results.get(i)) {
                                    analysisInfo.addRight();
                                } else {
                                    analysisInfo.addWrong();
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private AnalysisInfo contains(List<AnalysisInfo> analysisInfos, String testSite) {
        for (AnalysisInfo analysisInfo : analysisInfos) {
            if (analysisInfo.getTestSite().equals(testSite)) {
                return analysisInfo;
            }
        }
        return null;
    }


    private String questionToString(List<Question> questionList) {
        List<String> questionIds = new ArrayList<String>();
        for (Question question : questionList) {
            questionIds.add(question.getqId());
        }
        return JsonUtils.toJson(questionIds);
    }

    public SubmitTestResp submitTest(SubmitTestReq submitTestReq) {
        SubmitTestResp submitTestResp = new SubmitTestResp();
        SelfTest selfTest = dao.getSelfTest(submitTestReq.getSelfTestModel().getId());
        if (selfTest == null) {
            submitTestResp.setResult(SubmitTestResp.RESULT_FAILED);
            submitTestResp.setDesc(SubmitTestResp.DESC_NOT_FOUND);
        } else {
            selfTest = submitTestReq.getSelfTestModel().toSelfTest(selfTest);
            dao.updateSelfTest(selfTest);
            submitTestResp.setResult(SubmitTestResp.RESULT_SUCCESS);
            submitTestResp.setDesc(SubmitTestResp.DESC_SUCCESS);
            if (selfTest.getState() == ModelConfig.STATE_FINISH) {
                saveErrorQuestion(selfTest, submitTestReq);
            } else {
                System.out.println("未完成");
            }
        }
        return submitTestResp;
    }

    private void saveErrorQuestion(SelfTest selfTest, SubmitTestReq submitTestReq) {
        List<Boolean> resultList = submitTestReq.getSelfTestModel().getResultList();
        List<String> answerList = submitTestReq.getSelfTestModel().getAnswerList();
        String[] idList = JsonUtils.readValue(selfTest.getqId(), String[].class);
        for (int i = 0; i < resultList.size(); i++) {
            if (!resultList.get(i)) {
                List<ErrorQuestion> errorList = dao.queryErrorQuestion(selfTest.getPsId(), idList[i]);
                if (errorList.size() == 0) {
                    ErrorQuestion errorQuestion = new ErrorQuestion(selfTest.getPsId(), idList[i], 1, answerList.get(i));
                    dao.saveErrorQuestion(errorQuestion);
                    System.out.println("添加新的错题" + errorQuestion.getId());
                } else {
                    ErrorQuestion errorQuestion = errorList.get(0);
                    errorQuestion.setErrorTime(errorQuestion.getErrorTime() + 1);
                    errorQuestion.setMyAnswer(errorQuestion.getMyAnswer() + "," + answerList.get(i));
                    dao.updateErrorQuestion(errorQuestion);
                    System.out.println("错题次数+1" + errorQuestion.getId());
                }
            } else {
                System.out.println("正确");
            }
        }
    }

    public CollectionResp collection(CollectionReq collectionReq) {
        CollectionResp collectionResp = new CollectionResp();
        List<CollectQuestion> list = dao.queryCollectQuestion(collectionReq.getPsId(), collectionReq.getqId());
        if (list.size() == 0) {
            CollectQuestion collectQuestion = new CollectQuestion(collectionReq.getPsId(), collectionReq.getqId(), 0);
            dao.saveCollectQuestion(collectQuestion);
        } else {
            CollectQuestion collectQuestion = list.get(0);
            collectQuestion.setCollectTime(collectQuestion.getCollectTime() + 1);
        }
        collectionResp.setResult(CollectionResp.RESULT_SUCCESS);
        collectionResp.setDesc(CollectionResp.DESC_SUCCESS);
        return collectionResp;
    }

    public UnCollectionResp unCollection(UnCollectionReq unCollectionReq) {
        UnCollectionResp unCollectionResp = new UnCollectionResp();
        List<CollectQuestion> list = dao.queryCollectQuestion(unCollectionReq.getPsId(), unCollectionReq.getqId());
        if (list.size() == 0) {
            System.out.println(unCollectionReq.getqId() + "is not found");
            unCollectionResp.setResult(UnCollectionResp.RESULT_FAILED);
        } else {
            CollectQuestion collectQuestion = list.get(0);
            dao.deleteCollectQuestion(collectQuestion);
            unCollectionResp.setResult(UnCollectionResp.RESULT_SUCCESS);
            unCollectionResp.setDesc(UnCollectionResp.DESC_SUCCESS);
        }
        return unCollectionResp;
    }

    public List<String> getCollectQIdList(String qId) {
        List<CollectQuestion> collectQuestions = dao.queryCollectQuestion(qId);
        List<String> collectionQIdList = new ArrayList<String>();
        for (CollectQuestion collectQuestion : collectQuestions) {
            collectionQIdList.add(collectQuestion.getqId());
        }
        return collectionQIdList;
    }


//    public GetGroupsResp getGroups(GetGroupsReq getGroupsReq) {
//        GetGroupsResp getGroupsResp = new GetGroupsResp();
//        List<Student> getGroupsList = dao.queryStudentHomework(getGroupsReq.getId());
//        System.out.println("getGroupsList" + getGroupsList.size());
////        List<TestHistory> testHistoryList = new ArrayList<TestHistory>();
//        for (StudentHomework studentHomework : studentHomeworkList) {
//            HomeWork homeWork = dao.getHomework(studentHomework.getHomeworkId());
//            TestHistory testHistory = new TestHistory();
//            testHistory.setId(studentHomework.getId());
//            testHistory.setName(homeWork.getName());
//            testHistory.setNewTime(homeWork.getNewDate());
//            testHistory.setState(studentHomework.getState());
//            testHistoryList.add(testHistory);
//        }
//        getStudentHomeworkResp.setResult(GetStudentHomeworkResp.RESULT_SUCCESS);
//        getStudentHomeworkResp.setDesc(GetStudentHomeworkResp.DESC_SUCCESS);
//        getStudentHomeworkResp.setHomeworkList(testHistoryList);
//        return getStudentHomeworkResp;
//    }


//    public AddGroupsResp addClazz(AddGroupsReq addGroupsReq) {
//        AddGroupsResp addGroupsResp = new AddGroupsResp();
//        List<Student> list = dao.queryClazz(addClazzReq.getSchoolId(), addClazzReq.getGrade(), addClazzReq.getClazz());
//        if (list.size() == 0) {
//            Clazz clazz = new Clazz(addClazzReq.getSchoolId(), addClazzReq.getGrade(), addClazzReq.getClazz());
//            dao.saveClazz(clazz);
//            addClazzResp.setResult(addClazzResp.RESULT_SUCCESS);
//            addClazzResp.setDesc(addClazzResp.DESC_SUCCESS);
//        } else {
//            addClazzResp.setResult(addClazzResp.RESULT_FAILED);
//            addClazzResp.setDesc(addClazzResp.DESC_HAS_EXIST);
//        }
//        return addClazzResp;
//    }
//    public int[] getSites(int num) {
//        int[] sites = new int[num];
//        int[] seed = {1,2,3,4,5,6,7,8,9,10};
//        for(int i=0;i<num;i++){
//            int j = new Random().nextInt(10);
//            sites[i] = seed[j];
//            seed[j] = seed[9-i];
//        }
//        return sites;
//    }

    // 初始化Question
        /*
        for(int i=1;i<101;i++){
            String casualWorking = "第"+i+"题题干";
            String resolve = "第"+i+"题解析";
            List<String> ops = new ArrayList<String>();
            ops.add("第"+i+"题选项A");
            ops.add("第"+i+"题选项B");
            ops.add("第"+i+"题选项C");
            ops.add("第"+i+"题选项D");
            String options = JsonUtils.toJson(ops);
            int ans = new Random().nextInt(4);

            String answer;
            switch(ans){
                case 1:
                    answer = "A";
                    break;
                case 2:
                    answer = "B";
                    break;
                case 3:
                    answer = "C";
                    break;
                default:
                    answer = "D";
                    break;
            }
            int num = new Random().nextInt(5)+1;
            int[] sites = getSites(num);
            List<String> sits = new ArrayList<String>();
            for(int sit :sites){
                sits.add("知识点"+sit);
            }
            String testSites = JsonUtils.toJson(sits);

            Question question = new Question(casualWorking, options, answer, resolve, testSites);
            hibernateTemplate.save(question);
        }
        GetMyTestResp getMytestResp = new GetMyTestResp();
        return getMytestResp;
        */
}
