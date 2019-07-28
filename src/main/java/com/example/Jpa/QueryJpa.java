package com.example.Jpa;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.QueryModel;

public interface QueryJpa extends CrudRepository<QueryModel, Integer> {

	@Query("from QueryModel where createdDate>=:fromDate and createdDate>=:toDate and queryStatus!=0  ")

	public List<QueryModel> getQueryReport(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	/*@Query("from QueryModel")
	public List<QueryModel> getQueryReportForExcel();*/

	@Query("  select max(Id) FROM QueryModel ")
	public int maxId();
	@Transactional 
	@Modifying
	@Query("update QueryModel u  set u.queryStatus= :queryStatus, u.closedDate = :closedDate, u.closedBy = :completedBy,u.status=:status where u.queryId = :Id")
	public void updateQueryCompleted(@Param("Id") int Id,@Param("completedBy") String completedBy,@Param("closedDate") Date closedDate, @Param("status") String status,@Param("queryStatus") int queryStatus);
	@Transactional 
	@Modifying
	@Query("update QueryModel u set u.queryStatus= :queryStatus, u.closedDate = :closedDate, u.closedBy = :completedBy,u.status=:status where u.queryId = :Id")
	public void updateQueryPending(@Param("Id") int Id,@Param("completedBy") String completedBy,@Param("closedDate") Date closedDate, @Param("status") String status,@Param("queryStatus") int queryStatus);
	
	@Query("from QueryModel where queryId=:Id " )
	public List<QueryModel> getQueryId( @Param("Id") int Id);
	@Transactional 
	@Modifying
	@Query("update QueryModel u set u.queryStatus = 0 where u.queryId = :Id")
	public void deleteQuery( @Param("Id") int Id);
	
	@Query("from QueryModel where  queryStatus!=0  ")

	public List<QueryModel> getQueryReportForExcel();
	
	



}


