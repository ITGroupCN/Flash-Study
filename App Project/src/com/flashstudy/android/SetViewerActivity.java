package com.flashstudy.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.flashstudy.flashcard.Set;

public class SetViewerActivity extends Activity {

	private View _previous, _current, _next;
	private Set _set;
	private int _pos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_viewer);
		
		_set = (Set) this.getIntent().getExtras().get("set");
	}
}
