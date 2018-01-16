package com.diyaasstudio.theavenue_adminadder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nme,number,mail,psw;
    Button add,vl;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    public static final String TAG="EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nme=(EditText) findViewById(R.id.name);
        number=(EditText) findViewById(R.id.number);
        mail=(EditText) findViewById(R.id.mail);
        psw=(EditText) findViewById(R.id.psw);
        add=(Button) findViewById(R.id.add);
        vl=(Button) findViewById(R.id.viewlist);

        add.setOnClickListener(this);
        vl.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("User");


    }


    private void createadmin() {

        final String email=mail.getText().toString().trim();
        final String password=psw.getText().toString().trim();
        final String mobile=number.getText().toString().trim();
        final String name=nme.getText().toString().trim();

        Log.d(MainActivity.TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Log.w(MainActivity.TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Failed to Create. Please Try Again Later.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Log.d(MainActivity.TAG, "createUserWithEmail:success");
                            AdminDetails userDetails=new AdminDetails(1, email, name, mobile, password);
                            myRef.child(mobile).setValue(userDetails);
                            Snackbar.make(findViewById(R.id.layout), "Admin Created", Snackbar.LENGTH_LONG).show();

                        }
                    }
                });
        // [END create_user_with_email]
    }


    //Checking entered data
    private boolean validateForm() {
        boolean valid = true;

        String emailcheck = mail.getText().toString();
        if (TextUtils.isEmpty(emailcheck)) {
            Toast.makeText(MainActivity.this, "Enter Valid email",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        String passwordcheck = psw.getText().toString();
        if (TextUtils.isEmpty(passwordcheck)) {
            Toast.makeText(MainActivity.this, "Enter Valid password",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    public void onClick(View v)
    {
        if(v==add)
        {
            createadmin();
        }
        if(v==vl)
        {
            startActivity(new Intent(MainActivity.this,ViewList.class));
        }
    }
}
