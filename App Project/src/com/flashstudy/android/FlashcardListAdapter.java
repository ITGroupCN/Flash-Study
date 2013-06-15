package com.flashstudy.android;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flashstudy.flashcard.Set;

public class FlashcardListAdapter extends ArrayAdapter<Set> {

	private Context _context;
	private Set[] _sets;

	public FlashcardListAdapter(Context context, Set[] sets) {
		super(context, R.layout.list_element_flashcard, sets);
		_context = context;
		_sets = sets;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Set set = _sets[position];
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_element_flashcard, parent, false);
		Typeface tf = Typeface.createFromAsset(_context.getAssets(),"century_gothic.ttf");
		setTypeface(rowView, tf);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
		
		((TextView) rowView.findViewById(R.id.ListElement_name)).setText(set.getName());
		((TextView) rowView.findViewById(R.id.ListElement_dateCreated)).setText(dateFormat.format(set.getDateCreated()));
		((TextView) rowView.findViewById(R.id.ListElement_description)).setText(set.getDescription());
		((TextView) rowView.findViewById(R.id.ListElement_type)).setText(set.getType().toString());
		((TextView) rowView.findViewById(R.id.ListElement_numFlashcard)).setText("3");

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
