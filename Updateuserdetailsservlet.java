package com.GoogleAEdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Updateuserdetailsservlet extends HttpServlet {

	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	@Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {

	    
		String uusername = request.getParameter("uuname");
		String uDOB = request.getParameter("udob");
		String uUserGender =request.getParameter("ugender");
		int uUserAge =Integer.parseInt(request.getParameter("uage"));
		
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String htmlresponse2="<html>";
		htmlresponse2 +="<h1>The user name is " + uusername+ "<br/>" + " The user DOB is :" + uDOB +"<br/>";
		htmlresponse2 +="The user GENDER is" + uUserGender +"<br/>"+"The user UserAge is " + uUserAge +"<br/>";
		htmlresponse2 +="</html>";
		out.println(htmlresponse2);
		
		
		/*out.println("The user name is " + uusername);
		out.println("The user DOB is " + uDOB);
		out.println("The user GENDER is " + uUserGender);
		out.println("The user UserAge is " + uUserAge);*/
		

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();




//Creating entities and setting properties

Entity updatedUserdetails = new Entity("UpdatedUser");
updatedUserdetails.setProperty("UpdatedName", uusername);
updatedUserdetails.setProperty("UpdatedDOB", uDOB);
updatedUserdetails.setProperty("UpdatedGender",uUserGender );
updatedUserdetails.setProperty("UpdatedUserage", uUserAge);

datastore.put(updatedUserdetails);



	}

}
