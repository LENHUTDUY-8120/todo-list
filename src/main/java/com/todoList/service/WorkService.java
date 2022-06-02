package com.todoList.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todoList.entity.CategoryEntity;
import com.todoList.entity.GroupEntity;
import com.todoList.entity.WorkEntity;
import com.todoList.exception.ResourceNotFoundException;
import com.todoList.repository.CategoryRepo;
import com.todoList.repository.GroupRepo;
import com.todoList.repository.WorkRepo;
import com.todoList.request.WorkReq;
import com.todoList.response.WorkRes;
import com.todoList.util.MapperUtil;
import com.todoList.util.VnCharacterUtils;

@Service
public class WorkService {

	@Autowired
	private WorkRepo workRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private GroupRepo groupRepo;
	
	public WorkEntity addWork(WorkReq workReq) {
		WorkEntity workEntity = new WorkEntity();
		workEntity.setWorkTitle(workReq.getWorkTitle());
		workEntity.setContent(workReq.getContent());
		CategoryEntity categoryEntity = categoryRepo.findById(workReq.getCategoryId()).orElseThrow();
		workEntity.setCategory(categoryEntity);
		workEntity.setSearchKey(VnCharacterUtils.removeAccent(workReq.getWorkTitle()));
		workEntity.setDayWork(new Date());
		WorkEntity workEntityAfterSave = workRepo.save(workEntity);
		workEntityAfterSave.setPriority((workEntityAfterSave.getId()*10));
		return workRepo.save(workEntity);
	}
	
	public void remove(Long id) {
		workRepo.deleteById(id);;
	}
	
	public WorkRes update(Long id, WorkReq workReq) {
		WorkEntity workEntity = workRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("work", "id", id));
		workEntity.setWorkTitle(workReq.getWorkTitle());
		workEntity.setSearchKey(VnCharacterUtils.removeAccent(workReq.getWorkTitle()));
		workEntity.setContent(workReq.getContent());
		return null;
	}
	
	public List<WorkRes> getAll(String searchKey, Long groupId) {
		List<WorkEntity> workEntities = new ArrayList<>();
		if (searchKey == null && groupId == null) {
			workEntities = workRepo.findAll(Sort.by("priority"));
		} else if(groupId != null) {
//			workEntities = groupRepo.findById(groupId).orElseThrow(
//					() -> new ResourceNotFoundException("group", "id", groupId)).getWorks();
			workEntities = workRepo.findByGroupId(groupId, Sort.by("priority"));
		} else {
			workEntities = workRepo.findBySearchKeyContaining(searchKey, Sort.by("priority"));
		}
		return workEntities.stream().map(work ->
			toOne(work)).collect(Collectors.toList());
	}
	
	public WorkRes toOne(WorkEntity workEntity) {
		WorkRes workRes = MapperUtil.map(workEntity, WorkRes.class);
		workRes.setCategoryId(workEntity.getCategory().getId());
		workRes.setCategoryTitle(workEntity.getCategory().getName());
		return workRes;
	}
	
	public void addToGroup(Long groupId, Long WorkId) {
		GroupEntity groupEntity = groupRepo.findById(groupId).orElseThrow(
				() -> new ResourceNotFoundException("Group", "id", groupId));
		WorkEntity workEntity = workRepo.findById(WorkId).orElseThrow(
				() -> new ResourceNotFoundException("Work", "id", WorkId));
		workEntity.setGroup(groupEntity);
		workRepo.save(workEntity);
	}
	
	public void changePosition(Long fromId, Long toId, Long changeId) {
		WorkEntity workFrom = workRepo.findById(fromId).orElseThrow();
		WorkEntity workTo = workRepo.findById(toId).orElseThrow();
		WorkEntity workChange = workRepo.findById(changeId).orElseThrow();
		
		workChange.setPriority((workFrom.getPriority()+workTo.getPriority())/2);
		workRepo.save(workChange);
	}
	
}
