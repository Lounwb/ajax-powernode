package com.lounwb.ajax.beans;

/**
 * 区域类
 */
public class Area {
    private String code;
    private String name;

    public Area() {
    }

    public Area(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
