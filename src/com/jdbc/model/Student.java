package com.jdbc.model;

import java.util.Date;

/**
 * 学生实体类
 * <p/>
 * Created by yuanyin on 16/1/30.
 */
public class Student {

    private Integer id;
    private String user_name;
    private Integer sex;
    private Integer age;
    private Date birthday;
    private String email;
    private String mobile;
    private String create_user;
    private Date create_data;
    private String update_user;
    private Date update_data;
    private Integer isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreate_data() {
        return create_data;
    }

    public void setCreate_data(Date create_data) {
        this.create_data = create_data;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Date getUpdate_data() {
        return update_data;
    }

    public void setUpdate_data(Date update_data) {
        this.update_data = update_data;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", create_user='" + create_user + '\'' +
                ", create_data=" + create_data +
                ", update_user='" + update_user + '\'' +
                ", update_data=" + update_data +
                ", isdel=" + isdel +
                '}';
    }
}
