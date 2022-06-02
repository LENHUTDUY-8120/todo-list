package com.todoList.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoList.request.PositionChange;
import com.todoList.request.WorkReq;
import com.todoList.request.WorkToGroup;
import com.todoList.response.WorkRes;
import com.todoList.service.WorkService;
import com.todoList.util.MapperUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/work")
public class WorkApi {

	@Autowired
	private WorkService workService;
	
	@PostMapping
	public WorkRes addWork(@RequestBody WorkReq workReq) {
		return MapperUtil.map(workService.addWork(workReq), WorkRes.class);
	}
	
	@GetMapping
	public List<WorkRes> getAll(@RequestParam(required = false) String searchKey,
								@RequestParam(required = false) Long groupId) {
		return workService.getAll(searchKey, groupId);
	}
	
	@PutMapping
	public ResponseEntity<?> changePosition(@RequestBody PositionChange positionChange){
		workService.changePosition(positionChange.getFromId(),
				positionChange.getToId(),
				positionChange.getChangeId());
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/addToGroup")
	public ResponseEntity<?> addWorkToGroup(@RequestBody WorkToGroup workToGroup) {
		workService.addToGroup(workToGroup.getGroupId(), workToGroup.getWorkId());
		return ResponseEntity.ok().build();
	}
}
