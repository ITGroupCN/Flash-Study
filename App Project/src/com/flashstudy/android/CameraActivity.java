package com.flashstudy.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteSession.EvernoteService;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.client.android.OnClientCallback;
import com.evernote.edam.type.Data;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Resource;
import com.evernote.thrift.transport.TTransportException;

public class CameraActivity extends Activity {

	// Connect to evernote
	private static final String CONSUMER_KEY = "flashstudy";
	private static final String CONSUMER_SECRET = "bb01d8276f0078b2";
	private static final EvernoteService EVERNOTE_HOST = EvernoteService.SANDBOX; // Change SANDBOX to PRODUCTION later
	private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
	
	private EvernoteSession mEvernoteSession;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		mEvernoteSession = EvernoteSession.getInstance(this, CONSUMER_KEY, CONSUMER_SECRET, EVERNOTE_SERVICE);
		mEvernoteSession.authenticate(this);
		Toast.makeText(this, "fuck yea", Toast.LENGTH_LONG).show();	
	}
	
	public void onSaveNote(View view) {
		saveNote();
	}
	
	private void saveNote() {
		Note note = new Note();
		note.setTitle("Shrav is a bitch");
		note.setContent(EvernoteUtil.NOTE_PREFIX + "Shrav likes men" + EvernoteUtil.NOTE_SUFFIX);
		Resource resource = new Resource();
		resource.setMime("image/png");
		resource.setData(data)
		Data data = new Data();
		data.
		try {
			mEvernoteSession.getClientFactory().createNoteStoreClient()
					.createNote(note, new OnClientCallback<Note>() {
						@Override
						public void onSuccess(Note data) {
							Toast.makeText(getApplicationContext(),
									"FUCK YES", Toast.LENGTH_LONG)
									.show();
						}

						@Override
						public void onException(Exception exception) {
							Toast.makeText(getApplicationContext(),
									"FUCK NO",
									Toast.LENGTH_LONG).show();
						}
					});
		} catch (TTransportException exception) {
			Toast.makeText(getApplicationContext(),
					"ERROR SHRAV'S FAULT", Toast.LENGTH_LONG)
					.show();
		}
		
	}
}
