package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import webSocket.MyWebSocket;

public class CountListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("³É¹¦");
		HttpSession session=event.getSession();
		ServletContext application=session.getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null) {
			num=(Integer)application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);
		for(MyWebSocket item:MyWebSocket.set) {
			item.sendMessage(String.valueOf(num));
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("Ê§Ð§");
		HttpSession session=event.getSession();
		ServletContext application=session.getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null) {
			num=(Integer)application.getAttribute("num");
		}
		num--;
		application.setAttribute("num", num);
		for(MyWebSocket item:MyWebSocket.set) {
			item.sendMessage(String.valueOf(num));
		}
	}

}
