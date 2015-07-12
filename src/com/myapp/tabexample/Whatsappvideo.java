package com.myapp.tabexample;

import com.myapp.tabexample.Videofragmentsec.MyCursorAdapter;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Whatsappvideo extends ListFragment{

	SimpleCursorAdapter adapter;
	final Uri mediaSrc = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	String selection= MediaStore.Video.Media.ALBUM+" "+"LIKE"+" "+"'%what%'";
   
	String[] param = {MediaStore.Video.Media.ALBUM};
	public	String size;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Bundle data = getArguments();
		//int index = data.getInt(getTag());
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Cursor cursor = adapter.getCursor();
		cursor.moveToPosition(position);
		
		String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
		int intID = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));  

		Uri playableUri
			= Uri.withAppendedPath(mediaSrc, _id);
		
		String  str = playableUri.toString();
		//MainActivity mainActivity = (MainActivity)getActivity();
		//mainActivity.myPlayerFragment.setTargetUri(playableUri, intID);
		
		Intent i = new Intent(getActivity(),Videopage.class);
		i.putExtra("Class", "whatsappvideo");
		i.putExtra("_id", _id);
		i.putExtra("intID", intID);
		i.putExtra("playableUri", str);
		startActivity(i);
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
        String[] from = {
        		MediaStore.MediaColumns.TITLE,MediaStore.MediaColumns.SIZE};
        int[] to = {
        		android.R.id.text1,android.R.id.text2};

       // cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
        
        Cursor cursor = getActivity().managedQuery(
        		mediaSrc, 
        		null, 
        		selection, 
        		null, 
        		MediaStore.Audio.Media.TITLE);
        
        adapter = new MyCursorAdapter(getActivity(),
        		R.layout.row, cursor, from, to);
        setListAdapter(adapter);
        
    // Toast.makeText(getActivity(), selection, Toast.LENGTH_LONG).show();
	}
	
	public class MyCursorAdapter extends SimpleCursorAdapter{
		public MyCursorAdapter(Context context, int layout, Cursor c,
				String[] from, int[] to) {
			super(context, layout, c, from, to);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater=getActivity().getLayoutInflater();
			View row=inflater.inflate(R.layout.row, parent, false);
			TextView text1 = (TextView)row.findViewById(R.id.text1);
			TextView text2 = (TextView)row.findViewById(R.id.text2);
			ImageView image1 = (ImageView)row.findViewById(R.id.image1);
			
			
			
			
			Cursor cursor = adapter.getCursor();
			cursor.moveToPosition(position);
			
			//Title
			String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
			text1.setText(title);
			
			//size
		 size = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
		  
			text2.setText(size );
			
			//Toast.makeText(getActivity(),size, Toast.LENGTH_LONG).show();
			
			//Thumbnail
			BitmapFactory.Options options = new BitmapFactory.Options();  
			options.inDither = false;  
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;  
			Bitmap bitmapThumb = MediaStore.Video.Thumbnails.getThumbnail(
				getActivity().getContentResolver(), 
				cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)),  
				Images.Thumbnails.MINI_KIND, 
				options);
			
			image1.setImageBitmap(bitmapThumb);
			
			return row;		
		}
	}

}
