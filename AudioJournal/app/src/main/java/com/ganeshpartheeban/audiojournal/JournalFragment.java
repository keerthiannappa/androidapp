package com.ganeshpartheeban.audiojournal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFragment extends Fragment implements View.OnClickListener {

    Button university , global;
    WebView webview;
    public JournalFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_journal, container, false);
        university = (Button) view.findViewById(R.id.University);
        global = (Button) view.findViewById(R.id.Global);
        webview = (WebView) view.findViewById(R.id.webview);
        university.setOnClickListener(this);
        global.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v)
    {
        if(v == university)
        {
            webview.loadUrl("https://www.hindustanuniv.ac.in");
        }
        if(v == global)
        {
            webview.loadUrl("https://www.hindustanuniv.ac.in");
        }
    }


}
