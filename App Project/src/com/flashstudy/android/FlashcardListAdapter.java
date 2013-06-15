package com.flashstudy.android;

import android.content.Context;
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
		
		((TextView) rowView.findViewById(R.id.ListElement_name)).setText(set.getName());
		((TextView) rowView.findViewById(R.id.ListElement_dateCreated)).setText(set.getDateCreated().toString());
		((TextView) rowView.findViewById(R.id.ListElement_description)).setText(set.getDescription());
		((TextView) rowView.findViewById(R.id.ListElement_type)).setText(set.getType().toString());
		((TextView) rowView.findViewById(R.id.ListElement_numFlashcard)).setText("3");

		return rowView;
	}
}
