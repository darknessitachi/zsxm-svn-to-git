package com.netwander.explib.entity;



import java.util.List;

/*dhtmlx的Jsontree
 * xufeng 
 * 2009.06.29
 */
public class JSONTree {
	private String id;
	
	private String text;
	
	private String open;
	
	private List<JSONTree> item;
	
	private String checked;//选中状态0,-1,1
	
	private String style;
	
	private String disabled;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<JSONTree> getItem() {
		return item;
	}

	public void setItem(List<JSONTree> item) {
		this.item = item;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	

	


}
