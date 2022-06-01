package com.todoList.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.todoList.dto.WorkDTO;
import com.todoList.repository.WorkRepo;

public class WorkService {

	@Autowired
	private WorkRepo workRepo;
	
	public WorkDTO addWork() {
		
		return null;
	}
}
