package com.flashstudy.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flashstudy.flashcard.Set;

public class MenuActivity extends Activity {
	
	private FlashcardListAdapter _flashcardAdapter;
	private ArrayList<Set> _sets;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.MenuActivity_layout);
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		setTypeface(layout, tf);
		
		_sets = new ArrayList<Set>();
		_flashcardAdapter = new FlashcardListAdapter(this, _sets);
		readSets();
		final ListView setList = (ListView) findViewById(R.id.MenuActivity_setList);
		setList.setAdapter(_flashcardAdapter);
		setList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MenuActivity.this, SetViewerActivity.class);
				Set set = (Set) setList.getAdapter().getItem(position);
				intent.putExtra("set", set.toString());
				startActivity(intent);
			}
			
		});
		
		LinearLayout header = (LinearLayout) findViewById(R.id.MenuActivity_header);
		ImageView homeButton = (ImageView) header.findViewById(R.id.Header_logo);
		homeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView formButton = (ImageView) header.findViewById(R.id.Header_formUpload);
		formButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MenuActivity.this, FormEntryActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView cameraButton = (ImageView) header.findViewById(R.id.Header_cameraUpload);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			// SET A FILE URI
			public void onClick(View view) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/Documents/terms.gif"))); // set the image file name
				startActivityForResult(intent, 100);
				
			}
		});
		
		setTypeface(layout, tf);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_sets.clear();
		readSets();
	}
	
	private void readSets() {
		try {
			File file = new File("/sdcard/Documents/sets.txt");
			if(!file.exists())
				file.createNewFile();
			
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext())
				_sets.add(Set.fromString(scanner.nextLine()));
		} catch(IOException e) {
			Log.i("Error", "IOException");
		}
		
		_flashcardAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 100) {
	        if (resultCode == RESULT_OK) {
	        	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/Documents/definitions.gif"))); // set the image file name
				startActivityForResult(intent, 101);
	        }
	    } else if(requestCode == 101) {
	    	if(resultCode == RESULT_OK) {
	    		Intent intent = new Intent(MenuActivity.this, CameraActivity.class);
	        	startActivity(intent);
	    	}
	    }
	}
	
	private void setTypeface(View view, Typeface typeface) {
		if(!(view instanceof ViewGroup)) {
			if(view instanceof TextView)
				((TextView) view).setTypeface(typeface);
			return;
		}
		
		ViewGroup viewGroup = (ViewGroup) view;
		for(int i = 0; i < viewGroup.getChildCount(); i++)
			setTypeface(viewGroup.getChildAt(i), typeface);
	}
}
