package com.flashstudy.android;

import android.os.Bundle;

import com.evernote.client.android.EvernoteSession.EvernoteService;

public class CameraActivity {

	// Connect to evernote
	private static final String CONSUMER_KEY = "shravvmehtaa";
	private static final String CONSUMER_SECRET = "fde31f0694d0134d";
	private static final EvernoteService EVERNOTE_HOST = EvernoteService.SANDBOX; // Change SANDBOX to PRODUCTION later
	
	public void onCreate(Bundle savedInstanceState) {
		
		
	}
	
	private void setupSession() {
	    mEvernoteSession = EvernoteSession.getInstance(this, CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_HOST);
	}

	
}
