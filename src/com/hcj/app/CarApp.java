package com.hcj.app;

import com.hcj.web.CarSystemController;


/**
 * CarApp
 *   启动项目
 * @author hcj
 * @date 2022-02-16
 */
public class CarApp {
    public static void main(String[] args) {
        CarSystemController csc = new CarSystemController();
        csc.startSystem();
    }
}
