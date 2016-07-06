package com.webapp.model.Req;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aass on 2016/4/8.
 */
public class AddGroupsReq extends BaseReq {
    private String id;
    private String name;
    private List<Student> addGroupsList = new ArrayList<Student>();

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

    public AddGroupsReq() {
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

    public List<Student> getAddGroupsList() {
        return addGroupsList;
    }

    public void setAddGroupsList(List<Student> addGroupsList) {
        this.addGroupsList = addGroupsList;
    }
}
