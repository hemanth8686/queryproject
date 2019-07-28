package com.example.serviceImpl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.QueryBean;
import com.example.dao.QueryDao;
import com.example.model.ClientModel;
import com.example.model.EmployeeModel;
import com.example.model.QueryModel;
import com.example.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService {
	@Autowired
	private QueryDao queryDao;

	@Override
	public void saveQuery(QueryBean queryBean) throws ParseException {
		queryDao.saveQuery(queryBean);
		
		
	}
	
	public List<QueryModel> getQueryReport(String fromDate,String toDate)throws ParseException{
	return	queryDao.getQueryReport(fromDate, fromDate);
	}

	@Override
	public List<ClientModel> getClientList() {
		// TODO Auto-generated method stub
		return queryDao.getClientList();
	}

	@Override
	public void addClient(String clientName) {
		queryDao.addClient(clientName);
		
	}

	@Override
	public List<EmployeeModel> getEmployeeList() {
		// TODO Auto-generated method stub
		return queryDao.getEmployeeList();
	}

	@Override
	public void addEmployee(String employeeName, String employeeMail, String mailType) {
		queryDao.addEmployee(employeeName, employeeName, mailType);
		
	}

	@Override
	public List<QueryModel> getQueryReportForExcel() {
		// TODO Auto-generated method stub
		return queryDao.getQueryReportForExcel();
	}

	@Override
	public String updateQuery(int  Id,String completedBy,String closedDate,String status) {
	return	queryDao.updateQuery(Id,completedBy,closedDate,status);
		
	}

	@Override
	public List<QueryModel> getQueryById(int Id) {
		// TODO Auto-generated method stub
		return queryDao.getQueryById(Id);
	}

	@Override
	public void deleteQuery(int Id) {
		queryDao.deleteQuery(Id);
		
	}

	@Override
	public List<EmployeeModel> getTOEmail() {
		// TODO Auto-generated method stub
		return queryDao.getTOEmail();
	}

	@Override
	public List<EmployeeModel> getCCEmail() {
		// TODO Auto-generated method stub
		return queryDao.getCCEmail();
	}

	@Override
	public List<String> toEmailtestTo() {
		// TODO Auto-generated method stub
		return queryDao.toEmailtest();
	}

	@Override
	public List<String> toEmailtestCc() {
		// TODO Auto-generated method stub
		return queryDao.ccEmailtest();
	}

}
