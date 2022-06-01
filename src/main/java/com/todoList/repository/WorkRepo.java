package com.todoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoList.entity.WorkEntity;

public interface WorkRepo extends JpaRepository<WorkEntity, Long>{

}
