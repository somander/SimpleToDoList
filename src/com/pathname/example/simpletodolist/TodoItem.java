package com.pathname.example.simpletodolist;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "TodoItem")
public class TodoItem extends Model {
	@Column(name = "title")
	public String title;
	
	@Column(name = "duedate")
	public String duedate;
	
	@Column(name = "priority")
	public int priority;
	
	public TodoItem(){
		super();
	}
}
