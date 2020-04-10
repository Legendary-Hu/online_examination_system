package com.bjqf.entity;

public class StudentList {
    private String spid;
    private int userid;
    private String pname;
    private int rightcount;
    private int errorcount;
    public String getSpid() {
        return spid;
    }
    public void setSpid(String spid) {
        this.spid = spid;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public int getRightcount() {
        return rightcount;
    }
    public void setRightcount(int rightcount) {
        this.rightcount = rightcount;
    }
    public int getErrorcount() {
        return errorcount;
    }
    public void setErrorcount(int errorcount) {
        this.errorcount = errorcount;
    }
}
