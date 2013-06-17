package com.flashstudy.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashstudy.flashcard.Flashcard;
import com.flashstudy.flashcard.FlashcardType;
import com.flashstudy.flashcard.Set;
import com.googlecode.tesseract.android.TessBaseAPI;

public class CameraActivity extends Activity {
	
	private ArrayList<Flashcard> _flashcards;
	private InputListAdapter _inputListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		try {
			//Bitmap terms = createBitmap("/sdcard/Documents/phototest.gif");
			//Bitmap terms = createBitmap("/sdcard/Documents/terms.gif");
			Bitmap terms = createBitmap("/sdcard/Documents/phototesta1.gif");
			String termsResult = ocr(terms);
			String[] termList = termsResult.split("\n");
			
			//Bitmap defs  = createBitmap("/sdcard/Documents/definitions.gif");
			Bitmap defs  = createBitmap("/sdcard/Documents/phototesta2.gif");
			String defsResult = ocr(defs);
			String[] defList = defsResult.split("\n");
			int numRows = (termList.length < defList.length) ? termList.length : defList.length;
			
			_flashcards = new ArrayList<Flashcard>();
			for(int i = 0; i < numRows; i++)
				_flashcards.add(new Flashcard(termList[i], defList[i]));
			
			_inputListAdapter = new InputListAdapter(this, _flashcards, true);
			ListView view = (ListView) findViewById(R.id.CameraActivity_inputList);
			view.setAdapter(_inputListAdapter);
			
		} catch(Exception e) {
			Log.i("ERROR", e.toString());
		}
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		((TextView) findViewById(R.id.CameraActivity_nameText)).setTypeface(tf);
		
		final EditText nameInput = (EditText) findViewById(R.id.CameraActivity_nameInput);
		nameInput.setTypeface(tf);
		
		((TextView) findViewById(R.id.CameraActivity_descText)).setTypeface(tf);
		
		final EditText descInput = (EditText) findViewById(R.id.CameraActivity_descInput);
		descInput.setTypeface(tf);
		
		((TextView) findViewById(R.id.CameraActivity_term)).setTypeface(tf);
		((TextView) findViewById(R.id.CameraActivity_definition)).setTypeface(tf);
		
		
		Button submitButton = (Button) findViewById(R.id.CameraActivity_submit);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Set set = new Set(nameInput.getText().toString(), _inputListAdapter.getFlashcards(), 
						new Date(), FlashcardType.ENGLISH, descInput.getText().toString());
				
				try {
					FileOutputStream writer = new FileOutputStream(new File("/sdcard/Documents/sets.txt"), true);
					PrintStream printer = new PrintStream(writer);
					
					printer.println(set.toString());
				} catch(IOException e) {
					Log.i("FormEntryActivity@submit", "IOError: " + e.toString());
				}
				
				finish();
			}
		});
		LinearLayout header = (LinearLayout) findViewById(R.id.CameraActivity_header);
		ImageView homeButton = (ImageView) header.findViewById(R.id.Header_logo);
		homeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(CameraActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView formButton = (ImageView) header.findViewById(R.id.Header_formUpload);
		formButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(CameraActivity.this, FormEntryActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView cameraButton = (ImageView) header.findViewById(R.id.Header_cameraUpload);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/Documents/terms.gif"))); // set the image file name
				startActivityForResult(intent, 100);
				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 100) {
	        if (resultCode == RESULT_OK) {
	        	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/Documents/definitions.gif"))); // set the image file name
				startActivityForResult(intent, 101);
	        }
	    } else if(requestCode == 101) {
	    	if(resultCode == RESULT_OK) {
	    		Intent intent = new Intent(CameraActivity.this, CameraActivity.class);
	        	startActivity(intent);
	    	}
	    }
	}
	
	public void onAddElement(View view) {
		_flashcards.add(new Flashcard("", ""));
		_inputListAdapter.notifyDataSetChanged();
	}
	
	public Bitmap createBitmap(String filePath) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(filePath)));
		return bitmap.copy(Bitmap.Config.ARGB_8888, true);
	}

	public String ocr(Bitmap bitmap) {
		TessBaseAPI baseApi = new TessBaseAPI();
		// DATA_PATH = Path to the storage
		// lang for which the language data exists, usually "eng"
		
		baseApi.init("/sdcard/Documents", "eng"); baseApi.setImage(bitmap);
		String recognizedText = baseApi.getUTF8Text();
		baseApi.end();
		bitmap.recycle();
		return recognizedText;
	}
}

/*
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
}*/
