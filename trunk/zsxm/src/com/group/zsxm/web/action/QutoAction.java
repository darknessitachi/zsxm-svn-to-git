package com.group.zsxm.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.group.core.common.Message;
import com.group.core.common.TreeBean;
import com.group.zsxm.entity.JSONTree;
import com.group.zsxm.service.QutoService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/xtmanager/quto.jsp"),
		@Result(name = "menu", value = "/WEB-INF/jsp/xtmanager/qutoMenu.jsp") })
public class QutoAction extends BaseAction {

	private static final long serialVersionUID = -7285331558641230468L;

	
	@Autowired
	@Qualifier("qutoService")
	private QutoService qutoService;

	private String userid;

	private String username;

	private String menuIds;
	
	public String execute() {
		return Action.INPUT;
	}

	public String doMenu() {
		username = qutoService.getUserNameById(userid);
		return "menu";
	}
	
	@SuppressWarnings("unchecked")
	public void doLoadMenu() {
		List menus = qutoService.getMenuByUserId(userid);
		TreeFactory tf = new TreeFactory();
		tf.setPdm("00");
		tf.setIsDisabledCheck(false);
		tf.setRootText("菜单权限列表");
		renderText(tf.create(menus));
	}
	
	public void doSaveMenu() {
		Message message = new Message();
		try {
			qutoService.saveMenuByUserId(userid, menuIds);
			message.setCode("1");
			message.setText("分配成功！");
		} catch (Exception e) {
			message.setCode("-1");
			message.setText(e.getMessage());
		}
		renderJson(message);
	}
	
	public void getAlluser(){
		Map map = new HashMap();
		map.put("data",qutoService.getAllUser());
		renderJson(map);
	}
	
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

class TreeFactory {
	private String rootId;

	private String rootText;

	private Boolean rootOpen = true;

	private String pdm = "1";// 处于第一级循环的pdm,默认是1

	private Boolean isDisabledCheck = true;

	private Boolean hasRootNode = true;

	private Boolean useStyle = true;

	public String create(List<TreeBean> menus) {
		return tree2json(menus);
	}

	@SuppressWarnings("unchecked")
	private String tree2json(List<TreeBean> treeBeans) {
		List<JSONTree> treeList = new ArrayList();

		List<JSONTree> jsonTreeList = new ArrayList();
		for (int i = 0; i < treeBeans.size(); i++) {
			TreeBean treeBean = treeBeans.get(i);
			if (treeBean.getPdm().equals(pdm)) {
				jsonTreeList.add(build(treeBean, findchild(treeBeans, treeBean,
						i + 1)));
			}
		}

		JSONTree jsonTreeRoot = new JSONTree();
		jsonTreeRoot
				.setId(this.rootId != null && !this.rootId.equals("") ? this.rootId
						: "root");
		jsonTreeRoot
				.setText(this.rootText != null && !this.rootText.equals("") ? this.rootText
						: "选择菜单");
		jsonTreeRoot.setOpen("1");
		// if(this.rootOpen)jsonTreeRoot.setOpen("1");
		jsonTreeRoot.setItem(jsonTreeList);
		treeList.add(jsonTreeRoot);

		JSONTree jsonTree = new JSONTree();
		jsonTree.setId("0");
		if (getHasRootNode()) {
			jsonTree.setItem(treeList);
		} else {
			jsonTree.setItem(jsonTreeList);
		}
		return JSONObject.fromBean(jsonTree).toString().replaceAll(
				",\"item\"\\:\\[\\]", "").replaceAll(",\"checked\":\"\"", "")
				.replaceAll(",\"open\":\"\"", "").replaceAll(",\"text\":\"\"",
						"").replaceAll(",\"disabled\":\"\"", "");
	}

	private JSONTree build(TreeBean treeBean, List<JSONTree> childTreeList) {
		JSONTree jsonTree = new JSONTree();
		jsonTree.setId(treeBean.getDm());
		jsonTree.setText(treeBean.getDm() + " " + treeBean.getMc());
		if (!treeBean.getIsc().equals("0")) {
			if (this.isDisabledCheck) {
				jsonTree.setChecked("1");
				jsonTree.setDisabled("true");
			} else {
				if (childTreeList.size() == 0)
					jsonTree.setChecked("0");
			}
			if (this.useStyle)
				jsonTree.setStyle("color:#ffa500;");
		}

		jsonTree.setItem(childTreeList);
		return jsonTree;
	}

	@SuppressWarnings("unchecked")
	private List<JSONTree> findchild(List<TreeBean> treeBeans,
			TreeBean treeBean, int index) {
		List<JSONTree> jsonTreeList = new ArrayList();
		if (haschild(treeBeans, treeBean, index)) {
			int t_len = treeBeans.size();
			for (int i = index; i < t_len; i++) {
				TreeBean m = treeBeans.get(i);
				if (m.getPdm().equals(treeBean.getDm())) {
					jsonTreeList.add(build(m, findchild(treeBeans, m, i + 1)));
				}
			}
		}
		return jsonTreeList;
	}

	private boolean haschild(List<TreeBean> treeBeans, TreeBean treeBean,
			int index) {
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
