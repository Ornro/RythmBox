package org.ups.ter.RythmBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HighScoreActivity extends Activity {

	 /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();
 
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_high_score);
	        
	        ListView listView = (ListView)findViewById(R.id.list);
	        
	        String data = readTextFile(this, R.raw.highscore);
	        
	        list.add(data);
	        
	        //do something with the data for only the first 5
	        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

	        listView.setAdapter(adapter);
	        
	        adapter.notifyDataSetChanged();
	       
	        // setListAdapter(adapter); 
	    }

	    public String readTextFile(Context ctx, int resId)
	    {
	    	File sdcard = Environment.getExternalStorageDirectory();

	    	//Get the text file
	    	File file = new File(sdcard, "RythmBox/highscore.txt");

	    	//Read text from file
	    	StringBuilder text = new StringBuilder();
	    	
	    	
	        BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        String line;
	        StringBuilder stringBuilder = new StringBuilder();
	        try 
	        {
	            while (( line = br.readLine()) != null) 
	            {
	                stringBuilder.append(line);
	                stringBuilder.append('\n');
	            }
	        } 
	        catch (IOException e) 
	        {
	            return null;
	        }
	        return stringBuilder.toString();
	    }

}
