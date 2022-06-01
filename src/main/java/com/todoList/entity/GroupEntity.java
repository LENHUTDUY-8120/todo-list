package com.todoList.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "workgroup")
public class GroupEntity extends BaseEntity{

	@Column(nullable = false)
	private String groupTitle;
	
	@OneToMany(mappedBy = "group")
	private List<WorkEntity> works = new ArrayList<>();
	
	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public List<WorkEntity> getWorks() {
		return works;
	}

	public void setWorks(List<WorkEntity> works) {
		this.works = works;
	}
}
