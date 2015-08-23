package com.group.zsxm.web.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.group.core.Constants;

@Controller
public class DwrSchedulePush  implements ServletContextAware {

	private ServletContext servletContext;
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletContext = arg0;

	}

	public int NotifyInfo (String userid,HashMap map) {
        
        ServerContext ctx = ServerContextFactory.get(servletContext );
        
        try{
        if (ctx != null) {

              ScriptBuffer script = new ScriptBuffer();
              script.appendScript("showschedule(")
               .appendData(map)
               .appendScript(");");
            
            // Push script out to clients viewing the page
            Collection<ScriptSession> sessions = 
                      ctx.getScriptSessionsByPage("/login!login.do");
            
            Collection<ScriptSession> sessions2 = 
                      ctx.getAllScriptSessions ();
            //Iterator iterator = sessions.iterator();

            for (ScriptSession session : sessions2) {
            	String name = String.valueOf(session.getAttribute("userid"));
            	if(name != null && userid.equals(name)){
            		session.addScript(script);
            	}
            }        
        }
        }catch (Exception e) {
        	e.printStackTrace();
			// TODO: handle exception
		}
        return 0;
    }

}
