package com.yiw.circledemo.bean;

import java.util.List;

/**
 * Created by hasee-pc on 2018/03/07.
 */

public class TestCircleItem {
    String username;
    String content;

    String createTime;
    String type;
    private List<PhotoInfo> photos;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public TestCircleItem() {

    }
}
