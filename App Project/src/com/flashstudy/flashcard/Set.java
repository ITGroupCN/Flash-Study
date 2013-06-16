package com.flashstudy.flashcard;

import java.util.ArrayList;
import java.util.Date;

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
		String obj = _name+","+_dateCreated.getTime()+","+_type.toString()+","+_description;
		for(Flashcard card : _flashcards)
			obj += "," + card.toString();
		
		return obj;
	}
	
	public static Set fromString(String str) {
		String val[] = str.split(",");
		ArrayList<Flashcard> flashcards = new ArrayList<Flashcard>();
		
		String name = val[0];
		Date   date = new Date(Long.valueOf(val[1]));
		FlashcardType type = FlashcardType.valueOf(val[2]);
		String desc = val[3];
		
		for(int i = 4; i < val.length; i++)
			flashcards.add(Flashcard.fromString(val[i]));
		
		return new Set(name, flashcards, date, type, desc);
	}
}
