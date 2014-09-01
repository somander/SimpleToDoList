package com.pathname.example.simpletodolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TodoActivity extends Activity {
	TodoAdapter todoadapter;
	ListView lvItems;
	private final int REQUEST_CODE = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		List<TodoItem> todoResults = GetTodoItems();
		
		lvItems = (ListView) findViewById(R.id.lvItems);
		todoadapter = new TodoAdapter(this, todoResults);
		lvItems.setAdapter(todoadapter);
		
		setupListViewListener();
		//setupClickListener();
	}

	private void setupListViewListener(){
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long rowId){
				
				TodoItem itemToRemove = (TodoItem) todoadapter.getItem(position);
				
				String deletedMsg = itemToRemove.title + " deleted";
				Toast.makeText(parent.getContext(), deletedMsg, Toast.LENGTH_SHORT).show();
				
				itemToRemove.delete();
				todoadapter.remove(position);
				todoadapter.notifyDataSetChanged();
				return true;
			}
		});
	}
	/*
	private void setupClickListener(){
		lvItems.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view, int position, long rowId){
				  Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
				  String itemText = items.get(position);
				  i.putExtra("itemText", itemText); 
				  i.putExtra("itemPosition", position);
				  startActivityForResult(i, REQUEST_CODE);
			}
		});
	}
	
	private void readItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
			e.printStackTrace();
		}
	}
			
	private void saveItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try{
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	
	private List<TodoItem> GetTodoItems(){
	     List<TodoItem> items = getAll();
	     if (items.isEmpty()) {
	    	 // Save one example item.
	    	 TodoItem item = new TodoItem();
	    	 item.title = "Please add a description of what you want to do";
	    	 item.duedate = "Due date here";
	    	 item.priority = 1;
	    	 item.save();
	    	 items = getAll();
	     }
	     return items; 
    }
	
	public static List<TodoItem> getAll() {
	    return new Select()
	        .from(TodoItem.class)
	        .orderBy("title asc")
	        .execute();
	}

	
	public void addTodoItem(View v) {
		TodoItem newTodo = new TodoItem();
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		EditText etNewDueDate = (EditText) findViewById(R.id.etNewDueDate);
		newTodo.title = etNewItem.getText().toString();
		newTodo.duedate = etNewDueDate.getText().toString();
		newTodo.priority = 1;
		newTodo.save();
		todoadapter.add(newTodo);
		
		String addedMsg = etNewItem.getText().toString() + " added";
		Toast.makeText(this, addedMsg, Toast.LENGTH_SHORT).show();
		
		etNewItem.setText("");
		etNewDueDate.setText("");
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
		     String itemText = data.getExtras().getString("itemText");
		     //int itemPosition = data.getExtras().getInt("itemPosition");
		     //items.remove(itemPosition);
		     //itemsAdapter.insert(itemText, itemPosition);
		     todoadapter.notifyDataSetChanged();
		     itemText = itemText + " updated";
		     Toast.makeText(this, itemText, Toast.LENGTH_SHORT).show();
		  }
		} 
}
