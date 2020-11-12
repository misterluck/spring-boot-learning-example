package org.example.dubbo.api.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String nick;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
