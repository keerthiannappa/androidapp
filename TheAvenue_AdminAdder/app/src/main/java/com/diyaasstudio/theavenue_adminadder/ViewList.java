package com.diyaasstudio.theavenue_adminadder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    ListView lv;
    ArrayList<AdminDetails> dataModels;
    private static CustomAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mref=database.getReference("User");
    public static final String TAG="EmailPassword";


    @Override
    protected void onStart() {
        super.onStart();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataModels.clear();
                for(DataSnapshot adminlist:dataSnapshot.getChildren())
                {
                    AdminDetails admins=adminlist.getValue(AdminDetails.class);
                    dataModels.add(admins);

                }
                adapter= new CustomAdapter(dataModels,ViewList.this);
                lv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        lv = (ListView) findViewById(R.id.listview);
        dataModels= new ArrayList<>();

    }
}
