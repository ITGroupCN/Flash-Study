package com.flashstudy.android;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flashstudy.flashcard.Flashcard;
import com.flashstudy.flashcard.FlashcardType;
import com.flashstudy.flashcard.Set;

public class MenuActivity extends Activity {

	private FlashcardListAdapter _flashcardAdapter;
	private Set[] _sets;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ArrayList<Flashcard> cards = new ArrayList<Flashcard>();
		cards.add(new Flashcard("paras", "faggot"));
		cards.add(new Flashcard("fbla", "gay"));
		cards.add(new Flashcard("michael", "wrassla"));
		
		Set set1 = new Set("Test Set", cards, new Date(), FlashcardType.SPANISH, "hello");
		
		_sets = new Set[1];
		_sets[0] = set1;
		
		_flashcardAdapter = new FlashcardListAdapter(this, _sets);
		ListView setList = (ListView) findViewById(R.id.MenuActivity_setList);
		setList.setAdapter(_flashcardAdapter);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.MenuActivity_layout);
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic_italic.ttf");
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
