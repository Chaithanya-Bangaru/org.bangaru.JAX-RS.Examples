package org.bangaru.ws.rest;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bangaru.mysql.dao.JavaRestJNDI;
/**
 * 
 * @author ChaithanyaBangaru
 * Describes JAX-RS annotation Examples
 * TODO To Add JSON Response Examples
 * TODO Other param types like FormParam,HeaderParam,CookieParam,MatrixParam
 *
 */
@Path(value = "/webservice")
public class BasicRESTAnnotation {
	private static final String JERSEY_API_VERSION = "1.17";
	
	//Default GET call as Example
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<h3>Java RESTful WebService<h3>";
	}

	//Relative path as Example
	@GET
	@Path(value = "/jersey/version")
	@Produces(MediaType.TEXT_HTML)
	public String returnAPIVersion(){
		return "<h1>Java RESTful Jersey version used is :<h1> "+JERSEY_API_VERSION;
	}
	
	//@PathParam Example
	@GET
	@Path(value = "/wsDev/{devName}")
	@Produces(MediaType.TEXT_HTML)
	public String wsWithInputParam(@PathParam("devName") String devName){
		return "<h3>Java REST WebService<h3> developed by "+devName;
	}	
	
	//@PathParam Example
	//TODO 
	/*public String wsRegExExample(@PathParam("devName") String devName){
		return "<h3>Java RESTful WebService<h3> developed by "+devName;
	}	*/
	
	//@QueryParam Example
	@GET
	@Path(value = "/wsDev/demoTo")
	@Produces(MediaType.TEXT_HTML)
	public String wsWithInputQueryParam(@QueryParam("companyName") String demoToCompany){
		return "<h3>Java RESTful WebService<h3> DEMO to Company "+demoToCompany;
	}
	
	//DB Connection Example with Weblogic Datasource creation example
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path(value = "/dbTime")
	public String getDateTimeinMySqlDatabase(){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String myString = null;
		String todaysDate = null;
		String dbReturnString = null;
		DataSource ds = null;

		try {
			ds = JavaRestJNDI.getConnectionFromMySqlDS();
			conn = ds.getConnection();
			//System.out.println("conn:"+conn);
			String sql = "SELECT curdate() as TODAYSDATE,curtime() as DATETIME";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){
				myString = rs.getString("DATETIME");
				todaysDate = rs.getString("TODAYSDATE");
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbReturnString = "<h1>Current Time at DB end is :<h1> "+todaysDate +" " +myString;
		return dbReturnString;
	}
}
