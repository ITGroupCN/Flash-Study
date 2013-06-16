package com.flashstudy.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flashstudy.flashcard.Flashcard;
import com.flashstudy.flashcard.Set;

public class InputListAdapter extends BaseAdapter {

	private ArrayList<Flashcard> _flashcards;
	private Context _context;
	
	public InputListAdapter(Context context, ArrayList<Flashcard> flashcards) {
		_flashcards = flashcards;
		_context = context;
	}
	
	@Override
	public int getCount() {
		return _flashcards.size();
	}

	@Override
	public Object getItem(int position) {
		return _flashcards.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_element_input, parent, false);
		Typeface tf_normal = Typeface.createFromAsset(_context.getAssets(),"century_gothic.ttf");
		setTypeface(rowView, tf_normal);
		return rowView;
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
