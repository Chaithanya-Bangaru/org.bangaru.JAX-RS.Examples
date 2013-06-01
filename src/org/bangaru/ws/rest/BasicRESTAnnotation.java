package org.bangaru.ws.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path(value = "/rs/webservice")
public class BasicRESTAnnotation {
	private static final String jerseyApiVersion = "1.17";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<h3>Java RESTful WebService<h3>";
	}
	
	@GET
	@Path(value = "jersey/version")
	@Produces(MediaType.TEXT_HTML)
	public String returnAPIVersion(){
		return "<h1>Java RESTful Jersey version used is :<h1> "+jerseyApiVersion;
	}
}
