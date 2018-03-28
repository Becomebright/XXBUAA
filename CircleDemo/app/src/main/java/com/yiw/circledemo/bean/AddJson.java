package com.yiw.circledemo.bean;

import java.util.List;

/**
 * Created by hasee-pc on 2018/03/20.
 */

public class AddJson {
    private int user_id;
    private String content;
    private String createTime;
    private String type;
    private List<PhotoInfo> photos;

    public AddJson(int user_id, String content, String createTime, String type, List<PhotoInfo> photos) {
        this.user_id = user_id;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
        this.photos = photos;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInfo> photos) {
        this.photos = photos;
    }
}
