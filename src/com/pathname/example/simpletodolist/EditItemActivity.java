package com.pathname.example.simpletodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	int itemPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		String itemText = getIntent().getStringExtra("itemText");
		String itemDueDate = getIntent().getStringExtra("itemDueDate");
		int itemPriority = getIntent().getIntExtra("itemPriority", 0);
		itemPosition = getIntent().getIntExtra("itemPosition", 0);
		EditText etEditedItem = (EditText) findViewById(R.id.etEditTodoItem);
		EditText etDuedate = (EditText) findViewById(R.id.etDuedate);
		EditText etPriority = (EditText) findViewById(R.id.etPriority);
		etEditedItem.setText(itemText);
		etDuedate.setText(itemDueDate);
		etPriority.setText(String.valueOf(itemPriority));
	}
	
	public void onSubmit(View v) {
		  EditText etEditItem = (EditText) findViewById(R.id.etEditTodoItem);
		  EditText etDuedate = (EditText) findViewById(R.id.etDuedate);
		  EditText etPriority = (EditText) findViewById(R.id.etPriority);
		  Intent data = new Intent();
		  data.putExtra("itemText", etEditItem.getText().toString());
		  data.putExtra("itemDuedate", etDuedate.getText().toString());
		  data.putExtra("itemPriority", Integer.valueOf(etPriority.getText().toString()));
		  data.putExtra("itemPosition", itemPosition);
		  setResult(RESULT_OK, data);
		  finish();
		} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
