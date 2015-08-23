package com.group.core.common;

/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

import com.group.zsxm.entity.JSONTree;

import net.sf.json.JSONObject;


/**
 * @author xufeng
 * 
 */
public class TreeFactory {
	private String rootId;
	
	private String rootText;
	
	private Boolean rootOpen = true;
	
	private String pdm = "1";//处于第一级循环的pdm,默认是1
	
	private Boolean isDisabledCheck = true; 
	
	private Boolean hasRootNode = true;
	
	private Boolean useStyle = true;
	public String create(List<TreeBean> menus) {
		return tree2json(menus);
	}

	@SuppressWarnings("unchecked")
	private String tree2json(List<TreeBean> treeBeans) {
		List<JSONTree> treeList = new ArrayList();
		List<UserData> userdataList = new ArrayList();
		
		List<JSONTree> jsonTreeList = new ArrayList();
		for (int i = 0; i < treeBeans.size(); i++) {
			TreeBean treeBean = treeBeans.get(i);
			if (treeBean.getPdm().equals(pdm)) {
				jsonTreeList.add(build(treeBean, findchild(treeBeans, treeBean, i + 1)));
			}
		}
		
		
		JSONTree jsonTreeRoot = new JSONTree();
		jsonTreeRoot.setId(this.rootId!=null && !this.rootId.equals("") ? this.rootId : "root");
		jsonTreeRoot.setText(this.rootText!=null && !this.rootText.equals("") ? this.rootText : "选择数据");
		jsonTreeRoot.setOpen("1");
		userdataList.add(new UserData("bz","0"));
		userdataList.add(new UserData("id","0"));
		jsonTreeRoot.setUserdata(userdataList);
		//if(this.rootOpen)jsonTreeRoot.setOpen("1");
		jsonTreeRoot.setItem(jsonTreeList);
		treeList.add(jsonTreeRoot);
		 
		JSONTree jsonTree = new JSONTree();
		jsonTree.setId("0");
		if(getHasRootNode()){
			jsonTree.setItem(treeList);
		}else{
			jsonTree.setItem(jsonTreeList);
		}
		return JSONObject.fromBean(jsonTree).toString().replaceAll(",\"item\"\\:\\[\\]","").replaceAll(",\"checked\":\"\"","").replaceAll(",\"open\":\"\"","").replaceAll(",\"text\":\"\"", "").replaceAll(",\"disabled\":\"\"","");
	}

	private JSONTree build(TreeBean treeBean, List<JSONTree> childTreeList) {
		JSONTree jsonTree = new JSONTree();
		jsonTree.setId(treeBean.getDm());
		//2009-10-03 王晓杰改，去除代码
		//jsonTree.setText(treeBean.getDm() +" "+treeBean.getMc());
		jsonTree.setText(treeBean.getMc());
		
		List<UserData> userdataList = new ArrayList();
		userdataList.add(new UserData("bz",treeBean.getBz()==null?"":treeBean.getBz()));
		userdataList.add(new UserData("id",treeBean.getId()==null?"":treeBean.getId()));
		jsonTree.setUserdata(userdataList);
		
		if(!treeBean.getIsc().equals("0")){
			if(this.isDisabledCheck){
				jsonTree.setChecked("1");
				jsonTree.setDisabled("true");
			}else{
				jsonTree.setChecked("1");
			}
			if(this.useStyle)
			jsonTree.setStyle("color:blue;");
		}
		
		jsonTree.setItem(childTreeList);
		return jsonTree;
	}

	@SuppressWarnings("unchecked")
	private List<JSONTree> findchild(List<TreeBean> treeBeans, TreeBean treeBean,
			int index) {
		List<JSONTree> jsonTreeList = new ArrayList();
		if (haschild(treeBeans, treeBean, index)) {
			int t_len = treeBeans.size();
			for (int i = index; i < t_len; i++) {
				TreeBean m  = treeBeans.get(i);
				if (m.getPdm().equals(treeBean.getDm())) {
					jsonTreeList.add(build(m, findchild(treeBeans, m, i + 1)));
				}
			}
		}
		return jsonTreeList;
	}

	private boolean haschild(List<TreeBean> treeBeans, TreeBean treeBean, int index) {
		if (treeBeans.size() > index) {
			return treeBean.getDm().equals(treeBeans.get(index).getPdm());
		}
		return false;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getRootText() {
		return rootText;
	}

	public void setRootText(String rootText) {
		this.rootText = rootText;
	}

	public Boolean getRootOpen() {
		return rootOpen;
	}

	public void setRootOpen(Boolean rootOpen) {
		this.rootOpen = rootOpen;
	}

	public String getPdm() {
		return pdm;
	}

	public void setPdm(String pdm) {
		this.pdm = pdm;
	}

	public Boolean getIsDisabledCheck() {
		return isDisabledCheck;
	}

	public void setIsDisabledCheck(Boolean isDisabledCheck) {
		this.isDisabledCheck = isDisabledCheck;
	}

	public Boolean getHasRootNode() {
		return hasRootNode;
	}

	public void setHasRootNode(Boolean hasRootNode) {
		this.hasRootNode = hasRootNode;
	}

	public Boolean getUseStyle() {
		return useStyle;
	}

	public void setUseStyle(Boolean useStyle) {
		this.useStyle = useStyle;
	}
	
}
