package com.flashstudy.flashcard;

import java.util.ArrayList;

public class Set {

	private ArrayList<Flashcard> _flashcards;
	
	public Set(ArrayList<Flashcard> flashcards) {
		_flashcards = flashcards;
	}
	
	public void addFlashcard(Flashcard flashcard) {
		_flashcards.add(flashcard);
	}
	
	public Flashcard getFlashcard(int index) {
		return _flashcards.get(index);
	}
	
	public ArrayList<Flashcard> getAllFlashcards() {
		return _flashcards;
	}
	
	@Override
	public String toString() {
		return _flashcards.toString();
	}
}
