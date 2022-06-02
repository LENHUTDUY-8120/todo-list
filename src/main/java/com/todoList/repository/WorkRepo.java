package com.todoList.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todoList.entity.WorkEntity;

public interface WorkRepo extends JpaRepository<WorkEntity, Long>{

	List<WorkEntity> findBySearchKeyContaining(String searchKey, Sort sortType);
	
	@Query(value = "select * from work w inner join workgroup wg on w.group_id = wg.id where wg.id = ?1")
	List<WorkEntity> findByGroupId( Long id, Sort sortType);
}
