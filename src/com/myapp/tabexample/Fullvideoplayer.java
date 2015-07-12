package com.myapp.tabexample;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Fullvideoplayer extends Activity {

	private VideoView mVideoView;
	private MediaController mController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullvideo);
		
		Intent i = getIntent();
		i.getStringExtra("myUri");
		int pos = i.getIntExtra("pos", 0);
		
		Uri myuri = Uri.parse(i.getStringExtra("myUri"));
		
		mVideoView = (VideoView) findViewById(R.id.videoView1);
        mVideoView.setVideoURI(myuri);
		
		mController = new MediaController(this, false);
		mVideoView.setMediaController(mController);
	    mVideoView.seekTo(pos);
	    
		mVideoView.requestFocus();
		mVideoView.start();
		
		
	}
	public int Updatepos()
	{
		return mVideoView.getCurrentPosition();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	
	}

}
