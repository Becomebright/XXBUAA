package com.yiw.circledemo.bean;

/**
 * Created by hasee-pc on 2018/03/21.
 */

public class CommentJson {
    private int comment_id;
    private String content;
    private int user_id;
    private String nickname;
    private int toReplyUser_id;
    private  String toReplyUser_nickname;
    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getToReplyUser_id() {
        return toReplyUser_id;
    }

    public void setToReplyUser_id(int toReplyUser_id) {
        this.toReplyUser_id = toReplyUser_id;
    }

    public String getToReplyUser_nickname() {
        return toReplyUser_nickname;
    }

    public void setToReplyUser_nickname(String toReplyUser_nickname) {
        this.toReplyUser_nickname = toReplyUser_nickname;
    }
}
