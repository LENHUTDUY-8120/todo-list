package com.todoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoList.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

}
