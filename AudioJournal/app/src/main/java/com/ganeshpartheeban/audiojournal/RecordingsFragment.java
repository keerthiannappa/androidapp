package com.ganeshpartheeban.audiojournal;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordingsFragment extends Fragment {

    MediaPlayer mediaPlayer;
    private List<String> fileList = new ArrayList<String>();
    ListView listView;

    public RecordingsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recordings, container, false);

        listView = (ListView) view.findViewById(R.id.listview);

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Audio Journal");
        ListDir(root);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String audiojournal = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Audio Journal/"+((TextView) view).getText().toString();

                // Launching new Activity on selecting single List Item
                playLastStoredAudioMusic(audiojournal);
                mediaPlayerPlaying();


            }
        });


        return view;
    }

    void ListDir(File f) {
        File[] files = f.listFiles();
        fileList.clear();
        for (File file : files) {
            String s=file.getPath();
            s=s.substring(s.lastIndexOf("/")+1);
            fileList.add(s);
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(getContext(), R.layout.list_item, fileList);
        listView.setAdapter(directoryList);
    }

    private void playLastStoredAudioMusic(String audiojournal){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audiojournal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }

    private void mediaPlayerPlaying(){
        if(!mediaPlayer.isPlaying()){
            stopAudioPlay();
        }
    }

    private void stopAudioPlay(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
