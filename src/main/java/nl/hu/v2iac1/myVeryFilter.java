package nl.hu.v2iac1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class myVeryFilter implements Filter {
 
	 private ArrayList<String> urlList;
     
	    public void destroy() {
	    }
	 
	    public void doFilter(ServletRequest req, ServletResponse res,
	            FilterChain chain) throws IOException, ServletException {
	    	
	    	Configuration configuration = new Configuration();
	    	String ses2 = configuration.getValue(Configuration.Key.SES2);
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String url = request.getServletPath();
	        boolean allowedRequest = false;
	        
	        if(request.getSession().getAttribute("user") == null){
	        	 response.sendRedirect("http://www.google.nl");
	        }
	        else if(request.getSession().getAttribute("user").equals(ses2)) {
	            allowedRequest = true;
	            request.getSession().setAttribute("user", null);
	        }
	             
	        if (!allowedRequest) {
	            HttpSession session = request.getSession(false);
	            if (null == session) {
	                response.sendRedirect("http://www.google.nl");
	            }
	        }
	         
	        chain.doFilter(req, res);
	    }
	 
	    public void init(FilterConfig config) throws ServletException {
//	        String urls = config.getInitParameter("avoid-urls");
//	        StringTokenizer token = new StringTokenizer(urls, ",");
//	 
//	        urlList = new ArrayList<String>();
//	 
//	        while (token.hasMoreTokens()) {
//	            urlList.add(token.nextToken());
//	 
//	        }
	    }
	}

