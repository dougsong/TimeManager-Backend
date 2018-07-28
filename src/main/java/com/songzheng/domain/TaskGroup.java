package com.songzheng.domain;

import java.util.HashSet;
import java.util.Set;

public class TaskGroup {
	
	private String gid;
	
	private String group_title;
	
	private Integer count;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Set<Task> tasks = new HashSet<>();
	
	public String getGroup_title() {
		return group_title;
	}

	public TaskGroup(String gid, String group_title, Integer count, User user, Set<Task> tasks) {
		this.gid = gid;
		this.group_title = group_title;
		this.count = count;
		this.user = user;
		this.tasks = tasks;
	}

	public TaskGroup() {
	}

	public void setGroup_title(String group_title) {
		this.group_title = group_title;

	}


	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
}
