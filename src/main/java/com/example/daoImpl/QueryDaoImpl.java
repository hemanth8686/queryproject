package com.example.daoImpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Jpa.ClientJpa;
import com.example.Jpa.EmployeeJpa;
import com.example.Jpa.QueryJpa;
import com.example.bean.QueryBean;
import com.example.dao.QueryDao;
import com.example.model.ClientModel;
import com.example.model.EmployeeModel;
import com.example.model.QueryModel;

@Repository
public class QueryDaoImpl implements QueryDao {
	
	@Autowired
	private QueryJpa queryJpa;
	
	
	@Autowired
	private ClientJpa clientJpa;
	
	
	@Autowired
	private EmployeeJpa employeeJpa;
	
	
	
	static String format = "";

	public static String getCurrentDate() {

		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		format = sdf.format(cal);
		System.out.println(format);
		return format;

	}

	public static String getDatelog() {
		String dat = "";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date();
		dat = dateFormat.format(date);

		return dat;
	}
	public int getClientIdByName(String name) {
		return clientJpa.getClientNameById(name);
	}
	
	public void saveQuery(QueryBean queryBean) throws ParseException {
		QueryModel model=new QueryModel();
		System.out.println(queryBean.getRaisedDate()+"tesing dao");
		String raisedDate = queryBean.getRaisedDate();
		String completedDate = queryBean.getCompletedDate();
	
		   Date raisedDateForModel=new SimpleDateFormat("yyyy-MM-dd").parse(raisedDate);
		 
		  
		model.setClient(queryBean.getClient());
		System.out.println(getClientIdByName(queryBean.getClient()));
		model.setClientId(getClientIdByName(queryBean.getClient()));
		model.setRaisedDate(raisedDateForModel);
		model.setQuery(queryBean.getQuery());
		model.setRaisedBy(queryBean.getRaisedBy());
		model.setRaisedThrough((queryBean.getRaisedThrough()));
		model.setClosedBy(queryBean.getCompletedBy());
		model.setStatus(queryBean.getStatus());
		model.setClosedBy(queryBean.getCompletedBy());
		model.setClient(queryBean.getClient());
		model.setPriority(queryBean.getPriority());
		  if (completedDate.length()>0) {
			   Date   completedDateForModel=new SimpleDateFormat("yyyy-MM-dd").parse(completedDate);  
			   model.setClosedDate(completedDateForModel);
		}
		
		model.setAttentedBy(queryBean.getAttentedBy());
		model.setClient(queryBean.getClient());
		
		
		if (queryBean.getStatus().equalsIgnoreCase("pending")) {
			model.setQueryStatus(2);
			
		}
		
		else if (queryBean.getStatus().equalsIgnoreCase("completed")) {
			model.setQueryStatus(1);
			
		}
		else {
			model.setQueryStatus(3);

		}
		model.setQueryId(queryJpa.maxId()+1);
		model.setCreatedDate(new Timestamp(new Date().getTime()));
		queryJpa.save(model);
	}

	@Override
	public List<QueryModel> getQueryReport(String fromDate, String toDate) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(fromDate+"fromDatefromDate"+toDate+"toDatetoDate");
		  Date fromDateQuery=new SimpleDateFormat("yyyy-MM-dd").parse(fromDate); 
		  Date toDateQuery=new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		List<QueryModel> queryReport = queryJpa.getQueryReport(fromDateQuery, toDateQuery);
		for (QueryModel queryModel : queryReport) {
			System.out.println(queryModel.getQuery()+"gotoQueryReportgotoQueryReportgotoQueryReport");
			
		}
		return queryReport;
	}

	@Override
	public List<ClientModel> getClientList() {
		List<ClientModel> clientList = clientJpa.getClientList();
		return clientList;
	}

	@Override
	public void addClient(String clientName) {
		ClientModel clientModel=new ClientModel();
		clientModel.setClientName(clientName);
		clientModel.setStatus(1);
		clientJpa.save(clientModel);
		
	}

	@Override
	public List<EmployeeModel> getEmployeeList() {
		List<EmployeeModel> employeeList = employeeJpa.getEmployeeList();
		return employeeList;
	}

	@Override
	public void addEmployee(String employeeName, String employeeMail, String mailType) {
		EmployeeModel employeeModel=new EmployeeModel();
		employeeModel.setEmployeeName(employeeName);
		employeeModel.setEmployeeMail(employeeMail+",");
		employeeModel.setMailType(mailType);
		employeeModel.setStatus(1);
		employeeJpa.save(employeeModel);
	}

	@Override
	public List<QueryModel> getQueryReportForExcel(int clientId) {
				List<QueryModel> queryReportForExcel = new ArrayList<QueryModel>();
System.out.println(clientId+"clientIdclientId");
				if (clientId==0) {
		queryReportForExcel= queryJpa.getQueryReportForExcelCommon();
		
	}
	else {
		queryReportForExcel = queryJpa.getQueryReportForExcel(clientId);
		
	}
	return queryReportForExcel;
		
		
			
	
		
		
	}

	@Override
	@Transactional 
	public String updateQuery(int  Id,String closedBy,String closedDate,String status) {
		  Date closedDateQuery;
		  System.out.println(status+"statusstatus");
		try {
			closedDateQuery = new SimpleDateFormat("yyyy-MM-dd").parse(closedDate);
			if (status.equalsIgnoreCase("completed")) {
				int queryStatus=1;
				queryJpa.updateQueryCompleted(Id, closedBy, closedDateQuery, status,queryStatus);
				
			}
			else {
				int queryStatus=2;
				queryJpa.updateQueryPending(Id, closedBy, closedDateQuery, status,queryStatus);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "Update success";
		
	}

	@Override
	public List<QueryModel> getQueryById(int Id) {
		// TODO Auto-generated method stub
		return queryJpa.getQueryId(Id);
	}

	@Override
	public void deleteQuery(int Id) {
	queryJpa.deleteQuery(Id);
		
	}

	@Override
	public List<EmployeeModel> getTOEmail() {
		// TODO Auto-generated method stub
		return employeeJpa.getEmployeeListTo();
	}

	@Override
	public List<EmployeeModel> getCCEmail() {
		// TODO Auto-generated method stub
		return employeeJpa.getEmployeeListCC();
	}
	@Override
	public List<String> toEmailtest(){
		return employeeJpa.getEmployeeListToTest();
		
	}

	@Override
	public List<String> ccEmailtest() {
		// TODO Auto-generated method stub
		return employeeJpa.getEmployeeListCcTest();
	}
	
	
	

}
