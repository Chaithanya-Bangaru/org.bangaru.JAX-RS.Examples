package org.banaru.mysql.dao;

import javax.naming.Context;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JavaRestJNDI {

	private static DataSource mysqlDatasource = null;
	private static Context dbContext = null;

	public static DataSource getConnectionFromMySqlDS() throws Exception{

		if(mysqlDatasource != null){
			return mysqlDatasource;
		}
		try {
			if(dbContext == null){
				dbContext = new InitialContext();
			}
			mysqlDatasource = (DataSource) dbContext.lookup("JavaRestJNDI");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mysqlDatasource;
	}
}
