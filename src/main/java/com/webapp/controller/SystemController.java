package com.webapp.controller;

import com.webapp.model.Req.*;
import com.webapp.model.Resp.*;
import com.webapp.model.model.SelfTestModel;
import com.webapp.model.model.TestHistory;
import com.webapp.model.table.School;
import com.webapp.service.SystemService;
import com.webapp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 15-11-26.
 */
@Controller
@RequestMapping("/")
public class SystemController {

    @Autowired
    private SystemService systemService;
    // 请求参数的key
    public static final String REQUEST_PARAMS_KEY = "req";

    Map<String,Object> getReqs(Map<String,Object> reqs){
        return JsonUtils.readValue((String)reqs.get(REQUEST_PARAMS_KEY),Map.class);
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam Map<String,Object> reqs) {
        System.out.println("before: "+reqs);
        System.out.println("get   : "+reqs.get("req"));
        reqs = getReqs(reqs);
        System.out.println("after : "+reqs);
        LoginResp loginResp = new LoginResp();
        LoginReq loginReq = JsonUtils.readValue(reqs,LoginReq.class);
        String ret = loginReq.hasNull();
        if(ret == null){
            System.out.println("ret = null");
            loginResp = systemService.login(loginReq);
        }else{
            loginResp.setResult(LoginResp.RESULT_FAILED);
            loginResp.setDesc(ret+" is null");
            loginResp.setType(LoginResp.ROLE_NULL);
        }
        return JsonUtils.readValue(loginResp,Map.class);
    }

    @RequestMapping(value = "forgetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> forgetPassword(@RequestParam Map<String,Object> reqs) {
        reqs = getReqs(reqs);
        ForgetPasswordResp forgetPasswordResp = new ForgetPasswordResp();
        ForgetPasswordReq forgetPasswordReq = JsonUtils.readValue(reqs,ForgetPasswordReq.class);
        String ret = forgetPasswordReq.hasNull();
        if(ret == null){
            forgetPasswordResp = systemService.forgetPassword(forgetPasswordReq);
        }else{
            forgetPasswordResp.setResult(forgetPasswordResp.RESULT_FAILED);
            forgetPasswordResp.setDesc(ret+" is null");
        }
        return JsonUtils.readValue(forgetPasswordResp,Map.class);
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(@RequestParam Map<String,Object> reqs) {
        System.out.println("req:"+reqs);
        reqs = getReqs(reqs);
        System.out.println("req:"+reqs);
        RegisterResp registerResp = new RegisterResp();
        RegisterReq registerReq = JsonUtils.readValue(reqs,RegisterReq.class);
        String ret = registerReq.hasNull();
        if(ret == null){
            registerResp = systemService.register(registerReq);
        }else{
            registerResp.setResult(registerResp.RESULT_FAILED);
            registerResp.setDesc(ret+" is null");
        }
        return JsonUtils.readValue(registerResp,Map.class);
    }

    @RequestMapping(value = "finishInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> finishInfo(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        FinishInfoResp finishInfoResp = new FinishInfoResp();
        FinishInfoReq finishInfoReq = JsonUtils.readValue(reqs,FinishInfoReq.class);
        String ret = finishInfoReq.hasNull();
        if(ret == null){
            finishInfoResp = systemService.finishInfo(finishInfoReq);
        }else{
            finishInfoResp.setResult(FinishInfoResp.RESULT_FAILED);
            finishInfoResp.setDesc(ret + " is null");
        }

        return JsonUtils.readValue(finishInfoResp,Map.class);
    }

    @RequestMapping(value = "getInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getInfo(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetInfoResp getInfoResp = new GetInfoResp();
        GetInfoReq getInfoReq = JsonUtils.readValue(reqs,GetInfoReq.class);
        String ret = getInfoReq.hasNull();
        if(ret == null){
            getInfoResp = systemService.getInfo(getInfoReq);
        }else{
            getInfoResp.setResult(GetInfoResp.RESULT_FAILED);
            getInfoResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getInfoResp, Map.class);
    }


    @RequestMapping(value = "getSchoolList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getSchoolList(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetSchoolResp getSchoolResp = new GetSchoolResp();
        GetSchoolReq getSchoolReq = new GetSchoolReq();
        getSchoolResp = systemService.getSchoolList(getSchoolReq);
        return JsonUtils.readValue(getSchoolResp,Map.class);
    }

    @RequestMapping(value = "addSchool",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addSchool(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        AddSchoolResp addSchoolResp = new AddSchoolResp();
        AddSchoolReq addSchoolReq = JsonUtils.readValue(reqs,AddSchoolReq.class);
        String ret = addSchoolReq.hasNull();
        if(ret == null){
            addSchoolResp = systemService.addSchool(addSchoolReq);
        }else{
            addSchoolResp.setResult(AddSchoolResp.RESULT_FAILED);
            addSchoolResp.setDesc(ret +" is null");
        }
        return JsonUtils.readValue(addSchoolResp,Map.class);
    }

    @RequestMapping(value = "getClazzList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getClazzList(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetClazzResp getClazzResp = new GetClazzResp();
        GetClazzReq getClazzReq = JsonUtils.readValue(reqs,GetClazzReq.class);
        String ret = getClazzReq.hasNull();
        if(ret == null){
            getClazzResp = systemService.getClazzList(getClazzReq);
        }else{
            getClazzResp.setResult(GetClazzResp.RESULT_FAILED);
            getClazzResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getClazzResp,Map.class);
    }

    @RequestMapping(value = "addClazz",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addClazz(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        AddClazzResp addClazzResp = new AddClazzResp();
        AddClazzReq addClazzReq = JsonUtils.readValue(reqs,AddClazzReq.class);
        String ret = addClazzReq.hasNull();
        if(ret == null){
            addClazzResp = systemService.addClazz(addClazzReq);
        }else{
            addClazzResp.setResult(AddClazzResp.RESULT_SUCCESS);
            addClazzResp.setDesc(ret +" is null");
        }
        return JsonUtils.readValue(addClazzResp,Map.class);
    }

    @RequestMapping(value = "getTestSites",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getTestSites(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetTestSitesResp getTestSitesResp = new GetTestSitesResp();
        GetTestSitesReq getTestSitesReq = new GetTestSitesReq();
        getTestSitesResp = systemService.getTestSites(getTestSitesReq);
        return JsonUtils.readValue(getTestSitesResp,Map.class);
    }

    @RequestMapping(value = "getNewTest",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getNewTest(@RequestParam Map<String,Object> reqs){
        System.out.println("reqs:"+reqs);
        reqs = getReqs(reqs);
        System.out.println("reqs:"+reqs);
        GetNewTestResp getNewTestResp = new GetNewTestResp();
        GetNewTestReq getNewTestReq = JsonUtils.readValue(reqs,GetNewTestReq.class);
        System.out.println("getNewTestReq==null?"+(getNewTestReq == null));
        String ret = getNewTestReq.hasNull();
        if(ret == null){
            getNewTestResp = systemService.getNewTest(getNewTestReq);
        }else{
            getNewTestResp.setResult(GetNewTestResp.RESULT_FAILED);
            getNewTestResp.setDesc(ret +" is null");
        }
        return JsonUtils.readValue(getNewTestResp,Map.class);
    }

    @RequestMapping(value = "getMyTest",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMyTest(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetMyTestResp getMyTestResp = new GetMyTestResp();
        GetMyTestReq getMyTestReq = JsonUtils.readValue(reqs,GetMyTestReq.class);
        String ret = getMyTestReq.hasNull();
        if(ret == null){
            getMyTestResp = systemService.getMyTest(getMyTestReq);
        }else{
            getMyTestResp.setResult(GetMyTestResp.RESULT_FAILED);
            getMyTestResp.setDesc(ret+" is null");
        }
        return JsonUtils.readValue(getMyTestResp,Map.class);
    }

    @RequestMapping(value = "getSelfTestById",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getSelfTestById(@RequestParam Map<String,Object> reqs){
        System.out.println("reqs:"+reqs);
        reqs = getReqs(reqs);
        System.out.println("reqs:"+reqs);
        GetSelfTestByIdResp getSelfTestByIdResp = new GetSelfTestByIdResp();
        GetSelfTestByIdReq getSelfTestByIdReq = JsonUtils.readValue(reqs,GetSelfTestByIdReq.class);
        System.out.println("getSelfTestByIdReq == null?:"+(getSelfTestByIdReq == null));
        String ret = getSelfTestByIdReq.hasNull();
        if(ret == null){
            getSelfTestByIdResp = systemService.getSelfTestById(getSelfTestByIdReq);
        }else{
            getSelfTestByIdResp.setSelfTestModel(new SelfTestModel());
            getSelfTestByIdResp.setResult(GetMyTestResp.RESULT_FAILED);
            getSelfTestByIdResp.setDesc(ret+" is null");
        }
        return JsonUtils.readValue(getSelfTestByIdResp,Map.class);
    }

    @RequestMapping(value = "submitTest",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitTest(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        SubmitTestResp submitTestResp = new SubmitTestResp();
        SubmitTestReq submitTestReq = JsonUtils.readValue(reqs,SubmitTestReq.class);
        String ret = submitTestReq.hasNull();
        if(ret == null){
            submitTestResp = systemService.submitTest(submitTestReq);
        }else{
            submitTestResp.setResult(SubmitTestResp.RESULT_FAILED);
            submitTestResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(submitTestResp,Map.class);
    }

    @RequestMapping(value = "collection",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> collection(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        CollectionResp collectionResp = new CollectionResp();
        CollectionReq collectionReq = JsonUtils.readValue(reqs,CollectionReq.class);
        String ret = collectionReq.hasNull();
        if(ret == null){
            collectionResp = systemService.collection(collectionReq);
        }else{
            collectionResp.setResult(CollectionResp.RESULT_FAILED);
            collectionResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(collectionResp,Map.class);
    }

    @RequestMapping(value = "unCollection",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> unCollection(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        UnCollectionResp unCollectionResp = new UnCollectionResp();
        UnCollectionReq unCollectionReq = JsonUtils.readValue(reqs,UnCollectionReq.class);
        String ret = unCollectionReq.hasNull();
        if(ret == null){
            unCollectionResp = systemService.unCollection(unCollectionReq);
        }else{
            unCollectionResp.setResult(CollectionResp.RESULT_FAILED);
            unCollectionResp.setDesc(ret+"is null");
        }
        return JsonUtils.readValue(unCollectionResp,Map.class);
    }

    @RequestMapping(value = "getErrors",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getErrors(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetErrorsResp getErrorsResp = new GetErrorsResp();
        GetErrorsReq getErrorsReq = JsonUtils.readValue(reqs,GetErrorsReq.class);
        String ret = getErrorsReq.hasNull();
        if(ret == null){
            getErrorsResp = systemService.getErrors(getErrorsReq);
        }else{
            getErrorsResp.setResult(GetErrorsResp.RESULT_FAILED);
            getErrorsResp.setDesc(ret + "is null");
        }
        return JsonUtils.readValue(getErrorsResp,Map.class);
    }

    @RequestMapping(value = "getCollections",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCollections(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetCollectionsResp getCollectionsResp = new GetCollectionsResp();
        GetCollectionsReq getCollectionsReq = JsonUtils.readValue(reqs,GetCollectionsReq.class);
        String ret = getCollectionsReq.hasNull();
        if(ret == null){
            System.out.println("get Collections");
            getCollectionsResp = systemService.getCollections(getCollectionsReq);
        }else{
            getCollectionsResp.setResult(GetErrorsResp.RESULT_FAILED);
            getCollectionsResp.setDesc(ret + "is null");
        }
        return JsonUtils.readValue(getCollectionsResp,Map.class);
    }

    @RequestMapping(value = "searchPerson",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchPerson(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        SearchPersonResp searchPersonResp = new SearchPersonResp();
        SearchPersonReq searchPersonReq = JsonUtils.readValue(reqs,SearchPersonReq.class);
        String ret = searchPersonReq.hasNull();
        System.out.println("ret:"+ret);
        if(ret == null){
            System.out.println("start service");
            searchPersonResp = systemService.searchPerson(searchPersonReq);
        }else{
            searchPersonResp.setResult(SearchPersonResp.RESULT_FAILED);
            searchPersonResp.setDesc(ret + "is null");
        }
        return JsonUtils.readValue(searchPersonResp, Map.class);
    }

    @RequestMapping(value = "getClazzDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getClazzDetail(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetClazzDetailResp getClazzDetailResp = new GetClazzDetailResp();
        GetClazzDetailReq getClazzDetailReq = JsonUtils.readValue(reqs, GetClazzDetailReq.class);
        String ret = getClazzDetailReq.hasNull();
        if(ret == null){
            getClazzDetailResp = systemService.getClazzDetail(getClazzDetailReq);
        }else{
            getClazzDetailResp.setResult(SearchPersonResp.RESULT_FAILED);
            getClazzDetailResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getClazzDetailResp, Map.class);
    }


    @RequestMapping(value = "postHomework", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> postHomework(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        PostHomeworkResp postHomeworkResp = new PostHomeworkResp();
        PostHomeworkReq postHomeworkReq = JsonUtils.readValue(reqs,PostHomeworkReq.class);
        String ret = postHomeworkReq.hasNull();

        if(ret == null){
            System.out.println("posthomework start");
            postHomeworkResp = systemService.postHomework(postHomeworkReq);
            System.out.println("posthomework end");
        }else{
            postHomeworkResp.setResult(postHomeworkResp.RESULT_FAILED);
            postHomeworkResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(postHomeworkResp, Map.class);
    }

    @RequestMapping(value = "getTeacherHomework", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTeacherHomework(@RequestParam Map<String, Object> reqs){
        reqs = getReqs(reqs);
        GetTeacherHomeworkResp getTeacherHomeworkResp = new GetTeacherHomeworkResp();
        GetTeacherHomeworkReq getTeacherHomeworkReq = JsonUtils.readValue(reqs, GetTeacherHomeworkReq.class);
        String ret = getTeacherHomeworkReq.hasNull();
        if(ret == null){
            getTeacherHomeworkResp = systemService.getTeacherHomework(getTeacherHomeworkReq);
        }else{
            getTeacherHomeworkResp.setResult(GetTeacherHomeworkResp.RESULT_FAILED);
            getTeacherHomeworkResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getTeacherHomeworkResp, Map.class);
    }

    @RequestMapping(value = "getHomeworkById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getHomeworkById(@RequestParam Map<String, Object> reqs){
        reqs = getReqs(reqs);
        GetHomeworkByIdResp getHomeworkByIdResp = new GetHomeworkByIdResp();
        GetHomeworkByIdReq getHomeworkByIdReq = JsonUtils.readValue(reqs,GetHomeworkByIdReq.class);
        String ret = getHomeworkByIdReq.hasNull();
        if(ret == null){
            getHomeworkByIdResp = systemService.getHomeworkById(getHomeworkByIdReq);
        }else{
            getHomeworkByIdResp.setResult(GetHomeworkByIdResp.RESULT_FAILED);
            getHomeworkByIdResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getHomeworkByIdResp, Map.class);
    }

    @RequestMapping(value = "submitStudent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitStudent(@RequestParam Map<String, Object> reqs){
        System.out.println("reqs");
        reqs = getReqs(reqs);
        SubmitStudentResp submitStudentResp = new SubmitStudentResp();
        SubmitStudentReq submitStudentReq = JsonUtils.readValue(reqs,SubmitStudentReq.class);
        String ret = submitStudentReq.hasNull();
        if(ret == null){
            submitStudentResp = systemService.submitStudent(submitStudentReq);
        }else{
            submitStudentResp.setResult(SubmitStudentResp.RESULT_FAILED);
            submitStudentResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(submitStudentResp, Map.class);
    }

    @RequestMapping(value = "getStudentHomework", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStudentHomework(@RequestParam Map<String, Object> reqs){
        reqs = getReqs(reqs);
        GetStudentHomeworkResp getStudentHomeworkResp = new GetStudentHomeworkResp();
        GetStudentHomeworkReq getStudentHomeworkReq = JsonUtils.readValue(reqs, GetStudentHomeworkReq.class);
        String ret = getStudentHomeworkReq.hasNull();
        if(ret == null){
            getStudentHomeworkResp = systemService.getStudentHomework(getStudentHomeworkReq);
        }else{
            getStudentHomeworkResp.setResult(GetStudentHomeworkResp.RESULT_FAILED);
            getStudentHomeworkResp.setDesc(ret +" is null");
        }
        return JsonUtils.readValue(getStudentHomeworkResp, Map.class);
    }

    @RequestMapping(value = "getStudentHomeworkById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStudentHomeworkById(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetStudentHomeworkByIdResp getStudentHomeworkByIdResp = new GetStudentHomeworkByIdResp();
        GetStudentHomeworkByIdReq getStudentHomeworkByIdReq = JsonUtils.readValue(reqs, GetStudentHomeworkByIdReq.class);
        String ret = getStudentHomeworkByIdReq.hasNull();
        if(ret == null){
            getStudentHomeworkByIdResp = systemService.getStudentHomeworkById(getStudentHomeworkByIdReq);
        }else{
            getStudentHomeworkByIdResp.setResult(GetStudentHomeworkByIdResp.RESULT_FAILED);
            getStudentHomeworkByIdResp.setDesc(ret + "is null");
        }
        return JsonUtils.readValue(getStudentHomeworkByIdResp, Map.class);
    }

    @RequestMapping(value = "submitStudentHomework", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitStudentHomework(@RequestParam Map<String, Object> reqs){

        reqs = getReqs(reqs);
        SubmitStudentHomeworkResp submitStudentHomeworkResp = new SubmitStudentHomeworkResp();
        System.out.println("req:"+JsonUtils.toJson(reqs));
        SubmitStudentHomeworkReq submitStudentHomeworkReq = JsonUtils.readValue(reqs, SubmitStudentHomeworkReq.class);
        String ret = submitStudentHomeworkReq.hasNull();
        if(ret == null){
            submitStudentHomeworkResp = systemService.submitStudentHomework(submitStudentHomeworkReq);
        }else{
            submitStudentHomeworkResp.setResult(SubmitStudentResp.RESULT_FAILED);
            submitStudentHomeworkResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(submitStudentHomeworkResp, Map.class);
    }

//    @RequestMapping(value = "getGroups", method = RequestMethod.POST)
//    @RequestMapping(value = "getGroups", method = RequestMethod.POST)
    @RequestMapping(value = "getGroups", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>getGroups(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetGroupsResp getGroupsResp = new GetGroupsResp();
        GetGroupsReq getGroupsReq = JsonUtils.readValue(reqs,GetGroupsReq.class);
        String ret =getGroupsReq.hasNull();

        if(ret == null){
            getGroupsResp = systemService.getGroups(getGroupsReq);
        }else{
           getGroupsResp.setResult(getGroupsResp.RESULT_FAILED);
           getGroupsResp.setDesc(ret + " is null");
        }
        return JsonUtils.readValue(getGroupsResp, Map.class);
    }


    @RequestMapping(value = "addGroups",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addGroups(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        AddGroupsResp addGroupsResp = new AddGroupsResp();
        AddGroupsReq addGroupsReq = JsonUtils.readValue(reqs,AddGroupsReq.class);
        String ret = addGroupsReq.hasNull();
        if(ret == null){
            addGroupsResp = systemService.addGroups(addGroupsReq);
        }else{
            addGroupsResp.setResult(AddGroupsResp.RESULT_FAILED);
            addGroupsResp.setDesc(ret +" is null");
        }
        return JsonUtils.readValue(addGroupsResp,Map.class);
    }

    @RequestMapping(value = "getStudentAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getStudentAnalysis(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetStudentAnalysisResp getStudentAnalysisResp = new GetStudentAnalysisResp();
        GetStudentAnalysisReq getStudentAnalysisReq = JsonUtils.readValue(reqs,GetStudentAnalysisReq.class);
        String ret = getStudentAnalysisReq.hasNull();
        if(ret == null){
            getStudentAnalysisResp = systemService.getStudentAnalysis(getStudentAnalysisReq);
        }else{
            getStudentAnalysisResp.setResult(GetStudentAnalysisResp.RESULT_FAILED);
            getStudentAnalysisResp.setDesc(ret + "is null");
        }
        return JsonUtils.readValue(getStudentAnalysisResp,Map.class);
    }

    @RequestMapping(value = "getHead", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getHead(@RequestParam Map<String,Object> reqs){
        reqs = getReqs(reqs);
        GetHeadResp getHeadResp = systemService.getHead(JsonUtils.readValue(reqs,GetHeadReq.class));
        return JsonUtils.readValue(getHeadResp, Map.class);
    }
//    @RequestMapping(value = "addSchool",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> addSchool(@RequestParam Map<String,Object> reqs){
//        reqs = getReqs(reqs);
//        AddSchoolResp addSchoolResp = new AddSchoolResp();
//        AddSchoolReq addSchoolReq = JsonUtils.readValue(reqs,AddSchoolReq.class);
//        String ret = addSchoolReq.hasNull();
//        if(ret == null){
//            addSchoolResp = systemService.addSchool(addSchoolReq);
//        }else{
//            addSchoolResp.setResult(AddSchoolResp.RESULT_FAILED);
//            addSchoolResp.setDesc(ret +" is null");
//        }
//        return JsonUtils.readValue(addSchoolResp,Map.class);
//    }

}
