package com.myapp.tabexample;


import android.R.color;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends Activity {

	TabHost mtabhost;
	private ActionBar actionBar;
	private String[] tabs = { "Top Rated", "Games", "Movies" };
	String mtag;
		
	VideoListFragment myVideoListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faltu);
        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        
       
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
		    Tab tabx = actionBar.newTab();
            tabx.setText("ALL");
			tabx.setTabListener(new TabListner(this,"tabx", VideoListFragment.class));
			actionBar.addTab(tabx);
			
			
			 Tab taby = actionBar.newTab();
	            taby.setText("Camera");
				taby.setTabListener(new TabListner(this,"taby", Videofragmentsec.class));
				actionBar.addTab(taby);
				
				 Tab tabz = actionBar.newTab();
		            tabz.setText("Sdcard");
					tabz.setTabListener(new TabListner(this,"tabz", VideoFragmentcard.class));
					actionBar.addTab(tabz);

					Tab tabw = actionBar.newTab();
		            tabw.setText("Whatsapp");
					tabw.setTabListener(new TabListner(this,"tabw", Whatsappvideo.class));
					actionBar.addTab(tabw);		
			if(savedInstanceState!=null)
			{
				int savedindex= savedInstanceState.getInt("INDEX");
				getActionBar().setSelectedNavigationItem(savedindex);
			}
    }
   
    @Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("INDEX", getActionBar().getSelectedNavigationIndex());
		
	}
    
   class TabListner extends ListFragment implements ActionBar.TabListener
    {
         Activity myActivity;
         String mytag;
         Class myClass;
         Class myClarr;
         
          TabListner(Activity activity , String tag, Class cls)
         {
        	 myActivity = activity;
        	 mytag = tag;
        	 myClass = cls;
         }
         
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
			ListFragment myFragment = (ListFragment) myActivity.getFragmentManager().findFragmentByTag(mytag);
			
			if(myFragment==null)
			{
				myFragment = (ListFragment) ListFragment.instantiate(myActivity,myClass.getName());
				ft.add(R.id.content, myFragment, mytag);
				
				//Toast.makeText(MainActivity.this, mytag.toString(), Toast.LENGTH_LONG).show();
			}
			else
			{
				ft.attach(myFragment);
			}
				
				
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
			ListFragment myFragment = (ListFragment) myActivity.getFragmentManager().findFragmentByTag(mytag);
			
			if(myFragment!=null)
			{
				ft.detach(myFragment);
				//Toast.makeText(MainActivity.this,"detached", Toast.LENGTH_LONG).show();
			}
		}
    	
    }
	

   
    
}
