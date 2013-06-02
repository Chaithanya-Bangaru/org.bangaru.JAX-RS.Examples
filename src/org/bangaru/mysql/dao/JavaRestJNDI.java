
package org.bangaru.mysql.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JavaRestJNDI {
	private static Context dbContext;
	private static DataSource mysqlDS;

	public static DataSource getConnectionFromMySqlDS() throws NamingException, SQLException {
		if(mysqlDS != null){
			return  mysqlDS;
		}	
		if(dbContext == null){
			dbContext = new InitialContext();
		}

		mysqlDS = (DataSource) dbContext.lookup("JavaRestJNDI");
		return mysqlDS;
	}

}