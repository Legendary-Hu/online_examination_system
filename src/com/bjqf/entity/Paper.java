package com.bjqf.entity;

public class Paper {
    private int pid;
    private String pname;
    private int sid;
    //计算每套试卷的试题数量
    private int pcount;
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public int getPcount() {
        return pcount;
    }
    public void setPcount(int pcount) {
        this.pcount = pcount;
    }
}
