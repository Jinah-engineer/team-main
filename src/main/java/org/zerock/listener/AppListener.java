package org.zerock.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class BoardListener
 *
 */
public class AppListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AppListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	application.setAttribute("appRoot", application.getContextPath());
    	
    	// aws s3 bucket 주소
    	
    	// 1:1 문의글 관련 파일이 저장되는 AWS s3 bucket 주소
    	String bucketUrl1 = "https://swteam1.s3.ap-northeast-2.amazonaws.com/help/";
    	application.setAttribute("askImgRoot", bucketUrl1);

    	String bucketUrl2 = "https://swteam1.s3.ap-northeast-2.amazonaws.com/review/";
    	application.setAttribute("imgReviewRoot", bucketUrl2);
    	
    	String bucketUrl3 = "https://swteam1.s3.ap-northeast-2.amazonaws.com/storeIntroduceImg/";
    	application.setAttribute("imgIntroRoot", bucketUrl3);
    }
	
}








