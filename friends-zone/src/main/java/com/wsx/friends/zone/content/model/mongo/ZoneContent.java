package com.wsx.friends.zone.content.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.wsx.friends.core.model.BaseBean;



@Document(collection="zone_content")
public class ZoneContent extends BaseBean {

	private static final long serialVersionUID = 8744884716160248311L;

	private String dataId;
	private String title;
	private String content;
	private String puser;
	private String pdate;
	
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPuser() {
		return puser;
	}
	public void setPuser(String puser) {
		this.puser = puser;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
}
