package com.demo.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.demo.rest.model.Message;
import com.demo.rest.service.MessageService;

@Path("/messages")

public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages( ) {
		
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/query")//http://localhost:8080/JerseyDemo/api/messages/query;id=110
	@Produces(MediaType.TEXT_PLAIN)	
	public String queryMessage(@QueryParam("id") long id,@HeaderParam("user-agent") String name) {
		return id+" is used for query header "+ name;
	}
	
	@GET
	@Path("/matrix")//http://localhost:8080/JerseyDemo/api/messages/matrix;name=110
	@Produces(MediaType.TEXT_PLAIN)	
	public String matrixMessage(@MatrixParam("name") String name, @CookieParam("country") String country) {
		return " Matrix is used for  name "+ name+"\n Cookie value for country:"+country;
	}
	
	@GET
	@Path("/security")//http://localhost:8080/JerseyDemo/api/messages/matrix;name=110
	@Produces(MediaType.TEXT_PLAIN)	
	public String securityMessage(@Context  SecurityContext securityContext) {
		return " Security is : "+securityContext.isSecure();
	}
	
	@GET
	@Path("/context")
	@Produces(MediaType.TEXT_PLAIN)	
	public String contextMessage(@Context HttpHeaders httpheader, @Context UriInfo uriinfo) {
		return "Header is: "+httpheader.getRequestHeader("country")+""
				+ "\n AbsolutePath  is "+uriinfo.getAbsolutePath()+" Path is: "+uriinfo.getPath();
	}
	
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{id}")
	public String deletemessage(@PathParam("id") long id) {
		return messageService.deletedMessage(id);
	}
	
	@GET
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("{id}")
	public Response getMessage(@PathParam("id") long id, @Context UriInfo uriinfo) {
		
		Message message = messageService.getMessage(id);
		String uriinfoString = uriinfo.getBaseUriBuilder().
				path(MessageResource.class).
				path(Long.toString(id)).build().
				toString();
		message.addLink(uriinfoString, "self");
		return Response.status(Status.FOUND).entity(message).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message msg) {
		Message message =messageService.addMessage(msg);
		return Response.status(Status.CREATED).entity(message).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Message updateMessage(@PathParam("id") long id,Message msg) {
		msg.setId(id);
		return messageService.updateMessage(msg);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Message deleteMessage(@PathParam("id") long id) {
		return messageService.deleteMessage(id);
	}
	
	

}
