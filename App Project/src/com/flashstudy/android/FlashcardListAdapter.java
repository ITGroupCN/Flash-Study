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

	private LayoutInflater _inflater;
	private Context _context;
	private ArrayList<Set> _sets;
	private Typeface _tf, _tfItalic;
	
	public FlashcardListAdapter(Context context, ArrayList<Set> sets) {
		_context = context;
		_sets = sets;
		
		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_tf = Typeface.createFromAsset(_context.getAssets(),"century_gothic.ttf");
		_tfItalic = Typeface.createFromAsset(_context.getAssets(), "century_gothic_italic.ttf");
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
		
        convertView = _inflater.inflate(R.layout.list_element_flashcard, null);
        TextView nameField = (TextView) convertView.findViewById(R.id.ListElementFlashcard_name);
        TextView descField = (TextView) convertView.findViewById(R.id.ListElementFlashcard_description);
		TextView dateField = (TextView) convertView.findViewById(R.id.ListElementFlashcard_dateCreated);
		TextView typeField = (TextView) convertView.findViewById(R.id.ListElementFlashcard_type);
		TextView numField = (TextView) convertView.findViewById(R.id.ListElementFlashcard_numFlashcard);
			
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
		dateField.setText(dateFormat.format(set.getDateCreated()) + ":");
		descField.setText(set.getDescription());
		nameField.setText(set.getName());
		typeField.setText("(" + set.getType().toString() + ")");
		numField.setText("" + set.getAllFlashcards().size());

		setTypeface(convertView, _tf);
		setTypeface(descField, _tfItalic);
		
		return convertView;
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
