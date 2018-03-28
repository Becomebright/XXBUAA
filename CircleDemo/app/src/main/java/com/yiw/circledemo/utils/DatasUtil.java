package com.yiw.circledemo.utils;

import com.yiw.circledemo.bean.CircleItem;
import com.yiw.circledemo.bean.CircleJson;
import com.yiw.circledemo.bean.CommentItem;
import com.yiw.circledemo.bean.FavortItem;
import com.yiw.circledemo.bean.PhotoInfo;
import com.yiw.circledemo.bean.Sendjson;
import com.yiw.circledemo.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * 
* @ClassName: DatasUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:16:21 
*
 */
public class DatasUtil {
	public static int flag=1;
	public static final String[] CONTENTS = { "",
			"北京航空航天大学（Beihang University），简称北航，由中华人民共和国工业和信息化部直属，中央直管副部级建制，位列“双一流”、“211工程”、“985工程”，入选“珠峰计划”、“2011计划”、“111计划”、“卓越工程师教育培养计划”，为国际宇航联合会、“中欧精英大学联盟”、“中国西班牙大学联盟”、“中俄工科大学联盟”成员，是全国第一批16所重点高校之一、80年代恢复学位制度后全国第一批设立研究生院的22所高校之一，也是新中国第一所航空航天高等学府。",
			//"今天是个好日子，http://www.ChinaAr.com;一个不错的VR网站,18123456789,",
			//"呵呵，http://www.ChinaAr.com;一个不错的VR网站,18123456789,",
			//"只有http|https|ftp|svn://开头的网址才能识别为网址，正则表达式写的不太好，如果你又更好的正则表达式请评论告诉我，谢谢！",
			"sony大法好",
			//"哈哈哈哈",
			//"图不错",
			"今天我过生日！" };
	/*public static final String[] PHOTOS = {
			"http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg",
			"http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg",
			"http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg",
			"http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg",
			"http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg",
			"http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg",
			"http://f.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fecc3e83ef586034a85edf723d.jpg",
			"http://cdn.duitang.com/uploads/item/201309/17/20130917111400_CNmTr.thumb.224_0.png",
			"http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg",
			"http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg",
			"http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg",
			"http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg",
			"http://pic6.nipic.com/20100330/4592428_113348099353_2.jpg",
			"http://pic9.nipic.com/20100917/5653289_174356436608_2.jpg",
			"http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
			"http://pic1.nipic.com/2008-12-09/200812910493588_2.jpg",
			"http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg" };*/
	public static final String[] HEADIMG = {
			"http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg",
			"http://www.feizl.com/upload2007/2014_06/1406272351394618.png",
			"http://v1.qzone.cc/avatar/201308/30/22/56/5220b2828a477072.jpg%21200x200.jpg",
			"http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg",
			"http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg!200x200.jpg",
			"http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg",
			"http://img.woyaogexing.com/touxiang/nv/20140212/9ac2117139f1ecd8%21200x200.jpg",
			"http://p1.qqyou.com/touxiang/uploadpic/2013-3/12/2013031212295986807.jpg"};

	public static List<User> users = new ArrayList<User>();
	public static List<PhotoInfo> PHOTOS = new ArrayList<>();
	/**
	 * 动态id自增长
	 */
	private static int circleId = 0;
	/**
	 * 点赞id自增长
	 */
	private static int favortId = 0;
	/**
	 * 评论id自增长
	 */
	private static int commentId = 0;
	public static  User curUser ;
	static {
		User user1 = new User("2", "jzr", HEADIMG[1]);
		User user2 = new User("3", "dsz", HEADIMG[2]);
		User user3 = new User("4", "hhx", HEADIMG[3]);
		User user4 = new User("5", "grh", HEADIMG[4]);
		User user5 = new User("6", "wqp", HEADIMG[5]);
		User user6 = new User("7", "sy", HEADIMG[6]);
		User user7 = new User("8", "lzh", HEADIMG[7]);

		users.add(curUser);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);

		PhotoInfo p1 = new PhotoInfo();
		p1.url = "https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=be3496ddf21fbe090853cb460a096756/e850352ac65c103896bc6769b2119313b17e895f.jpg";
		p1.w = 640;
		p1.h = 792;

		PhotoInfo p2 = new PhotoInfo();
		p2.url = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D11%2C0%2C577%2C381%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=6f20cf65ab8b87d6440df15f3a391914/622762d0f703918f7cd0409e5b3d269759eec465.jpg";
		p2.w = 640;
		p2.h = 792;

		PhotoInfo p3 = new PhotoInfo();
		p3.url = "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=3041cb1f580fd9f9b41a5d3b4444bf4f/a9d3fd1f4134970aebf7f44c9ccad1c8a6865dc9.jpg";
		p3.w = 950;
		p3.h = 597;

		PhotoInfo p4 = new PhotoInfo();
		p4.url = "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=6304d9a9583d26973ade000f3492d99e/bd315c6034a85edf4e591a4040540923dc5475f8.jpg";
		p4.w = 533;
		p4.h = 800;

		PhotoInfo p5 = new PhotoInfo();
		p5.url = "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=517588bd346d55fbd1cb7e740c4b242f/d8f9d72a6059252da3c8f9673d9b033b5ab5b9f2.jpg";
		p5.w = 700;
		p5.h = 467;

		PhotoInfo p6 = new PhotoInfo();
		p6.url = "https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=e69bdd608e44ebf8797c6c6db890bc4f/b3119313b07eca80e8c3dd84982397dda044838b.jpg";
		p6.w = 700;
		p6.h = 467;

		PhotoInfo p7 = new PhotoInfo();
		p7.url = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=899ea495aeefce1bfe26c098ce3898bb/a50f4bfbfbedab641a3eaaa3fe36afc379311e00.jpg";
		p7.w = 1024;
		p7.h = 640;

		PhotoInfo p8 = new PhotoInfo();
		p8.url = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=28d01a3d5cfbb2fb202650402e234bc1/8cb1cb1349540923ac6a711e9b58d109b3de494a.jpg";
		p8.w = 1024;
		p8.h = 768;

		PhotoInfo p9 = new PhotoInfo();
		p9.url = "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=420a33719c82d158af8f51e3e16372bd/2f738bd4b31c870132880779277f9e2f0708ff77.jpg";
		p9.w = 1024;
		p9.h = 640;

		PhotoInfo p10 = new PhotoInfo();
		p10.url = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=46266ff98b26cffc7d27b7e0d86821f5/7af40ad162d9f2d3a64f3d8fa0ec8a136327cc70.jpg";
		p10.w = 1024;
		p10.h = 768;

		PHOTOS.add(p1);
		PHOTOS.add(p2);
		PHOTOS.add(p3);
		PHOTOS.add(p4);
		PHOTOS.add(p5);
		PHOTOS.add(p6);
		PHOTOS.add(p7);
		PHOTOS.add(p8);
		PHOTOS.add(p9);
		PHOTOS.add(p10);
	}

	public static List<CircleItem> createCircleDatas() {
		//Sendjson.jsonPost("http://10.19.116.193:5000/post/get",curUser);
		Sendjson sendjson;
		if(flag==1){
			sendjson=new Sendjson("http://123.206.27.121:5000/post/get",curUser);
		}
		else{
			sendjson=new Sendjson("http://123.206.27.121:5000/market/get",curUser);
		}
		//Sendjson sendjson=new Sendjson("http://10.19.116.193:5000/post/get",curUser);
		Thread thread=new Thread(sendjson);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<CircleItem> circleDatas = new ArrayList<CircleItem>();
		if(sendjson.gradeList!=null){
			for (int i = sendjson.gradeList.size() - 1; i>=0; i--) {
				CircleJson grade = sendjson.gradeList.get(i);
				CircleItem item = new CircleItem(grade);
				circleDatas.add(item);
			}
		}

//		for(CircleJson grade : sendjson.gradeList){
//			CircleItem item = new CircleItem(grade,getUser());
//			circleDatas.add(item);
//		}


//		List<CircleItem> circleDatas = new ArrayList<CircleItem>();
//		for (int i = 0; i < 15; i++) {
//			CircleItem item = new CircleItem();
//			User user = getUser();
//			item.setId(String.valueOf(circleId++));
//			item.setUser(user);
//			item.setContent(getContent());
//			item.setCreateTime("2月28日");
//
//			item.setFavorites(createFavortItemList());
//			item.setComments(createCommentItemList());
//			int type = getRandomNum(10) % 2;
//			if (type == 0) {
//				item.setType("1");// 链接
//				item.setLinkImg("http://pics.sc.chinaz.com/Files/pic/icons128/2264/%E8%85%BE%E8%AE%AFQQ%E5%9B%BE%E6%A0%87%E4%B8%8B%E8%BD%BD1.png");
//				item.setLinkTitle("点击参与班级自习活动");
//			} else if(type == 1){
//				item.setType("2");// 图片
//				item.setPhotos(createPhotos());
//			}else {
//				item.setType("3");// 视频
//				String videoUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.mp4";
//                String videoImgUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.jpg";
//				item.setVideoUrl(videoUrl);
//                item.setVideoImgUrl(videoImgUrl);
//			}
//			circleDatas.add(item);
//		}

		return circleDatas;
	}

	public static User getUser() {
		return users.get(getRandomNum(users.size()));
	}

	public static String getContent() {
		return CONTENTS[getRandomNum(CONTENTS.length)];
	}

	public static int getRandomNum(int max) {
		Random random = new Random();
		int result = random.nextInt(max);
		return result;
	}

	public static List<PhotoInfo> createPhotos() {
		List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
		int size = getRandomNum(PHOTOS.size());
		if (size > 0) {
			if (size > 9) {
				size = 9;
			}
			for (int i = 0; i < size; i++) {
				PhotoInfo photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
				if (!photos.contains(photo)) {
					photos.add(photo);
				} else {
					i--;
				}
			}
		}
		return photos;
	}

	public static List<FavortItem> createFavortItemList() {
		int size = getRandomNum(users.size());
		List<FavortItem> items = new ArrayList<FavortItem>();
		List<String> history = new ArrayList<String>();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				FavortItem newItem = createFavortItem();
				String userid = newItem.getUser().getId();
				if (!history.contains(userid)) {
					items.add(newItem);
					history.add(userid);
				} else {
					i--;
				}
			}
		}
		return items;
	}

	public static FavortItem createFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(getUser());
		return item;
	}
	
	public static FavortItem createCurUserFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(curUser);
		return item;
	}

	public static List<CommentItem> createCommentItemList() {
		List<CommentItem> items = new ArrayList<CommentItem>();
		int size = getRandomNum(10);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				items.add(createComment());
			}
		}
		return items;
	}

	public static CommentItem createComment() {
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent("这样好");
		User user = getUser();
		item.setUser(user);
		if (getRandomNum(10) % 2 == 0) {
			while (true) {
				User replyUser = getUser();
				if (!user.getId().equals(replyUser.getId())) {
					item.setToReplyUser(replyUser);
					break;
				}
			}
		}
		return item;
	}
	
	/**
	 * 创建发布评论
	 * @return
	 */
	public static CommentItem createPublicComment(String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		return item;
	}
	
	/**
	 * 创建回复评论
	 * @return
	 */
	public static CommentItem createReplyComment(User replyUser, String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		item.setToReplyUser(replyUser);
		return item;
	}
	
	
	public static CircleItem createVideoItem(String videoUrl, String imgUrl){
		CircleItem item = new CircleItem();
		item.setId(String.valueOf(circleId++));
		item.setUser(curUser);
		//item.setContent(getContent());
		item.setCreateTime("12月24日");

		//item.setFavorites(createFavortItemList());
		//item.setComments(createCommentItemList());
        item.setType("3");// 图片
        item.setVideoUrl(videoUrl);
        item.setVideoImgUrl(imgUrl);
		return item;
	}
}
