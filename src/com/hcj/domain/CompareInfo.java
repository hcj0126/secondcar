package com.hcj.domain;

import java.util.ArrayList;

/**
 * 存入对比车辆
 *
 * @author hcj
 */
public class CompareInfo {

    private ArrayList<Object> title;

    public CompareInfo() {
        title = new ArrayList<Object>();
    }

    public ArrayList<Object> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<Object> title) {
        this.title = title;
    }

}

