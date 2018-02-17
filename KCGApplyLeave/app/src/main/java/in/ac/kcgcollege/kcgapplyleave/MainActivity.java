package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("student");
    TextView mname,mno,mei,mat,mel,mver,mverdesc;
    Button mod,mleave,msignout;
    String db_email,db_name,db_reg,db_att;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mname=(TextView)findViewById(R.id.main_name);
        mno=(TextView)findViewById(R.id.main_reg);
        mei=(TextView)findViewById(R.id.main_email);
        mel=(TextView)findViewById(R.id.main_eligibility);
        mat=(TextView)findViewById(R.id.main_attendance);
        mver=(TextView)findViewById(R.id.main_verify);
        mverdesc=(TextView)findViewById(R.id.main_verify_desc);
        mod=(Button)findViewById(R.id.main_OD);
        mleave=(Button)findViewById(R.id.main_Leave);
        msignout=(Button)findViewById(R.id.main_signout);

        auth=FirebaseAuth.getInstance();

        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified() == false)
        {
            mod.setEnabled(false);
            mleave.setEnabled(false);
            mver.setVisibility(View.VISIBLE);
            mverdesc.setVisibility(View.VISIBLE);
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    db_email = snapshot.child("email").getValue(String.class);
                    db_att=snapshot.child("attendance").getValue(String.class);
                    db_name=snapshot.child("name").getValue(String.class);
                    db_reg=snapshot.getKey();
                    if(Objects.equals(db_email, user.getEmail())){
                        mei.setText(db_email);
                        mname.setText(db_name);
                        mno.setText(db_reg);
                        if(Integer.parseInt(db_att)<75)
                        {
                            mel.setText("Sorry! You are eligible only to apply for OD.");
                            mat.setText(db_att);
                            mat.setBackgroundColor(Color.RED);
                            mleave.setEnabled(false);

                        }
                        else
                        {
                            mel.setText("You are eligible to apply for OD/Leave.");
                            mat.setText(db_att);
                            mat.setBackgroundColor(Color.GREEN);
                            if(user.isEmailVerified() == true)
                            { mleave.setEnabled(true);}

                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Toast.makeText(SignUp2.this,"Database read failed",Toast.LENGTH_SHORT).show();
                Log.w("Data Retrieval", "Failed to read value.", error.toException());
            }
        });

        mod.setOnClickListener(this);
        mleave.setOnClickListener(this);
        msignout.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        if(v==mod){
            startActivity(new Intent(MainActivity.this,Od.class));
        }
        if(v==mleave){
            startActivity(new Intent(MainActivity.this,Leave.class));
        }
        if(v==msignout){
            auth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity2.class));
        }

    }
    @Override
    public void onBackPressed()
    {

        // super.onBackPressed();
        // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
