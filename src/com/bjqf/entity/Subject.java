package com.bjqf.entity;

public class Subject {
    /**
     * 题目实体类
     */
    private int sid;
    private String scontent;
    private String sa;
    private String sb;
    private String sc;
    private String sd;
    private String skey;
    private boolean sstate;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public boolean isSstate() {
        return sstate;
    }

    public void setSstate(boolean sstate) {
        this.sstate = sstate;
    }
}
