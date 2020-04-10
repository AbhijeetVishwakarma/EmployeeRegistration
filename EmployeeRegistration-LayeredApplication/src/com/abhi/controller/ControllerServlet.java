package com.abhi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abhi.service.EmployeeService;
import com.abhi.service.EmployeeServiceImpl;
import com.abhi.vo.EmployeeVO;

public class ControllerServlet  extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
		String name=null;
		String address =null;
		String designation =null;
		String gender =null;
		String salary =null;
		EmployeeVO vo =null;
		PrintWriter pw =null;
		EmployeeService service = null;
//		set response mime type
		response.setContentType("text/html");
//		gather form data
		name = request.getParameter("ename");
		address=request.getParameter("address");
		designation = request.getParameter("edesignation");
		gender = request.getParameter("gender");
		salary=request.getParameter("esalary");
//		store value in VO object
		vo.setName(name);
		vo.setAddress(address);
		vo.setDesignstion(designation);
		vo.setGender(gender);
		vo.setSalary(salary);
//		Pass VO object to service class 
		service = new EmployeeServiceImpl();
		String message = service.storeEmployee(vo);
		pw=response.getWriter();
		pw.println("<h1 >"+message+"</h1><br>");
//		set home page link
		pw.println("<a href='index.html'>Home</a>");
//		close stream 
		pw.close();
	}// doGet(-);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}// doPost();
	   

}// class
