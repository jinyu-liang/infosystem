package com.ailk.ims.httpjson.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ailk.ims.util.ImsLogger;

/**
 * http://10.10.140.75:18084/com.cmcc.sh.boss.interfaces.bossforcrm <BR>
 * 请求导向/imscnsh/CN_SHMgntAction/commonHttpJson.go
 * 
 * @author xieqr
 * @Date 2012-7-2
 */
public class ForwardServlet extends HttpServlet
{	
	private static final ImsLogger imsLogger = new ImsLogger(ForwardServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
    	long start = System.currentTimeMillis();
        this.getServletContext().getRequestDispatcher("/ims-intfsh/imscnsh/CN_SHMgntAction/commonHttpJson.go")
                .forward(request, response);
        imsLogger.info(start,"##################exit ForwardServlet###################");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        doGet(request, response);
    }
}
