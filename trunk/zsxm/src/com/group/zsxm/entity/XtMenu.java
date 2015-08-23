package com.group.zsxm.entity;

public class XtMenu  {
	
	private String menuid;

	private String pmenuid;

	private String title;

	private String image;

	private String action;

	private String target;

	private String status;

	private static final long serialVersionUID = 1L;

	public XtMenu() {
		super();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getPmenuid() {
		return pmenuid;
	}

	public void setPmenuid(String pmenuid) {
		this.pmenuid = pmenuid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
