package com.myapp.tabexample;

import java.io.IOException;
import java.security.Guard;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.VideoColumns;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.AudioManager;

public class PlayerFragment extends Fragment 
	implements SurfaceHolder.Callback , OnGestureListener {

	Uri targetUri = null;
	
	GestureDetector gd;
	MediaPlayer mediaPlayer;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	boolean pausing = false;
	int once = 0;
	VideoView vv;
	
	TextView mediaUri;
	Button buttonPlayVideo, buttonPauseVideo , btnfwd,full,btnprev,fullpot;
	private MediaController mController;
	public int pos;
	public int pos2;
	AudioManager mAudioManager;
	//ImageView thumbView;
	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		
		
	    
	    
		mController = new MediaController(getActivity(), false);
		
		View view = inflater.inflate(R.layout.playerlayout, container, false);
		//mediaUri = (TextView)view.findViewById(R.id.mediauri);
		//buttonPlayVideo = (Button)view.findViewById(R.id.playvideoplayer);
		buttonPauseVideo = (Button)view.findViewById(R.id.pausevideoplayer);
		btnfwd = (Button) view.findViewById(R.id.btnfwd);
		btnprev = (Button) view.findViewById(R.id.btnprev);
		full = (Button) view.findViewById(R.id.full);
		fullpot = (Button) view.findViewById(R.id.fullpot);
		surfaceView = (SurfaceView)view.findViewById(R.id.surfaceview);
	
	//	
		
		
		getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(176, 144);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mediaPlayer = new MediaPlayer();
		
		
		
		if(targetUri!=null)
		{
			//Toast.makeText(getActivity(),"pikachuu", Toast.LENGTH_LONG).show();
		}else
		{
			//Toast.makeText(getActivity(),"rikachuu", Toast.LENGTH_LONG).show();
		}
		
		
		
		
	final Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
	
	surfaceView.setOnClickListener(new View.OnClickListener() {
		
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			if(targetUri != null){
				if(pausing){
					pausing = false;
					mediaPlayer.start();	
					
				}
				else{
					pausing = true;
					mediaPlayer.pause();
					
				}
			}
		}
		
		
	});
		
		
		
		buttonPauseVideo.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(targetUri != null){
					if(pausing){
						pausing = false;
						mediaPlayer.start();	
					}
					else{
						pausing = true;
						mediaPlayer.pause();	
					
					}
				}
			}});
		btnfwd.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(targetUri != null){
					int pos = mediaPlayer.getCurrentPosition();
					pos = pos+1000;
					mediaPlayer.seekTo(pos);
				}
			}});
		
		btnprev.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(targetUri != null){
					int pos = mediaPlayer.getCurrentPosition();
					pos = pos-1000;
					mediaPlayer.seekTo(pos);
				}
			}});
		
		full.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				pos = mediaPlayer.getCurrentPosition();
				
				Intent i = new Intent(getActivity(), VideoPlayerActivity.class);
				i.putExtra("myUri", targetUri.toString());
				i.putExtra("pos", pos);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				mediaPlayer.stop();
				startActivity(i);
				
			}});
		fullpot.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				pos = mediaPlayer.getCurrentPosition();
				
				Intent i = new Intent(getActivity(), Videoplayeractivitypotrait.class);
				i.putExtra("myUri", targetUri.toString());
				i.putExtra("pos", pos);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				mediaPlayer.stop();
				startActivity(i);
				
			}});
		return view;
	}

	
     @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onViewCreated(view, savedInstanceState);
    	
    	
    }
	


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	/*	Toast.makeText(getActivity(), "threr there1", Toast.LENGTH_SHORT).show();
		Bundle args = getActivity().getIntent().getExtras();
		if(args!=null)
		{
			args.getInt("pos2");
			System.out.println(args);
			Toast.makeText(getActivity(), "threr there", Toast.LENGTH_SHORT).show();
		}*/
		
		
		
		System.out.println("posssssssssssssssssssssittttttttttttionnnnn"+pos);
		 if(pos!=0)
		 {
			// Toast.makeText(getActivity(), "on activity created", Toast.LENGTH_LONG).show();
			 try {
					mediaPlayer.setDataSource(getActivity().getApplicationContext(), targetUri);
					 mediaPlayer.prepare();	
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 mediaPlayer.start();
			 mediaPlayer.seekTo(pos);
		 }
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		
		
		//mediaPlayer.setDisplay(surfaceHolder);
		try {
			mediaPlayer.setDataSource(getActivity().getApplicationContext(), targetUri);
			 mediaPlayer.prepare();	
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 mediaPlayer.start();
		
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        
		
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaPlayer.release();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		try {
			mediaPlayer.setDataSource(getActivity().getApplicationContext(), targetUri);
			 mediaPlayer.prepare();	
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 mediaPlayer.start();
		 mediaPlayer.seekTo(pos);
		getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(176, 144);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		mediaPlayer.setDisplay(surfaceHolder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(176, 144);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		mediaPlayer.setDisplay(surfaceHolder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTargetUri(Uri u, int id){
		targetUri = u;
		
		
		/*mediaUri.setText(
				"Uri: " + u.toString() + "\n" + 
				"ID: " + id);*/
		
		//Get Thumbnail
	/*	BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inDither = false;  
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;  
        Bitmap bitmapThumb = MediaStore.Video.Thumbnails.getThumbnail(
        		getActivity().getContentResolver(), 
        		id,  
        		Images.Thumbnails.MICRO_KIND, 
        		options);

		thumbView.setImageBitmap(bitmapThumb);*/
	}


	@Override
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
