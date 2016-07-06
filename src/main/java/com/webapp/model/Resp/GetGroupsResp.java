package com.webapp.model.Resp;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aass on 2016/4/8.
 */
public class GetGroupsResp extends BaseResp {
    public static String DESC_SUCCESS = "获取成功";
    private List<Student> getGroupsList = new ArrayList<Student>();
    private List<Student> addGroupsList = new ArrayList<Student>();
    private String id;
    private String name;

    private class Student {
        public String sid;
        public String sname;

        public Student() {
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }
    }

    public GetGroupsResp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDescSuccess() {
        return DESC_SUCCESS;
    }

    public static void setDescSuccess(String descSuccess) {
        DESC_SUCCESS = descSuccess;
    }

    public List<Student> getGetgroups() {
        return getGroupsList;
    }

    public void setGetgroups(List<Student> getgroups) {
        this.getGroupsList = getgroups;
    }

    public List<Student> getAddGroupsList() {
        return addGroupsList;
    }

    public void setAddGroupsList(List<Student> addGroupsList) {
        this.addGroupsList = addGroupsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
