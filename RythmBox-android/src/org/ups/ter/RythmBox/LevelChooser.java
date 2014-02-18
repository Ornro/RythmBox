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
    
    public static int CHOICE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_chooser);
		
		
		 ListView listView = (ListView)findViewById(R.id.list);	        
	        list.add("Easy");
	        list.add("Medium");
	        list.add("Hard");
	        
	       
	        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

	        listView.setAdapter(adapter);
	        
	        adapter.notifyDataSetChanged();
	        
	        // ListView Item Click Listener
            listView.setOnItemClickListener(new OnItemClickListener() {


				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long index) {
					
					if (position == 0) {
						//start easy level
						//method to launch Game	
						CHOICE = 0;
						
					}
					
					if (position == 1) {
						//start medium level
						CHOICE = 1;
					}
					
					if (position == 2) {
						//start hard level
						CHOICE = 2;
					}
					
					startActivity(new Intent(LevelChooser.this, GameActivity.class));
					
					 Toast.makeText(getApplicationContext(),
		                      "Position :"+position, 
		                      Toast.LENGTH_LONG)
		                      .show();
					 
					
				}
    
             }); 
	        
	      
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_chooser, menu);
		return true;
	}

}
