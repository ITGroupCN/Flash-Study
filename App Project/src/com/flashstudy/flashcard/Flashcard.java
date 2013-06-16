package com.flashstudy.flashcard;

public class Flashcard {

	private String _term, _definition;
	
	public Flashcard(String term, String definition) {
		_term = term;
		_definition = definition;
	}
	
	public String getTerm() {
		return _term;
	}
	
	public String getDefinition() {
		return _definition;
	}
	
	public void setTerm(String term) {
		_term = term;
	}
	
	public void setDefinition(String definition) {
		_definition = definition;
	}
	
	@Override
	public String toString() {
		return _term + ":" + _definition;
	}
}
