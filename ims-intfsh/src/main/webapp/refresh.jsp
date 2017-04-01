
<%@page import="java.io.IOException"%>
<%@page import="java.util.Collection"%>

<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.cache.CacheManager"%>
<%@page import="com.ailk.ims.util.SpringUtil"%>
<html>
<body>
<%
// WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
// CacheManager cm = wac.getBean(CacheManager.class);
CacheManager cm = (CacheManager)SpringUtil.getBean(SpringUtil.getBeanNameByClass(CacheManager.class));
Collection<String> caches = cm.getCacheNames();
for (String name : caches) {
	out.println("<br>clean " + name);
	cm.getCache(name).clear();
}
out.println("cache clean success!");
%>
</body>
</html>
