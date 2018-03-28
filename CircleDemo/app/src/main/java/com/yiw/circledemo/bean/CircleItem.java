package com.yiw.circledemo.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class CircleItem extends BaseBean{

	public final static String TYPE_URL = "1";
	public final static String TYPE_IMG = "2";
	public final static String TYPE_VIDEO = "3";
	public static int favorite_id=0;
	public CircleItem() {
	}

//	public CircleItem(CircleJson circleJson,User user) {
//		this.setId(""+circleJson.getPost_id());
//		this.content=circleJson.getContent();
//		this.createTime=circleJson.getCreateTime();
//		this.type=circleJson.getType();
//		this.photos=circleJson.getPhotos();
//		this.user=user;
//
//
//		if(circleJson.getFavorites()!=null&&circleJson.getFavorites().size()>0){
//			favorites=new ArrayList<FavortItem>();
//			for(FavortJson favortJson:circleJson.getFavorites()){
//				User user1=new User(""+favortJson.getUser_id(),favortJson.getNickname(),null);
//				FavortItem favortItem=new FavortItem();
//				favortItem.setId(""+favorite_id++);
//				favortItem.setUser(user1);
//				favorites.add(favortItem);
//			}
//		}
//	}
	public CircleItem(CircleJson circleJson) {
		this.setId(""+circleJson.getPost_id());
		this.content=circleJson.getContent();
		this.createTime=circleJson.getCreateTime();
		if(circleJson.getType().equals("5")){
			this.type="2";
		}
		else{
			this.type=circleJson.getType();
		}
		this.photos=circleJson.getPhotos();

		this.user=new User(""+circleJson.getUser_id(),circleJson.getNickname(),circleJson.getUser_head_url());

		//"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522208042&di=a36962be14b6bc9039459e5ae53d3f6a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201606%2F12%2F20160612210613_i3ZPQ.jpeg"
		if(circleJson.getFavorites()!=null&&circleJson.getFavorites().size()>0){
			favorites=new ArrayList<FavortItem>();
			for(FavortJson favortJson:circleJson.getFavorites()){
				User user1=new User(""+favortJson.getUser_id(),favortJson.getNickname(),null);
				FavortItem favortItem=new FavortItem();
				favortItem.setId(""+favorite_id++);
				favortItem.setUser(user1);
				favorites.add(favortItem);
			}
		}
		if(circleJson.getComments()!=null&&circleJson.getComments().size()>0){
			comments=new ArrayList<CommentItem>();
			for(CommentJson commentJson:circleJson.getComments()){
				CommentItem commentItem=new CommentItem(commentJson);
				comments.add(commentItem);
			}
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String content;
	private String createTime;
	private String type;//1:链接  2:图片 3:视频
	private String linkImg;
	private String linkTitle;
	private List<PhotoInfo> photos;
	private List<FavortItem> favorites= new ArrayList<FavortItem>();
	private List<CommentItem> comments= new ArrayList<CommentItem>();
	private User user;
	private String videoUrl;
	private String videoImgUrl;

	private boolean isExpand;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<FavortItem> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<FavortItem> favorites) {
		this.favorites = favorites;
	}
	public List<CommentItem> getComments() {
		return comments;
	}
	public void setComments(List<CommentItem> comments) {
		this.comments = comments;
	}
	public String getLinkImg() {
		return linkImg;
	}
	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	public String getLinkTitle() {
		return linkTitle;
	}
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	public List<PhotoInfo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PhotoInfo> photos) {
		this.photos = photos;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public void setExpand(boolean isExpand){
		this.isExpand = isExpand;
	}

	public boolean isExpand(){
		return this.isExpand;
	}

	public boolean hasFavort(){
		if(favorites !=null && favorites.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean hasComment(){
		if(comments!=null && comments.size()>0){
			return true;
		}
		return false;
	}
	
	public String getCurUserFavortId(String curUserId){
		String favortid = "";
		if(!TextUtils.isEmpty(curUserId) && hasFavort()){
			for(FavortItem item : favorites){
				if(curUserId.equals(item.getUser().getId())){
					favortid = item.getId();
					return favortid;
				}
			}
		}
		return favortid;
	}
}
