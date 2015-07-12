package com.myapp.tabexample;



import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class Videopage extends Activity{

	
	PlayerFragment myPlayerFragment;
	public String title;
	public int pos2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	    
	    
		Intent i = getIntent();
		String Class = i.getStringExtra("Class");
	    i.getStringExtra("_id");
		i.getStringExtra("playableUri");
	    i.getDoubleExtra("intID", 0);
	   title =  i.getStringExtra("title");
	//   Toast.makeText(this, i.getStringExtra("title"), Toast.LENGTH_LONG).show();
	   
	   
	    if(Class.equals("VideoListfragment"))
        {
          
        //  Toast.makeText(this, Class, Toast.LENGTH_LONG).show();
          setContentView(R.layout.videopage);
         
        }
        if(Class.endsWith("Videofragmentsec"))
        {
        	//Toast.makeText(this, Class, Toast.LENGTH_LONG).show();
            setContentView(R.layout.videocamera);
        }
        if(Class.endsWith("Videofragmentcard"))
        {
        	//Toast.makeText(this, Class, Toast.LENGTH_LONG).show();
            setContentView(R.layout.videocard);
        }
        if(Class.endsWith("whatsappvideo"))
        {
        	setContentView(R.layout.whatsappvideo);
        }
	    
		//Toast.makeText(this, i.getStringExtra("playableUri"), Toast.LENGTH_LONG).show();
		//Toast.makeText(this,Class, Toast.LENGTH_LONG).show();
		Uri myuri = Uri.parse(i.getStringExtra("playableUri"));
		//Toast.makeText(this, myuri.toString(), Toast.LENGTH_LONG).show();
		
		FragmentManager fragmentManager = getFragmentManager();
		
        myPlayerFragment = (PlayerFragment)fragmentManager.findFragmentById(R.id.playerfragment);
        myPlayerFragment.setTargetUri(myuri,(int)i.getDoubleExtra("intID", 0));
           /* VideoListFragment myVideoListFragment;
        myVideoListFragment = (VideoListFragment)fragmentManager.findFragmentById(R.id.videolistfragment);
        Videofragmentsec myVideofragmentsec;
       myVideofragmentsec = (Videofragmentsec)fragmentManager.findFragmentById(R.id.videofragmentsec);
        */
    	
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(title);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		
	
		//Toast.makeText(this, pos2, Toast.LENGTH_LONG).show();
	}

}
