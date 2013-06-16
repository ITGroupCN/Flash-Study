package com.flashstudy.android;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		Data data = new Data();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.title);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();
			
			data.setBody(byteArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Resource resource = new Resource();
		resource.setMime("image/png");
		resource.setData(data);
		
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(resource);
		
		note.setResources(resources);
        //Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping
		
				
		
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
