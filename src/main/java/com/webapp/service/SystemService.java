package com.webapp.service;

import com.webapp.model.Req.*;
import com.webapp.model.Resp.*;

/**
 *
 * Created by zjm on 15-12-7.
 */
public interface SystemService {

    LoginResp login(LoginReq loginReq);

    ForgetPasswordResp forgetPassword(ForgetPasswordReq forgetPasswordReq);

    RegisterResp register(RegisterReq registerReq);

    FinishInfoResp finishInfo(FinishInfoReq finishInfoReq);

    GetInfoResp getInfo(GetInfoReq getInfoReq);

    GetSchoolResp getSchoolList(GetSchoolReq getSchoolReq);

    AddSchoolResp addSchool(AddSchoolReq addSchoolReq);

    GetClazzResp getClazzList(GetClazzReq getClazzReq);

    AddClazzResp addClazz(AddClazzReq addClazzReq);

    GetTestSitesResp getTestSites(GetTestSitesReq getTestSitesReq);

    GetNewTestResp getNewTest(GetNewTestReq getNewTestReq);

    GetMyTestResp getMyTest(GetMyTestReq getMyTestReq);

    SubmitTestResp submitTest(SubmitTestReq submitTestReq);

    CollectionResp collection(CollectionReq collectionReq);

    UnCollectionResp unCollection(UnCollectionReq collectionReq);

    GetSelfTestByIdResp getSelfTestById(GetSelfTestByIdReq getSelfTestByIdReq);

    GetErrorsResp getErrors(GetErrorsReq getErrorsReq);

    GetCollectionsResp getCollections(GetCollectionsReq getCollectionsReq);

    SearchPersonResp searchPerson(SearchPersonReq searchPersonReq);

    GetClazzDetailResp getClazzDetail(GetClazzDetailReq getClazzDetailReq);

    PostHomeworkResp postHomework (PostHomeworkReq postHomeworkReq);

    GetTeacherHomeworkResp getTeacherHomework(GetTeacherHomeworkReq getTeacherHomeworkReq);

    GetHomeworkByIdResp getHomeworkById(GetHomeworkByIdReq getHomeworkByIdReq);

    SubmitStudentResp submitStudent(SubmitStudentReq submitStudentReq);

    GetStudentHomeworkResp getStudentHomework(GetStudentHomeworkReq getStudentHomeworkReq);

    GetStudentHomeworkByIdResp getStudentHomeworkById(GetStudentHomeworkByIdReq getStudentHomeworkByIdReq);

    SubmitStudentHomeworkResp submitStudentHomework(SubmitStudentHomeworkReq submitStudentHomeworkReq);
    GetGroupsResp getGroups(GetGroupsReq getGroupsReq);

    AddGroupsResp addGroups(AddGroupsReq addGroupsReq);

    GetStudentAnalysisResp getStudentAnalysis(GetStudentAnalysisReq getStudentAnalysisReq);

    GetHeadResp getHead(GetHeadReq getHeadReq);

    GetTeacherAnalysisResp getTeacherAnalysis(GetTeacherAnalysisReq getTeacherAnalysisReq);
}
