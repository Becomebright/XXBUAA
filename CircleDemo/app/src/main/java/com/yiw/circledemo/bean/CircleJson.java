package com.yiw.circledemo.bean;

import java.util.List;

/**
 * Created by hasee-pc on 2018/03/07.
 */

public class CircleJson {
    private int user_id;
    private String nickname;
    private int post_id;
    private String content;
    private String createTime;
    private List<FavortJson> favorites;
    private List<PhotoInfo> photos;
    private List<CommentJson> comments;
    private String type;
    private String user_head_url;

    public String getUser_head_url() {
        return user_head_url;
    }

    public void setUser_head_url(String user_head_url) {
        this.user_head_url = user_head_url;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<FavortJson> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavortJson> favorites) {
        this.favorites = favorites;
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInfo> photos) {
        this.photos = photos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CommentJson> getComments() {
        return comments;
    }

    public void setComments(List<CommentJson> comments) {
        this.comments = comments;
    }
}
