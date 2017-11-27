package com.diyasstudios.theavenue;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    ListView lv;
    ArrayList<DataModel> dataModels;
    private static CustomAdapter adapter;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_event, container, false);
        //list view

        lv = (ListView) view.findViewById(R.id.listview);
        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("issue1", "News", "1","September 23, 2008"));
        dataModels.add(new DataModel("issue2", "News", "2","February 9, 2009"));
        dataModels.add(new DataModel("issue3", "News", "3","April 27, 2009"));
        dataModels.add(new DataModel("issue4","News","4","September 15, 2009"));
        dataModels.add(new DataModel("issue5", "News", "5","October 26, 2009"));
        dataModels.add(new DataModel("issue6", "News", "8","May 20, 2010"));
        dataModels.add(new DataModel("issue7", "News", "9","December 6, 2010"));
        dataModels.add(new DataModel("issue8","News","11","February 22, 2011"));
        dataModels.add(new DataModel("issue9", "News", "14","October 18, 2011"));
        dataModels.add(new DataModel("issue10", "News", "16","July 9, 2012"));
        dataModels.add(new DataModel("issue11", "News", "19","October 31, 2013"));
        dataModels.add(new DataModel("issue12","News","21","November 12, 2014"));
        dataModels.add(new DataModel("issue13", "News", "23","October 5, 2015"));

        adapter= new CustomAdapter(dataModels,getApplicationContext());


        lv.setAdapter(adapter);
        return view;
    }

    private Context getApplicationContext() {
        return this.getContext();
    }

}
