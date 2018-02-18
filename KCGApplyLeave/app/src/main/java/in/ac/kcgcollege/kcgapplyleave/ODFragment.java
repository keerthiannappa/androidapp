package in.ac.kcgcollege.kcgapplyleave;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ODFragment extends Fragment {

    TextView ofname,ofreg,ofdays,offrom,ofto,ofstatus,ofreason,ofnodata;
    String ofsname,ofsdays,ofsfrom,ofsto,ofsreason,ofsstatus;
    FrameLayout frameLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Applications");
    DatabaseReference myRef= database.getReference("student");

    public ODFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_od, container, false);

        ofname=(TextView)view.findViewById(R.id.frag_od_name);
        ofreg=(TextView)view.findViewById(R.id.frag_od_reg);
        ofdays=(TextView)view.findViewById(R.id.frag_od_days);
        offrom=(TextView)view.findViewById(R.id.frag_od_from);
        ofto=(TextView)view.findViewById(R.id.frag_od_to);
        ofstatus=(TextView)view.findViewById(R.id.frag_od_status);
        ofreason=(TextView)view.findViewById(R.id.frag_od_reason);
        ofnodata=(TextView)view.findViewById(R.id.frag_od_nodata);
        frameLayout=(FrameLayout)view.findViewById(R.id.frag_od_layout);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("OD").hasChild(Status.sreg)){
                    ofsdays=dataSnapshot.child("OD").child(Status.sreg).child("days").getValue(String.class);
                    ofsfrom=dataSnapshot.child("OD").child(Status.sreg).child("from").getValue(String.class);
                    ofsto=dataSnapshot.child("OD").child(Status.sreg).child("to").getValue(String.class);
                    ofsreason=dataSnapshot.child("OD").child(Status.sreg).child("reason").getValue(String.class);
                    ofsstatus=dataSnapshot.child("OD").child(Status.sreg).child("status").getValue(String.class);
                    ofreg.setText(Status.sreg);
                    ofdays.setText(ofsdays);
                    offrom.setText(ofsfrom);
                    ofto.setText(ofsto);
                    ofreason.setText(ofsreason);
                    if(Integer.parseInt(ofsstatus)==0){
                        ofstatus.setText("Still Waiting");
                        ofstatus.setBackgroundColor(Color.RED);
                    }
                    if(Integer.parseInt(ofsstatus)==1){
                        ofstatus.setText("Confirmed by CT");
                        ofstatus.setBackgroundColor(Color.YELLOW);
                    }
                    if(Integer.parseInt(ofsstatus)==2){
                        ofstatus.setText("Confirmed by HOD");
                        ofstatus.setBackgroundColor(Color.GREEN);
                    }
                    if(Integer.parseInt(ofsstatus)==3){
                        ofstatus.setText("Rejected by CT");
                        ofstatus.setBackgroundColor(Color.RED);
                    }
                    if(Integer.parseInt(ofsstatus)==4){
                        ofstatus.setText("Rejected by HOD");
                        ofstatus.setBackgroundColor(Color.RED);
                    }
                }
                else{
                    frameLayout.setVisibility(View.INVISIBLE);
                    ofnodata.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    ofsname=dataSnapshot.child(Status.sreg).child("name").getValue(String.class);
                    ofname.setText(ofsname);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }



}
