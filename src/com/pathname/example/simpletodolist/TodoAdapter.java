package com.pathname.example.simpletodolist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter {
	private static List<TodoItem> todoList;
	 
	 private LayoutInflater mInflater;

	 public TodoAdapter(Context context, List<TodoItem> results) {
	  todoList = results;
	  mInflater = LayoutInflater.from(context);
	 }
	 
	 public void add(TodoItem todo) {
		 todoList.add(todo);
		 
	 }
	 
	 public void remove(int position) {
		 todoList.remove(position);
	 }
	 
	 public void setItems(List<TodoItem> updatedList) {
		 todoList = updatedList;
	 }
	 
	 public TodoItem get(int position) {
		 TodoItem itemtodo = todoList.get(position);
		 return itemtodo;
	 }

	 public int getCount() {
	  return todoList.size();
	 }

	 public Object getItem(int position) {
	  return todoList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.custom_row_view, null);
	   holder = new ViewHolder();
	   holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
	   holder.txtDueDate = (TextView) convertView.findViewById(R.id.duedate);
	   holder.txtPriority = (TextView) convertView.findViewById(R.id.priority);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtTitle.setText(todoList.get(position).title);
	  holder.txtDueDate.setText(todoList.get(position).duedate);
	  holder.txtPriority.setText(Integer.toString(todoList.get(position).priority));

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtTitle;
	  TextView txtDueDate;
	  TextView txtPriority;
	 }
}
