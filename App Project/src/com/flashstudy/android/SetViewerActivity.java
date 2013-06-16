package com.flashstudy.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.flashstudy.flashcard.Flashcard;
import com.flashstudy.flashcard.Set;

public class SetViewerActivity extends Activity {

	private TextView _posText;
	private Typeface _tf, _tfItalic;
	private int _pos;
	private Set _set;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_viewer);
		
		_set = Set.fromString((String) this.getIntent().getExtras().get("set"));
		
		((TextView) findViewById(R.id.SetViewerActivity_setName)).setText(_set.getName());
		_posText = (TextView) findViewById(R.id.SetViewerActivity_pos);
		_posText.setText((_pos + 1) + "/" + _set.getAllFlashcards().size());
	
		LinearLayout layout = (LinearLayout) findViewById(R.id.SetViewActivity_layout);
		_tf = Typeface.createFromAsset(getAssets(),"century_gothic.ttf");
		_tfItalic = Typeface.createFromAsset(getAssets(),"century_gothic_italic.ttf");
		setTypeface(layout, _tf);
		
		LinearLayout header = (LinearLayout) findViewById(R.id.SetViewActivity_header);
		ImageView homeButton = (ImageView) header.findViewById(R.id.Header_logo);
		homeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(SetViewerActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView formButton = (ImageView) header.findViewById(R.id.Header_formUpload);
		formButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(SetViewerActivity.this, FormEntryActivity.class);
				startActivity(intent);
			}
		});
		
		ImageView cameraButton = (ImageView) header.findViewById(R.id.Header_cameraUpload);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(SetViewerActivity.this, "CAMERA BUTTON", Toast.LENGTH_SHORT).show();
			}
		});
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.SetViewerActivity_flipper);
		FlashcardView view = new FlashcardView(this);
		frame.addView(view);
		view.setFlashcards(_set.getAllFlashcards());
	}
	
	private void setTypeface(View view, Typeface typeface) {
		if(!(view instanceof ViewGroup)) {
			if(view instanceof TextView)
				((TextView) view).setTypeface(typeface);
			return;
		}
		
		ViewGroup viewGroup = (ViewGroup) view;
		for(int i = 0; i < viewGroup.getChildCount(); i++)
			setTypeface(viewGroup.getChildAt(i), typeface);
	}
	
	public class FlashcardView extends ViewFlipper {
		
		private static final float CLICK_DIST=10;
		private static final float SWIPE_DIST=100;
		
		private View _flashcardFront, _flashcardBack;
		private ArrayList<Flashcard> _flashcards;
		private LayoutInflater _inflater;
		private float _lastX;
		
		public FlashcardView(Context context) {
			super(context);
		}
		
		public FlashcardView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		
		public void setFlashcards(ArrayList<Flashcard> flashcards) {
			_flashcards = flashcards;
			this.getContext();
			_inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			redrawViewFlipper();
		}
		
		private void redrawViewFlipper() {
			this.removeAllViews();
			
			_flashcardFront = _inflater.inflate(R.layout.layout_flashcard_front, null);
			_flashcardBack  = _inflater.inflate(R.layout.layout_flashcard_back, null);
			
			
			TextView _flashcardFrontView = (TextView) _flashcardFront.findViewById(R.id.FlashcardFront_term);
			_flashcardFrontView.setText(_flashcards.get(_pos).getTerm());
			_flashcardFrontView.setTypeface(_tf);
			
			TextView _flashcardBackView = (TextView) _flashcardBack.findViewById(R.id.FlashcardBack_definition);
			_flashcardBackView.setText(_flashcards.get(_pos).getDefinition());
			_flashcardBackView.setTypeface(_tfItalic);
			
			_posText.setText((_pos + 1) + "/" + _set.getAllFlashcards().size());
			this.addView(_flashcardFront);
			this.addView(_flashcardBack);
			this.invalidate();
		}

		@Override
		public boolean onTouchEvent(MotionEvent e) {
			switch(e.getAction()) {
				case MotionEvent.ACTION_DOWN:
					_lastX = e.getX();
					return true;
				case MotionEvent.ACTION_UP:
					float deltaX = e.getX() - _lastX;
					if(Math.abs(deltaX) < CLICK_DIST) {
						this.showNext();
					} else if(Math.abs(deltaX) > SWIPE_DIST && deltaX < 0) {
						_pos = (_pos + 1 >= _flashcards.size()) ? 0 : _pos + 1;
						redrawViewFlipper();
					} else if(Math.abs(deltaX) > SWIPE_DIST && deltaX > 0) {
						_pos = (_pos - 1 < 0) ? _flashcards.size() - 1 : _pos - 1;
						redrawViewFlipper();
					}
					return true;
			}
			
			return false;
		}
	}

}
