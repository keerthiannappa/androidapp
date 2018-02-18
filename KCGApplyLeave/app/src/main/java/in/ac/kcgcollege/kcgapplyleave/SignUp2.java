package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("student");
    String regno,nm,emid;
    TextView tvregno,tvnm,tvemid,tvack;
    Button register,returntosignin2;
    EditText pass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        tvregno=(TextView)findViewById(R.id.registrationno);
        tvnm=(TextView)findViewById(R.id.name);
        tvemid=(TextView)findViewById(R.id.emailid);
        tvack=(TextView)findViewById(R.id.ack);
        register=(Button)findViewById(R.id.register);
        returntosignin2=(Button)findViewById(R.id.return2signin2);
        pass=(EditText)findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();

        Intent i=getIntent();

        regno=i.getStringExtra("reg");
        progressBar=(ProgressBar) findViewById(R.id.progressBar3);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.hasChild(regno)) {
                    emid = dataSnapshot.child(regno).child("email").getValue(String.class);
                    nm = dataSnapshot.child(regno).child("name").getValue(String.class);
                    tvregno.setText(regno);
                    tvemid.setText(emid);
                    tvnm.setText(nm);
                    Log.d("Data Retrieval", "Email is: " + emid);
                }
                else{
                    tvack.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Toast.makeText(SignUp2.this,"Database read failed",Toast.LENGTH_SHORT).show();
                Log.w("Data Retrieval", "Failed to read value.", error.toException());
            }
        });

        register.setOnClickListener(this);
        returntosignin2.setOnClickListener(this);

    }

    private void createAccount() {


        final String password=pass.getText().toString().trim();


        Log.d("create account", "createAccount:" + emid);
        if (!validateForm()) {
            progressBar.setVisibility(View.INVISIBLE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(emid,password)
                .addOnCompleteListener(SignUp2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        register.setVisibility(View.VISIBLE);
                        if (!task.isSuccessful()) {

                            Log.w("create account", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp2.this, "Failed to Create. Please Try Again Later.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Log.d("create account", "createUserWithEmail:success");

                            myRef.child(regno).child("password").setValue(password);
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification();
                            startActivity(new Intent(SignUp2.this,LoginActivity2.class));

                        }
                    }
                });
        // [END create_user_with_email]
    }


    //Checking entered data
    private boolean validateForm() {
        boolean valid = true;

        String passwordcheck = pass.getText().toString();
        if (TextUtils.isEmpty(passwordcheck)) {
            Toast.makeText(SignUp2.this, "Enter Valid password",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }


    public void onClick(View v)
    {
        if(v==register)
        {
            progressBar.setVisibility(View.VISIBLE);
            register.setVisibility(View.INVISIBLE);
            createAccount();

        }
        if(v==returntosignin2)
        {
            Intent i = new Intent(SignUp2.this, LoginActivity2.class);
            startActivity(i);
        }
    }
    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
