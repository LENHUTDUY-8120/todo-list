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

import com.todoList.request.CategoryReq;
import com.todoList.response.CategoryAllRes;
import com.todoList.response.CategoryRes;
import com.todoList.service.CategoryService;
import com.todoList.util.MapperUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryApi {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<CategoryAllRes> getAllCategory(){
		return categoryService.getAllCategory();
	}
	
	@PostMapping
	public CategoryRes addCategory(@RequestBody CategoryReq categoryReq) {
		return MapperUtil.map(categoryService.addCaterogy(categoryReq), CategoryRes.class);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeCategory(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public CategoryRes updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryReq categoryReq) {
		return MapperUtil.map(categoryService.update(categoryReq, id), CategoryRes.class);
	}
	
}
