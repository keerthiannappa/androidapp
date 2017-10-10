package com.diyasstudios.theavenue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    ListView lv;
    ArrayAdapter adapter;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_event, container, false);
        //list view
        String[] adobe_products = getActivity().getResources().getStringArray(R.array.adobe_products);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.list_item, adobe_products);
        lv = (ListView) view.findViewById(R.id.listview);
        lv.setAdapter(adapter);
        return view;
    }

}
