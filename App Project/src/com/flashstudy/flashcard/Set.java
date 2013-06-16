package com.flashstudy.flashcard;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;

public class Set {

	private ArrayList<Flashcard> _flashcards;
	private String _name, _description;
	private FlashcardType _type;
	private Date _dateCreated;
	
	public Set(String name, ArrayList<Flashcard> flashcards, Date dateCreated) {
		_name = name;
		_flashcards = flashcards;
		_dateCreated = dateCreated;
	}
	
	public Set(String name, ArrayList<Flashcard> flashcards, Date dateCreated, FlashcardType type) {
		_name = name;
		_flashcards = flashcards;
		_dateCreated = dateCreated;
		_type = type;
	}
	
	public Set(String name, ArrayList<Flashcard> flashcards, Date dateCreated, FlashcardType type, String description) {
		_name = name;
		_flashcards = flashcards;
		_dateCreated = dateCreated;
		_type = type;
		_description = description;
	}
	
	// Add, remove, and get all Flashcards
	public void addFlashcard(Flashcard flashcard) {
		_flashcards.add(flashcard);
	}
	
	public Flashcard getFlashcard(int index) {
		return _flashcards.get(index);
	}
	
	public ArrayList<Flashcard> getAllFlashcards() {
		return _flashcards;
	}
	
	// Get and set the Set name
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}

	// Get and set the Set description
	public String getDescription() {
		return _description;
	}
	
	public void setDescription(String description) {
		_description = description;
	}
	
	// Get and set the FlashcardType
	public FlashcardType getType() {
		return _type;
	}
	
	public void setType(FlashcardType type) {
		_type = type;
	}
	
	// Get and set Date created
	public Date getDateCreated() {
		return _dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		_dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		for(Flashcard card : _flashcards) {
			
		}
		return _flashcards.toString();
	}
	
	public static Set fromString() {
		return new Set()
	}
}
