package org.bangaru.ws.rest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bangaru.mysql.dao.JavaRestJNDI;


@Path(value = "/webservice")
public class BasicRESTAnnotation {
	private static final String jerseyApiVersion = "1.17";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<h3>Java RESTful WebService<h3>";
	}

	@GET
	@Path(value = "/jersey/version")
	@Produces(MediaType.TEXT_HTML)
	public String returnAPIVersion(){
		return "<h1>Java RESTful Jersey version used is :<h1> "+jerseyApiVersion;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path(value = "/dbTime")
	public String getDateTimeinMySqlDatabase(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String myString = null;
		String dbReturnString = null;

		try {
			pst = JavaRestJNDI.getConnectionFromMySqlDS().getConnection().prepareStatement("SELECT curdate(),curtime() as DATETIME");
			rs = pst.executeQuery();

			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbReturnString = "<h1>Current Time at DB end is :<h1> "+myString;
		return dbReturnString;
	}
}
