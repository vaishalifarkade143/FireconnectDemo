package com.example.fireconnectdemo;

import java.security.PublicKey;

public class VenderModel
{
    public String name;
    public String age;

    public VenderModel()
    {
    }

    public VenderModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
