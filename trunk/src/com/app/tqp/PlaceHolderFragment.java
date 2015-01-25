package com.app.tqp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceHolderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceHolderFragment newInstance(int sectionNumber) {
		PlaceHolderFragment fragment = new PlaceHolderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceHolderFragment() {
		songs = new ArrayList<String>();
	
	}

	final List<String> songs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_options, container, false);
		
		/* MDS:
		 * This is a piece of crap! it should be refactored completely
		 * using MVC and testing!
		 * No funcionan las sharedpreferences, no guardan dsp q cerras la app!!!
		 */
		
		final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		Set<String> tqpSongs = settings.getStringSet("tqpsongs", new HashSet<String>());
	
		for(String song : tqpSongs)
			songs.add(song);
		
        //refreshList();
		final ListView songsList = (ListView) rootView.findViewById(R.id.songsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
        		android.R.layout.simple_list_item_1, android.R.id.text1, arrayOf(songs));
        songsList.setAdapter(adapter);
		
        final EditText txtNewSong = (EditText) rootView.findViewById(R.id.newSongName);
        
        Button addButton = (Button) rootView.findViewById(R.id.btnAddSong);
        addButton.setOnClickListener(new View.OnClickListener()
        	{
        		@Override
        		public void onClick(View v){
      			
        			String newSongName = txtNewSong.getText().toString(); 
        			songs.add(newSongName);

        			SharedPreferences.Editor editor = settings.edit();
        			Set<String> songsPref = settings.getStringSet("tqpsongs", new HashSet<String>());
        			songsPref.add(newSongName);
    				editor.putStringSet("tqpsongs", songsPref);
    				boolean retState = editor.commit();
    				
        			
        			//refreshList();
        	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), 
        	        		android.R.layout.simple_list_item_1, android.R.id.text1, arrayOf(songs));
        	        songsList.setAdapter(adapter);
        			
        	        txtNewSong.getText().clear();
        		}
        	});
        
		//textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)) + " Motherfucker!");
		
        return rootView;
	}

	private void refreshList() {
		ListView songsList = (ListView) getActivity().findViewById(R.id.songsListView);
		// Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
        		android.R.layout.simple_list_item_1, android.R.id.text1, arrayOf(songs));


        // Assign adapter to ListView
        songsList.setAdapter(adapter);
	}

	private String[] arrayOf(List<String> anStringList) {
		 String array[] = new String[anStringList.size()];
		 return anStringList.toArray(array);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((OptionsActivity) activity).onSectionAttached(getArguments()
				.getInt(ARG_SECTION_NUMBER));
	}
}