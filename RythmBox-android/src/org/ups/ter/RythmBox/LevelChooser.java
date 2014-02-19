package org.ups.ter.RythmBox;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class LevelChooser extends Activity {

	/** Items entered by the user is stored in this ArrayList variable */
	ArrayList<String> list = new ArrayList<String>();

	/** Declaring an ArrayAdapter to set items to ListView */
	ArrayAdapter<String> adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_chooser);

		initListView();
	}

	private void initListView() {
		ListView listView = (ListView) findViewById(R.id.list);
		list.add("Easy");
		list.add("Medium");
		list.add("Hard");

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);

		listView.setAdapter(adapter);

		adapter.notifyDataSetChanged();

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long index) {

				launchLevel(position);
			}

		});		
	}

	// Launch the selected level
	protected void launchLevel(int position) {
		
		Intent tmpIntent = new Intent(LevelChooser.this, GameActivity.class);
		
		switch (position) {
		case 0:
		case 1:
		case 2:
			// Add extra data to our intent (level id)
			// then start GameActivity
			tmpIntent.putExtra("levelId", position);
			startActivity(tmpIntent);
			break;
		default:
			// Wrong position, do nothing
			break;
		}
	}

}
