package com.example.Jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.ClientModel;
import com.example.model.QueryModel;

public interface ClientJpa extends CrudRepository<ClientModel, Integer> {
	
	
	@Query("from ClientModel where  status=1  ")

	public List<ClientModel> getClientList();
	
	
	@Query("select Id from  ClientModel where  clientName=:name  ")

	public int getClientNameById(@Param("name") String name);

}
