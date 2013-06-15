package com.flashstudy.android;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.flashstudy.flashcard.Flashcard;

public class FlashcardListAdapter extends ArrayAdapter<Set> {

	private Context _context;
	private Set[] _flashcards;

	public FlashcardListAdapter(Context context, Set[] flashcards) {
		super
		_context = context;
		_flashcards = flashcards;
	}
}
