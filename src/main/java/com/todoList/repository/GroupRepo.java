package com.todoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoList.entity.GroupEntity;

public interface GroupRepo extends JpaRepository<GroupEntity, Long>{

}
