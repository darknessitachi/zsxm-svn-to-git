package com.group.zsxm.web.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Controller;

import com.group.core.Constants;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.MessageService;

@Controller
public class DwrDeclarePush {
    
    private MessageService messageService;
    
 
    @SuppressWarnings("unchecked")
    public void getMessage(String users,HashMap map)
    {
	try{		
            WebContext wctx = WebContextFactory.get();
            String contextpath = wctx.getHttpServletRequest().getContextPath();
            String currentPage = contextpath+"/login!login.do";
            ScriptBuffer script = new ScriptBuffer();
            //Map<String,Boolean> map =  declareService.getMessage(); 
            script.appendScript("showmessage(")
               .appendData(map)
               .appendScript(");");
            Collection<ScriptSession> sessions = new HashSet<ScriptSession>();

          
            sessions.addAll(wctx.getScriptSessionsByPage(currentPage));
          //  sessions.addAll(wctx.getScriptSessionsByPage(contextpath+"/login!doGlCheckLogin.do"));
            
            for (ScriptSession session : sessions) {
            	//查询与消息接收者相符的客户端页面,并输出消息内容
        	
                String xuser = (String)session.getAttribute("username");
                //System.out.println(xuser+"   "+users);
                if(xuser != null && users.indexOf(xuser)>-1){
                	session.addScript(script);
                }
            }
            //Collection pages = wctx.getScriptSessionsByPage(currentPage);
            //for (Iterator it = pages.iterator(); it.hasNext();)
            //{
            //    ScriptSession otherSession = (ScriptSession) it.next();
            //    otherSession.addScript(script);
            //}
        }catch(Exception e){
        	e.printStackTrace();
            throw new BusException(e.getMessage());
        }
    }

    
    
    public void setScriptSession(){
    	try{
    		
    		WebContext wctx = WebContextFactory.get();
    		//System.out.println(wctx.getCurrentPage());
    		ScriptSession session = wctx.getScriptSession();
    		Xtuser xtuser = (Xtuser)wctx.getSession().getAttribute(Constants.USER_SESSION_KEY);
    		ScriptSession oldSession = getCurrentSession(xtuser.getLoginname(),wctx);
            if(oldSession != null && oldSession != session){
                oldSession.invalidate();
                oldSession = null;
            }
    		session.setAttribute("username", xtuser.getLoginname());
    		session.setAttribute("userid", xtuser.getUserid());
        }catch(Exception e){
            throw new BusException(e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
	private ScriptSession getCurrentSession(String user,WebContext wctx){
        String currentPage = wctx.getCurrentPage();
        ScriptSession xSession = null;
        Collection<ScriptSession> sessions = new HashSet<ScriptSession>();
        sessions.addAll(wctx.getScriptSessionsByPage(currentPage));
        for (ScriptSession session : sessions) {
            //查询与消息接收者相符的客户端页面,并输出消息内容
            String xuser = (String)session.getAttribute("username");
            if(xuser != null && xuser.equals(user)){
                xSession = session;
            }
        }
        return xSession;
    }

    public MessageService getMessageService() {
        return messageService;
    }
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    


}
