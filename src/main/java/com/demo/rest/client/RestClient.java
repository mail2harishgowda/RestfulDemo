package com.demo.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demo.rest.model.Employee;

@Path("/api")
public class RestClient {
	
	  @GET
	  @Path("xml")
	  @Produces( MediaType.TEXT_XML)
	  public String sayPlainTextHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }
	  
	  @GET
	  @Produces( MediaType.TEXT_HTML)
	  @Path("ui")
	  public String sayPlainHtmlHello() {
	    return "<html><body><b>html page</b></body></html";
	  }
	  
	  @GET
	  @Produces( MediaType.APPLICATION_JSON)
	  @Path("json")
	  public Employee sayPlainJsonHello() {
		  Employee emp = new Employee("Harish",1);
	    return emp;
	  }


}
