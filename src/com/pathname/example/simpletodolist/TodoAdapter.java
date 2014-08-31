package com.pathname.example.simpletodolist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter {
	private static List<TodoItem> searchArrayList;
	 
	 private LayoutInflater mInflater;

	 public TodoAdapter(Context context, List<TodoItem> results) {
	  searchArrayList = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return searchArrayList.size();
	 }

	 public Object getItem(int position) {
	  return searchArrayList.get(position);
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
	  
	  holder.txtTitle.setText(searchArrayList.get(position).title);
	  holder.txtDueDate.setText(searchArrayList.get(position).duedate);
	  holder.txtPriority.setText(Integer.toString(searchArrayList.get(position).priority));

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtTitle;
	  TextView txtDueDate;
	  TextView txtPriority;
	 }
}
