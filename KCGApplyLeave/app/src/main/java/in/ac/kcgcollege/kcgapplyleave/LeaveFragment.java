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


/**
 * A simple {@link Fragment} subclass.
 */
public class LeaveFragment extends Fragment {
    TextView lfname,lfreg,lfdays,lffrom,lfto,lfstatus,lfreason,lfnodata,lftype;
    String lfsname,lfsdays,lfsfrom,lfsto,lfsreason,lfsstatus,lfstype;
    FrameLayout frameLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Applications");
    DatabaseReference myRef= database.getReference("student");



    public LeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_leave, container, false);
        lfname=(TextView)view.findViewById(R.id.frag_leave_name);
        lfreg=(TextView)view.findViewById(R.id.frag_leave_reg);
        lfdays=(TextView)view.findViewById(R.id.frag_leave_days);
        lffrom=(TextView)view.findViewById(R.id.frag_leave_from);
        lfto=(TextView)view.findViewById(R.id.frag_leave_to);
        lfstatus=(TextView)view.findViewById(R.id.frag_leave_status);
        lfreason=(TextView)view.findViewById(R.id.frag_leave_reason);
        lfnodata=(TextView)view.findViewById(R.id.frag_leave_nodata);
        lftype=(TextView)view.findViewById(R.id.frag_leave_type);
        frameLayout=(FrameLayout)view.findViewById(R.id.frag_leave_layout);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Leave").hasChild(Status.sreg)){
                    lfsdays=dataSnapshot.child("Leave").child(Status.sreg).child("days").getValue(String.class);
                    lfsfrom=dataSnapshot.child("Leave").child(Status.sreg).child("from").getValue(String.class);
                    lfsto=dataSnapshot.child("Leave").child(Status.sreg).child("to").getValue(String.class);
                    lfsreason=dataSnapshot.child("Leave").child(Status.sreg).child("reason").getValue(String.class);
                    lfsstatus=dataSnapshot.child("Leave").child(Status.sreg).child("status").getValue(String.class);
                    lfstype=dataSnapshot.child("Leave").child(Status.sreg).child("type").getValue(String.class);
                    lfreg.setText(Status.sreg);
                    lfdays.setText(lfsdays);
                    lffrom.setText(lfsfrom);
                    lfto.setText(lfsto);
                    lfreason.setText(lfsreason);
                    lftype.setText(lfstype);
                    if(Integer.parseInt(lfsstatus)==0){
                        lfstatus.setText("Still Waiting");
                        lfstatus.setBackgroundColor(Color.RED);
                    }
                    if(Integer.parseInt(lfsstatus)==1){
                        lfstatus.setText("Confirmed by CT");
                        lfstatus.setBackgroundColor(Color.YELLOW);
                    }
                    if(Integer.parseInt(lfsstatus)==2){
                        lfstatus.setText("Confirmed by HOD");
                        lfstatus.setBackgroundColor(Color.GREEN);
                    }
                    if(Integer.parseInt(lfsstatus)==3){
                        lfstatus.setText("Rejected by CT");
                        lfstatus.setBackgroundColor(Color.RED);
                    }
                    if(Integer.parseInt(lfsstatus)==4){
                        lfstatus.setText("Rejected by HOD");
                        lfstatus.setBackgroundColor(Color.RED);
                    }
                }
                else{
                    frameLayout.setVisibility(View.INVISIBLE);
                    lfnodata.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lfsname=dataSnapshot.child(Status.sreg).child("name").getValue(String.class);
                lfname.setText(lfsname);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
