package com.softeem.test;

import java.util.Properties;

public class test {
    public static void main(String[] args) {
        Properties pro = System.getProperties();
        System.out.println(pro.getProperty("user.home"));
    }
}
