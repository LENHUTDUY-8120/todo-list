package com.todoList.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoList.entity.CategoryEntity;
import com.todoList.exception.ResourceNotFoundException;
import com.todoList.repository.CategoryRepo;
import com.todoList.request.CategoryReq;
import com.todoList.response.CategoryAllRes;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public CategoryEntity addCaterogy (CategoryReq categoryReq) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(categoryReq.getName());
		return categoryRepo.save(categoryEntity);
	}
	
	public void remove(Long id) {
		categoryRepo.deleteById(id);
	}
	
	public CategoryEntity update( CategoryReq categoryReq, Long id ) {
		CategoryEntity categoryEntity = categoryRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Category", "id", id));
		categoryEntity.setName(categoryReq.getName());
		return categoryRepo.save(categoryEntity);
	}
	
	public List<CategoryAllRes> getAllCategory() {
		List<CategoryEntity> categoryEntities = categoryRepo.findAll();
		return categoryEntities.stream().map(category -> toCategoryRes(category)).collect(Collectors.toList());
	}
	
	public CategoryAllRes toCategoryRes(CategoryEntity categoryEntity) {
		CategoryAllRes categoryAllRes = new CategoryAllRes();
		categoryAllRes.setName(categoryEntity.getName());
		categoryAllRes.setId(categoryEntity.getId());
		categoryAllRes.setAmount(categoryEntity.getWorks().size());
		return categoryAllRes;
	}
	
}
