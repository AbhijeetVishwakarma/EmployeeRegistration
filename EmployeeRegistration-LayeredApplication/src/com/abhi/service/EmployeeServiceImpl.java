package com.abhi.service;

import com.abhi.bo.EmployeeBO;
import com.abhi.dao.EmployeeDAO;
import com.abhi.dao.EmployeeDAOImpl;
import com.abhi.dto.EmployeeDTO;
import com.abhi.vo.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public String storeEmployee(EmployeeVO vo) {
		EmployeeDTO dto = null;
		double salary = 0.0D;
		double netSalary = 0.0D;
		EmployeeBO bo = null;
		EmployeeDAO dao = null;
		int count = 0;

//		Convert values of VO object values
		dto = new EmployeeDTO();
		dto.setName(vo.getName());
		dto.setAddress(vo.getAddress());
		dto.setDesignation(vo.getDesignstion());
		dto.setGender(vo.getGender());
		salary = Double.parseDouble(vo.getSalary());
		netSalary = calculateSalary(salary);
		dto.setSalary(netSalary);
//        Set BO OBJECT VALUES
		bo = new EmployeeBO();
		bo.setName(dto.getName());
		bo.setAddress(dto.getAddress());
		bo.setSalary(dto.getSalary());
		bo.setDesignation(dto.getDesignation());
		bo.setGender(dto.getGender());
//        Call insert method on DAO object
		dao = new EmployeeDAOImpl();
		count = dao.insertEmployee(bo);
		if (count == 1)
			return "DATA STORED SUCCESSFULL";
		else
			return "INTERNAL SERVER PROBLEM, FAILED TO STORE DATA  PLEASE TRY AGAIN LATER";

	}// storeEmployee();

	private static double calculateSalary(double salary) {
		double esi = 0.0D;
		double pf = 0.0D;
		double netSalary = salary;
		if (salary != 0.0) {
			esi = ((5 * salary) / 100) + ((5 * salary) % 100);
			pf = ((12 * salary) / 100) + ((12 * salary) % 100);
			netSalary = netSalary + esi + pf;
			return netSalary;
		} else {
			return netSalary;
		}

	}// calculateSalary();

}// class
