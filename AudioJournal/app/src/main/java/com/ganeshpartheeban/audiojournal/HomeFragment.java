package com.ganeshpartheeban.audiojournal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button start , stop ;
    Spinner spinner;

    String format;

    public HomeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        start = (Button) view.findViewById(R.id.start);
        stop = (Button) view.findViewById(R.id.stop);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                format = String.valueOf(spinner.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
                format=".mp4";
            }
        });


        return view;
    }

    @Override
    public void onClick(View v)
    {
        if(v == start)
        {

        }
        if(v == stop)
        {

        }
    }

}
