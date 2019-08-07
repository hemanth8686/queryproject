package com.example.contoller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.bean.QueryBean;
import com.example.model.ClientModel;
import com.example.model.EmployeeModel;
import com.example.model.QueryModel;
import com.example.service.QueryService;

@Controller
public class Query {

	@Autowired
	private QueryBean queryBean;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private JobQuery jobQuery;

	
	public static String getDatelog() {
		String dat = "";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date();
		dat = dateFormat.format(date);
		return dat;
	}
	

	@RequestMapping(value = "menu")
	public ModelAndView menu() {
		ModelAndView view = new ModelAndView();
		System.out.println("menu");
		view.setViewName("menu");
		return view;
	}

	@RequestMapping(value = "gotoQuery")
	public ModelAndView test() {
		ModelAndView view = new ModelAndView();
		List<ClientModel> clientList = queryService.getClientList();
		view.addObject("clientList", clientList);
		List<EmployeeModel> employeeList = queryService.getEmployeeList();
		view.addObject("employeeList", employeeList);
		view.setViewName("QueryEntry");
		return view;
	}

	@RequestMapping(value = "queryEnter")
	public ModelAndView queryEnter(@ModelAttribute(value = "QueryBean") QueryBean queryBean) throws ParseException {
		ModelAndView view = new ModelAndView();
		String status = queryBean.getStatus();
		System.out.println(status + "statusstatusstatus");
		List<ClientModel> clientList = queryService.getClientList();
		view.addObject("clientList", clientList);
		List<EmployeeModel> employeeList = queryService.getEmployeeList();
		view.addObject("employeeList", employeeList);
		queryService.saveQuery(queryBean);
		view.setViewName("QueryEntry");
		return view;

	}

	@RequestMapping(value = "gotoQueryReport")

	public ModelAndView gotoQueryReport() throws ParseException {
		ModelAndView view = new ModelAndView();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();
	        System.out.println(sdf.format(date));
	        String fromDate = sdf.format(date);
	        String toDate = sdf.format(date);
		List<QueryModel> queryReport = queryService.getQueryReport(fromDate, toDate);
		view.addObject("queryReport", queryReport);
		view.setViewName("QueryReport");
		return view;

	}

	@RequestMapping(value = "queryReport")
	public ModelAndView queryReport(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, HttpSession session ) throws ParseException {
		ModelAndView view = new ModelAndView();
		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);
	//	Integer userID = (Integer) session.getAttribute("name");
		System.out.println(fromDate + ".." + toDate);
		List<QueryModel> queryReport = queryService.getQueryReport(fromDate, toDate);
		view.addObject("queryReport", queryReport);
		view.setViewName("QueryReport");
		return view;

	}

	@RequestMapping(value = "gotoAddClient")
	public ModelAndView gotoAddClient() {
		ModelAndView view = new ModelAndView();
		List<ClientModel> clientList = queryService.getClientList();
		view.addObject("clientList", clientList);
		view.setViewName("AddClient");
		return view;
	}

	@RequestMapping(value = "addClient")
	public ModelAndView addClient(@RequestParam(value = "clientName") String clientName) {
		ModelAndView view = new ModelAndView();
		List<ClientModel> clientList = queryService.getClientList();
		queryService.addClient(clientName);
		view.addObject("clientList", clientList);
		view.setViewName("AddClient");

		return view;

	}

	@RequestMapping(value = "gotoAddEmployee")

	public ModelAndView gotoAddEmployee() {
		ModelAndView view = new ModelAndView();
		List<EmployeeModel> employeeList = queryService.getEmployeeList();
		view.addObject("employeeList", employeeList);
		view.setViewName("AddEmployee");
		return view;
	}

	@RequestMapping(value = "addEmployee")

	public ModelAndView addEmployee(@RequestParam(value = "employeeName") String employeeName,
			@RequestParam(value = "employeeEmail") String employeeMail,
			@RequestParam(value = "mailType") String mailType) {
		ModelAndView view = new ModelAndView();
		List<EmployeeModel> employeeList = queryService.getEmployeeList();
		queryService.addEmployee(employeeName, employeeMail, mailType);
		view.addObject("employeeList", employeeList);
		view.setViewName("AddEmployee");
		return view;
	}

	@RequestMapping(value = "gotoupdateQuery")
	public ModelAndView upadteQuery(@RequestParam(value = "Id") int Id) {
		ModelAndView view = new ModelAndView();
		List<EmployeeModel> employeeList = queryService.getEmployeeList();
		// List<QueryModel> queryReport = queryService.getQueryReport(fromDate, toDate);
		List<QueryModel> queryById = queryService.getQueryById(Id);
		view.addObject("employeeList", employeeList);
		view.addObject("queryById", queryById);
		view.setViewName("QueryUpdate");
		return view;
	}

	@RequestMapping(value = "updateQuery")
	public ModelAndView updateQuery(@RequestParam(value = "Id") int Id,
			@RequestParam(value = "completedBy") String completedBy,
			@RequestParam(value = "closedDate") String closedDate, @RequestParam(value = "status") String status) {
		ModelAndView view = new ModelAndView();
		String updateQuery = queryService.updateQuery(Id,completedBy,closedDate,status);
		view.addObject("message", updateQuery);
		view.setViewName("UpdateComplete");
		return view;

	}
	
	@RequestMapping(value = "deleteQuery")
	public ModelAndView deleteQuery(@RequestParam(value = "Id") int Id,HttpSession session) throws ParseException {
		ModelAndView view = new ModelAndView();
		queryService.deleteQuery(Id);
		String fromDate = (String) session.getAttribute("fromDate");
		String toDate = (String) session.getAttribute("toDate");
		List<QueryModel> queryReport = queryService.getQueryReport(fromDate, toDate);
		view.addObject("queryReport", queryReport);
		
		view.setViewName("QueryReport");
		return view;
	}
	
	
	@RequestMapping(value="homeMenu")
	public String homeMenu() {
		return "menu";

}
	
	
	@RequestMapping(value="updateExcel")
	public String updateExcel() throws IOException {
		jobQuery.createExcelByClickTest();
		return "menu";
	}

}