package com.flashstudy.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashstudy.flashcard.Flashcard;

public class FormEntryActivity extends Activity {

	private ArrayList<Flashcard> _flashcards;
	private InputListAdapter _inputListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_entry);
		
		Flashcard card = new Flashcard("", "");
		_flashcards = new ArrayList<Flashcard>();
		_flashcards.add(card);
		
		_inputListAdapter = new InputListAdapter(this, _flashcards);
		ListView listView = (ListView) findViewById(R.id.FormActivity_inputList);
		listView.setAdapter(_inputListAdapter);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		((TextView) findViewById(R.id.FormActivity_nameText)).setTypeface(tf);
		((TextView) findViewById(R.id.FormActivity_nameInput)).setTypeface(tf);
		((TextView) findViewById(R.id.FormActivity_descText)).setTypeface(tf);
		((TextView) findViewById(R.id.FormActivity_descInput)).setTypeface(tf);
		((TextView) findViewById(R.id.FormActivity_term)).setTypeface(tf);
		((TextView) findViewById(R.id.FormActivity_definition)).setTypeface(tf);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.FormActivity_layout);
		ImageView homeButton = (ImageView) layout.findViewById(R.id.Header_logo);
		homeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FormEntryActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView formButton = (ImageView) layout.findViewById(R.id.Header_formUpload);
		formButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FormEntryActivity.this, FormEntryActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView cameraButton = (ImageView) layout.findViewById(R.id.Header_cameraUpload);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(FormEntryActivity.this, "CAMERA BUTTON", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void onAddElement(View view) {
		_flashcards.add(new Flashcard("", ""));
		_inputListAdapter.notifyDataSetChanged();
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
