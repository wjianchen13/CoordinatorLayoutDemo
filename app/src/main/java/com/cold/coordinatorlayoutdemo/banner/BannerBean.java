package com.cold.coordinatorlayoutdemo.banner;

/*****************************************************************************************
 * banner 数据结构
 * @author ChenJie
 *
 ****************************************************************************************/
public class BannerBean {
	private String mImgUrl;
	private String mWebUrl;
	private int rId;

	public String getImgUrl() {
		return mImgUrl;
	}
	public void setImgUrl(String mImgUrl) {
		this.mImgUrl = mImgUrl;
	}
	public String getWebUrl() {
		return mWebUrl;
	}
	public void setWebUrl(String mWebUrl) {
		this.mWebUrl = mWebUrl;
	}
	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}
}
