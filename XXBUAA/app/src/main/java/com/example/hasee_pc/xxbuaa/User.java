package com.example.hasee_pc.xxbuaa;



public class User{
    String username;//统一认证账号
    String password;//统一认证密码
    String nickname;//姓名
    String gender;//性别
    String academy;//学院
    String stu_id;//学号

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    int user_id;

    public User(String nickname, String gender, String academy, String stu_id, int user_id) {
        this.nickname = nickname;
        this.gender = gender;
        this.academy = academy;
        this.stu_id = stu_id;
        this.user_id = user_id;
    }

    public User(String username) {
        this.username = username;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public User(){
        this.username = "";
        this.password = "";
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
