package com.ganeshpartheeban.audiojournal;


import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button startrec , stoprec ;
    Spinner spinner;

    String format;

    public MediaRecorder mediaRecorder;
    public String Storagepath;

    static final String AB = "abcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();


    public HomeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Storagepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File audioVoice = new File(Storagepath + "/Audio Journal");
        if(!audioVoice.exists()){
            audioVoice.mkdir();
        }

        startrec = (Button) view.findViewById(R.id.startrecording);
        stoprec = (Button) view.findViewById(R.id.stoprecording);

        startrec.setOnClickListener(this);
        stoprec.setOnClickListener(this);


        spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                format = String.valueOf(spinner.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
                format=".mp4";
            }
        });
        Storagepath = Storagepath+ "/Audio Journal/" + generateVoiceFilename(6) +format;
        initializeMediaRecord();


        return view;
    }

    @Override
    public void onClick(View v)
    {
        if(v == startrec)
        {

            if(mediaRecorder == null)
            {
                initializeMediaRecord();
            }
            startAudioRecording();
        }
        if(v == stoprec)
        {
           stopAudioRecording();
        }
    }

    public String generateVoiceFilename( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public void startAudioRecording(){
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startrec.setEnabled(false);
    }

    public void stopAudioRecording(){
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        startrec.setEnabled(true);
    }

    public void initializeMediaRecord(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(Storagepath);
    }

}
