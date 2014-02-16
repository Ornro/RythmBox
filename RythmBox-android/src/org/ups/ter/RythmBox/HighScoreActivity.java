package org.ups.ter.RythmBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
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

	        list.add("Bla");
	        list.add("Bla");
	        list.add("Bla");
	        list.add("Bla");
	        list.add("Bla");
	        
	        ListView listView = (ListView)findViewById(R.id.list);
	        
	        String data = readTextFile(this, R.raw.highscore);
	        
	        //do something with the data for only the first 5
	        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

	        listView.setAdapter(adapter);
	        
	        adapter.notifyDataSetChanged();
	       
	        // setListAdapter(adapter); 
	    }

	    public static String readTextFile(Context ctx, int resId)
	    {
	        InputStream inputStream = ctx.getResources().openRawResource(resId);

	        InputStreamReader inputreader = new InputStreamReader(inputStream);
	        BufferedReader bufferedreader = new BufferedReader(inputreader);
	        String line;
	        StringBuilder stringBuilder = new StringBuilder();
	        try 
	        {
	            while (( line = bufferedreader.readLine()) != null) 
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
