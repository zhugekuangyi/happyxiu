package com.mingsheng.model;

import java.sql.Timestamp;

public class MobileType {

    private String id;

    private String name;

    private Integer levei;

    private String pid;

    private Timestamp ctime;


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

    public Integer getLevei() {
        return levei;
    }

    public void setLevei(Integer levei) {
        this.levei = levei;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
}
