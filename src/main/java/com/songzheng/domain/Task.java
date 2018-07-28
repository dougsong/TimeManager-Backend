package com.songzheng.domain;

import java.util.Date;

public class Task {

	private String tid;
	
	private String task_content;
	
	private Boolean complete;
	
	private Boolean star;
	
	private Date expireTime;
	
	private TaskGroup taskGroup;

	public Task() {
	}

	public Task(String tid, String task_content, Boolean complete, Boolean star, Date expireTime, TaskGroup taskGroup) {

		this.tid = tid;
		this.task_content = task_content;
		this.complete = complete;
		this.star = star;
		this.expireTime = expireTime;
		this.taskGroup = taskGroup;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTask_content() {
		return task_content;
	}

	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getStar() {
		return star;
	}

	public void setStar(Boolean star) {
		this.star = star;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}
	
}
