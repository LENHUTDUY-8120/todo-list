package com.todoList.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoList.request.GroupReq;
import com.todoList.response.GroupRes;
import com.todoList.response.GroupResWithAmount;
import com.todoList.service.GroupService;
import com.todoList.util.MapperUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/group")
public class GroupApi {

	@Autowired
	private GroupService groupService;
	
	@PostMapping
	public GroupRes addGroup(@RequestBody GroupReq groupReq) {
		return MapperUtil.map(groupService.addGroup(groupReq), GroupRes.class);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeGroup(@PathVariable(name = "id") Long id) {
		groupService.remove(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public GroupRes update(@RequestBody GroupReq groupReq, @PathVariable(name = "id") Long id) {
		return MapperUtil.map(groupService.update(id, groupReq), GroupRes.class);
	}
	
	@GetMapping
	public List<GroupResWithAmount> getAll() {
		return groupService.getAll();
	}
}
