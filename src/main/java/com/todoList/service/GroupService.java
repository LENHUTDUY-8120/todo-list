package com.todoList.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoList.entity.GroupEntity;
import com.todoList.exception.ResourceNotFoundException;
import com.todoList.repository.GroupRepo;
import com.todoList.request.GroupReq;
import com.todoList.response.GroupResWithAmount;

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
	
	public GroupEntity update(Long id, GroupReq groupReq) {
		GroupEntity groupEntity = groupRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("group", "id", id));
		groupEntity.setGroupTitle(groupReq.getTitle());
		return groupRepo.save(groupEntity);
	}
	
	public List<GroupResWithAmount> getAll(){
		List<GroupEntity> groupEntities = groupRepo.findAll();
		return groupEntities.stream().map(group -> toOne(group)).collect(Collectors.toList());
	}
	
	private GroupResWithAmount toOne(GroupEntity groupEntity) {
		GroupResWithAmount groupResWithAmount = new GroupResWithAmount();
		groupResWithAmount.setId(groupEntity.getId());
		groupResWithAmount.setTitle(groupEntity.getGroupTitle());
		groupResWithAmount.setAmount(groupEntity.getWorks().size());
		groupResWithAmount.setCreateDate(groupEntity.getCreateDate());
		groupResWithAmount.setModifiedDate(groupEntity.getModifiedDate());
		return groupResWithAmount;
	}
}
