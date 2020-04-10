package com.bjqf.entity;

public class FunRole {
    private int funid;
    private String funname;
    private String funurl;
    private int funpid;
    private boolean funstate;
    private boolean rr;
    public int getFunid() {
        return funid;
    }
    public void setFunid(int funid) {
        this.funid = funid;
    }
    public String getFunname() {
        return funname;
    }
    public void setFunname(String funname) {
        this.funname = funname;
    }
    public String getFunurl() {
        return funurl;
    }
    public void setFunurl(String funurl) {
        this.funurl = funurl;
    }
    public int getFunpid() {
        return funpid;
    }
    public void setFunpid(int funpid) {
        this.funpid = funpid;
    }
    public boolean isFunstate() {
        return funstate;
    }
    public void setFunstate(boolean funstate) {
        this.funstate = funstate;
    }
    public boolean isRr() {
        return rr;
    }
    public void setRr(boolean rr) {
        this.rr = rr;
    }
}
