package com.app.tqp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_options, container, false);
		
		ListView songsList = (ListView) rootView.findViewById(R.id.songsListView);
		
        // Defined Array values to show in ListView
        String[] values = new String[] { "Tema sin nombre", 
                                         "Carrusel",
                                         "Sin Mirar",
                                         "Volviendo al final", 
                                        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
        		android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        songsList.setAdapter(adapter); 
		
		//textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)) + " Motherfucker!");
		
        return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((OptionsActivity) activity).onSectionAttached(getArguments()
				.getInt(ARG_SECTION_NUMBER));
	}
}