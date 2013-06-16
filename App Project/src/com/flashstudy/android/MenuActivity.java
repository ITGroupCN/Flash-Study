package com.flashstudy.android;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
		
		Set set1 = new Set("Test Set", cards, new Date(), FlashcardType.SPANISH, "A test set that shows shravs gay shit.");
		_sets = new ArrayList<Set>();
		_sets.add(set1);
		
		_flashcardAdapter = new FlashcardListAdapter(this, _sets);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.MenuActivity_layout);
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		setTypeface(layout, tf);
		
		ListView setList = (ListView) findViewById(R.id.MenuActivity_setList);
		setList.setAdapter(_flashcardAdapter);
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
