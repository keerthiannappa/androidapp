package com.diyasstudios.theavenue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    TextView name,mobile,type,email;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String k=user.getEmail();
    final String[] e=k.split("@");

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_me, container, false);

        name=(TextView) view.findViewById(R.id.Name);
        mobile=(TextView) view.findViewById(R.id.Mobile);
        type =(TextView) view.findViewById(R.id.type);
        email=(TextView) view.findViewById(R.id.emailid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.child(e[0]).child("mobile").getValue(String.class);
                mobile.setText(value1);
                String value0 = dataSnapshot.child(e[0]).child("name").getValue(String.class);
                name.setText(value0);
                String value2 = dataSnapshot.child(e[0]).child("emailID").getValue(String.class);
                email.setText(value2);
                String value3 = dataSnapshot.child(e[0]).child("admin").getValue(String.class);
                 if(Integer.parseInt(value3)==1)
                 { type.setText("Admin");}
                 if(Integer.parseInt(value3)==0)
                 { type.setText("User");}

            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });




        return view;
    }

}
