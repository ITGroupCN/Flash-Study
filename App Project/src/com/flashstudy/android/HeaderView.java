package com.flashstudy.android;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HeaderView extends ViewGroup {

	public HeaderView(Context context) {
		super(context);
	}
	
	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_inflater.inflate(R.layout.layout_header, this, true);
		
		ImageView logo = (ImageView) this.findViewById(R.id.Header_logo);
		logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HeaderView.this.getContext(), MenuActivity.class);
				HeaderView.this.getContext().startActivity(intent);
			}
		});
		
		ImageView form = (ImageView) this.findViewById(R.id.Header_formUpload);
		form.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HeaderView.this.getContext(), FormEntryActivity.class);
				HeaderView.this.getContext().startActivity(intent);
			}
		});
		
		ImageView cam  = (ImageView) this.findViewById(R.id.Header_cameraUpload);
		cam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HeaderView.this.getContext(), CameraActivity.class);
				HeaderView.this.getContext().startActivity(intent);
			}
		});
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
}
