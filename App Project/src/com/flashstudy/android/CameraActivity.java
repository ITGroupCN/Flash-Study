package com.flashstudy.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.evernote.client.android.EvernoteSession;

public class CameraActivity extends Activity {

	// Connect to evernote
	private static final String CONSUMER_KEY = "shravvmehtaa";
	private static final String CONSUMER_SECRET = "fde31f0694d0134d";
	//private static final EvernoteService EVERNOTE_HOST = EvernoteService.SANDBOX; // Change SANDBOX to PRODUCTION later
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
	
	public void onCreate(Bundle savedInstanceState) {
		
		EvernoteSession mEvernoteSession = EvernoteSession.getInstance(getApplicationContext(), CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_SERVICE);
		mEvernoteSession.authenticate(this);
		
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		// Update UI when oauth activity returns result
		case EvernoteSession.REQUEST_CODE_OAUTH:
		   	if (resultCode == Activity.RESULT_OK) {
		   		// Authentication was successful, do what you need to do in your app
		    }
		    break;
		}
	}

	
	//private void setupSession() {
	//   mEvernoteSession = EvernoteSession.getInstance(this, CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_HOST);
	//}
}
