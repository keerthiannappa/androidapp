package com.diyasstudios.theavenue;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    EditText emid;
    EditText etname;
    EditText etnumber;
    EditText psw;
    Button signup;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emid=(EditText) findViewById(R.id.signup_email_edittext);
        psw=(EditText) findViewById(R.id.signup_password_edittext);
        etname=(EditText) findViewById(R.id.signup_name_edittext);
        etnumber=(EditText) findViewById(R.id.signup_mobile_edittext);
        signup=(Button) findViewById(R.id.signup_button);
        progressBar=(ProgressBar) findViewById(R.id.progressBar_signup);
        signup.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("User");

    }
    private void createAccount() {

        final String email=emid.getText().toString().trim();
        final String password=psw.getText().toString().trim();
        final String mobile=etnumber.getText().toString().trim();
        final String name=etname.getText().toString().trim();

        Log.d(LoginActivity.TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        signup.setVisibility(View.VISIBLE);
                        if (!task.isSuccessful()) {

                            Log.w(LoginActivity.TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Failed to Create. Please Try Again Later.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Log.d(LoginActivity.TAG, "createUserWithEmail:success");
                            UserDetails userDetails=new UserDetails(name,mobile,email,password);
                            myRef.child(mobile).setValue(userDetails);
                            startActivity(new Intent(SignUp.this,LoginActivity.class));

                        }
                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String emailcheck = emid.getText().toString();
        if (TextUtils.isEmpty(emailcheck)) {
            Toast.makeText(SignUp.this, "Enter Valid email",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        String passwordcheck = psw.getText().toString();
        if (TextUtils.isEmpty(passwordcheck)) {
            Toast.makeText(SignUp.this, "Enter Valid password",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    public void onClick(View v)
    {
        if(v==signup)
        {
            progressBar.setVisibility(View.VISIBLE);
            signup.setVisibility(View.INVISIBLE);
            createAccount();

        }
    }
}
