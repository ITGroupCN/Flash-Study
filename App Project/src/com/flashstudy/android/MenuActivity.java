package com.flashstudy.android;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashstudy.flashcard.Flashcard;
import com.flashstudy.flashcard.FlashcardType;
import com.flashstudy.flashcard.Set;

public class MenuActivity extends Activity {

	private FlashcardListAdapter _flashcardAdapter;
	private ArrayList<Set> _sets;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ArrayList<Flashcard> cards = new ArrayList<Flashcard>();
		cards.add(new Flashcard("paras", "faggot"));
		cards.add(new Flashcard("fbla", "gay"));
		cards.add(new Flashcard("michael", "wrassla"));
		
		Set set1 = new Set("Capitulo 4 Vocab", cards, new Date(), FlashcardType.SPANISH, "Imagina Chapter 4");
		_sets = new ArrayList<Set>();
		_sets.add(set1);
		
		_flashcardAdapter = new FlashcardListAdapter(this, _sets);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.MenuActivity_layout);
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		setTypeface(layout, tf);
		
		ImageView formButton = (ImageView) layout.findViewById(R.id.Header_formUpload);
		formButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MenuActivity.this, FormEntryActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView cameraButton = (ImageView) layout.findViewById(R.id.Header_cameraUpload);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MenuActivity.this, "CAMERA BUTTON", Toast.LENGTH_SHORT).show();
			}
		});
		
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
		
		setTypeface(layout, tf);
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
