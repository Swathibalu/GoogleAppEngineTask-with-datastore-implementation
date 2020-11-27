package com.GoogleAEdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;



public class HelloAppEngine extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    
	String username = request.getParameter("uname");
	String DOB = request.getParameter("dob");
	String UserGender =request.getParameter("gender");
	int UserAge =Integer.parseInt(request.getParameter("age"));
	
	
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	String htmlresponse="<html>";
	htmlresponse +="<h1>The UserName is:  " + username+ "<br/>" + " The User DOB is :" + DOB +"<br/>";
	htmlresponse +="The UserGender is" + UserGender +"<br/>"+"The UserAge is " + UserAge +"<br/>";
	htmlresponse +="</html>";
	out.println(htmlresponse);
	
	
	/*out.println("The user name is " + username);
	out.println("The user DOB is " + DOB);
	out.println("The user GENDER is " + UserGender);
	out.println("The user UserAge is " + UserAge);*/
	
	//Datastore instantiation
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	 
	
	
	//Creating entities and setting properties
	
	int k=130;
	
	Transaction tx=datastore.beginTransaction();
	try {
	Entity Userdetails = new Entity("User",k++);
	Userdetails.setProperty("Name", username);
	Userdetails.setProperty("DOB", DOB);
	Userdetails.setProperty("Gender",UserGender );
	Userdetails.setProperty("Userage", UserAge);

	datastore.put(Userdetails);
	tx.commit();
	}
	finally
	{
		if(tx.isActive())
		{
			tx.rollback();
		}
	}
	//creating a key
		Key key=KeyFactory.createKey("User", 124);
		
		
	// Creating ancester key
		
		Key key1=new KeyFactory.Builder("User","GreatGrandpa").addChild("User", "Grandpa").addChild("User", "Dad").getKey();
		
		System.out.println("The key1 value :"+ key1);
	
	System.out.println("The key value :"+ key);
	
	
	//retrieving of entity user
	

	/*try {
		Entity e5 = datastore.get(key);
		System.out.println("The entity value of e5:"+ e5);
	} catch (EntityNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
	
	//deleting an entity user
	//datastore.delete(key);
	
	
	
	// The Query interface assembles a query
	Query q = new Query("User");

	//Query q = new Query("User").addFilter("Userage", FilterOperator.EQUAL,"21" ).addSort("Name", Query.SortDirection.ASCENDING);
	
	
	// PreparedQuery contains the methods for fetching query results
	// from the datastore
	PreparedQuery pq = datastore.prepare(q);

	for (Entity result : pq.asIterable()) {
	  String name = result.getProperty("Name").toString();
	  String dob = result.getProperty("DOB").toString();
	  String gender =result.getProperty("Gender").toString();
	  String userage=result.getProperty("Userage").toString();
	  System.out.println("Fetching reults");
	  System.out.println(name+"    "+dob+"   "+gender+"  "+userage);
	}
	
	
	
	
    response.setCharacterEncoding("UTF-8");

    

  }
}