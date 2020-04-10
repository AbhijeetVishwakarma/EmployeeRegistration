package com.abhi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.abhi.bo.EmployeeBO;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static final String INSERT_QUERY = "INSERT INTO EMP_DETAILS(NAME,ADDRESS,SALARY,DESIGNATION,GENDER)VALUES(?,?,?,?,?)";

	@Override
	public int insertEmployee(EmployeeBO bo) {
		InitialContext ic=null;
		DataSource ds = null;
		Connection con=null;
		PreparedStatement ps=null;
		int count =0;
			try {
			// create initial context object
			ic=new InitialContext();
//			gather DataSource object through lookup operation
			ds =(DataSource)ic.lookup("java:comp/env/DsJndi");
//			 get Connection object from coonnetion pool
			con = ds.getConnection();
//			get preparedStatement object and send query to database
			ps =con.prepareStatement(INSERT_QUERY);
//			set query parameters
			ps.setString(1, bo.getName());
			ps.setString(2, bo.getAddress());
			ps.setDouble(3, bo.getSalary());
			ps.setString(4, bo.getDesignation());
			ps.setString(5, bo.getGender());
//			Execute query
			count = ps.executeUpdate();
			}// try
			catch(SQLException sql) {
				sql.printStackTrace();
			}
			catch(Exception e) {
			e.printStackTrace();
		}
			finally {
//				close connection and prepared statement 
				try {
					if(ps!=null)
						ps.close();
					
				}//try
				catch(SQLException sql) {
					sql.printStackTrace();
				}// catch
				try {
					if(con!=null)
						con.close();
					
				}//try
				catch(SQLException sql) {
					sql.printStackTrace();
				}// catch
				
			}
		return count;
	}

}
