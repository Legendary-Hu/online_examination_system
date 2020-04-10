package com.bjqf.entity;

public class User {
    //user表中的字段
    private int userid;
    private int roleid;
    private String username;
    private String userpwd;
    private String usertruename;
    private Boolean userstate;



    //role表中的字段
    private String rolename;
    private Boolean rolestate;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUsertruename() {
        return usertruename;
    }

    public void setUsertruename(String userturename) {
        this.usertruename = userturename;
    }

    public Boolean isUserstate() {
        return userstate;
    }

    public void setUserstate(Boolean userstate) {
        this.userstate = userstate;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Boolean isRolestate() {
        return rolestate;
    }

    public void setRolestate(Boolean rolestate) {
        this.rolestate = rolestate;
    }
}
