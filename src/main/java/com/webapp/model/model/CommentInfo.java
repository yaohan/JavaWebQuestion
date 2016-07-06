package com.webapp.model.model;

/**
 * Created by yao_han on 2016/5/31.
 */
public class CommentInfo{
    private int role;
    private String headPortrait;
    private String name;
    private String comment;

    public CommentInfo() {
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}