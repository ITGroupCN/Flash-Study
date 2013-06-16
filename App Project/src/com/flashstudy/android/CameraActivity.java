package com.flashstudy.android;

import android.os.Bundle;
import android.app.Activity;


import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteSession.EvernoteService;

public class CameraActivity extends Activity {

	// Connect to evernote
	private static final String CONSUMER_KEY = "shravvmehtaa";
	private static final String CONSUMER_SECRET = "fde31f0694d0134d";
	//private static final EvernoteService EVERNOTE_HOST = EvernoteService.SANDBOX; // Change SANDBOX to PRODUCTION later
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
	
	public void onCreate(Bundle savedInstanceState) {
		
		EvernoteSession mEvernoteSession = EvernoteSession.getInstance(getApplicationContext(), CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_SERVICE);
		
		
	}
	
	//private void setupSession() {
	//   mEvernoteSession = EvernoteSession.getInstance(this, CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_HOST);
	//}
}
