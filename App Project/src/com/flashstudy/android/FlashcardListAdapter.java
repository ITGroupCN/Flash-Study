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

import com.flashstudy.flashcard.Set;

public class FlashcardListAdapter extends BaseAdapter {

	private Context _context;
	private ArrayList<Set> _sets;

	public FlashcardListAdapter(Context context, ArrayList<Set> sets) {
		_context = context;
		_sets = sets;
	}
	
	@Override
	public int getCount() {
		return _sets.size();
	}

	@Override
	public Object getItem(int position) {
		return _sets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Set set = _sets.get(position);
		
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_element_flashcard, parent, false);
		Typeface tf_normal = Typeface.createFromAsset(_context.getAssets(),"century_gothic.ttf");
		Typeface tf_italic = Typeface.createFromAsset(_context.getAssets(),"century_gothic_italic.ttf");
		setTypeface(rowView, tf_normal);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
		TextView dateCreated = (TextView) rowView.findViewById(R.id.ListElementFlashcard_dateCreated);
		dateCreated.setText(dateFormat.format(set.getDateCreated()) + ":");
		
		TextView description = (TextView) rowView.findViewById(R.id.ListElementFlashcard_description);
		description.setText(set.getDescription());
		setTypeface(description, tf_italic);
		
		((TextView) rowView.findViewById(R.id.ListElementFlashcard_name)).setText(set.getName());
		((TextView) rowView.findViewById(R.id.ListElementFlashcard_type)).setText("(" + set.getType().toString() + ")");
		((TextView) rowView.findViewById(R.id.ListElementFlashcard_numFlashcard)).setText("3");

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
