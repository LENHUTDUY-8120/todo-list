package com.todoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoList.entity.GroupEntity;
import com.todoList.repository.GroupRepo;
import com.todoList.request.GroupReq;

@Service
public class GroupService {

	@Autowired
	private GroupRepo groupRepo;
	
	public GroupEntity addGroup(GroupReq groupReq) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setGroupTitle(groupReq.getTitle());
		return groupRepo.save(groupEntity);
	}
	
	public void remove(Long id) {
		groupRepo.deleteById(id);
	}
}
