package com.flashstudy.android;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flashstudy.flashcard.Flashcard;

public class InputListAdapter extends BaseAdapter {

	private ArrayList<Flashcard> _flashcards;
	private LayoutInflater _inflater;
	private Typeface _tf;
	private Context _context;
	private boolean camera;
	
	public InputListAdapter(Context context, ArrayList<Flashcard> flashcards) {
		_flashcards = flashcards;
		_inflater = LayoutInflater.from(context);
		_context = context;
		_tf = Typeface.createFromAsset(context.getAssets(),"century_gothic.ttf");
	}
	
	public InputListAdapter(Context context, ArrayList<Flashcard> flashcards, boolean _camera) {
		_flashcards = flashcards;
		_inflater = LayoutInflater.from(context);
		_context = context;
		_tf = Typeface.createFromAsset(context.getAssets(),"century_gothic.ttf");
		camera = _camera;
	}
	
	public ArrayList<Flashcard> getFlashcards() {
		return _flashcards;
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
		ViewHolder holder;
		
		if(convertView == null || convertView.getTag() == null) {
			convertView = _inflater.inflate(R.layout.list_element_input, null);
			holder = new ViewHolder();
			holder._termField = (EditText) convertView.findViewById(R.id.ListElementInput_term);
			holder._definitionField = (EditText) convertView.findViewById(R.id.ListElementInput_definition);
			setTypeface(convertView, _tf);
			
			if(camera && position < _flashcards.size()) {
				holder._termField.setText(_flashcards.get(position).getTerm());
				holder._definitionField.setText(_flashcards.get(position).getDefinition());
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			_flashcards.get(position).setTerm(holder._termField.getText().toString());
			_flashcards.get(position).setDefinition(holder._definitionField.getText().toString());
		}
		
		//if(position == selectedListItem) {  
        //    holder.backgroundView.setBackgroundResource(R.drawable.and_gray_bg_listing_selected);
		//} else {
        //    holder.backgroundView.setBackgroundResource(R.drawable.and_gray_bg_listing);
		//}
		
		
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
	
	static class ViewHolder {
		EditText _termField, _definitionField;
	}
}
