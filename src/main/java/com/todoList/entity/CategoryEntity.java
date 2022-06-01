package com.todoList.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "category")
public class CategoryEntity extends BaseEntity{
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<WorkEntity> works = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WorkEntity> getWorks() {
		return works;
	}

	public void setWorks(List<WorkEntity> works) {
		this.works = works;
	}
	
}
