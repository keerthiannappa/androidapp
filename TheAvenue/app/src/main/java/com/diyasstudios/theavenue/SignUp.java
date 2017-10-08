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
    private static final String TAG="EmailPassword";

    EditText emid;
    EditText etname;
    EditText etnumber;
    EditText psw;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emid=(EditText) findViewById(R.id.signup_email_edittext);
        psw=(EditText) findViewById(R.id.signup_password_edittext);
        etname=(EditText) findViewById(R.id.signup_name_edittext);
        etnumber=(EditText) findViewById(R.id.signup_mobile_edittext);
        signup=(Button) findViewById(R.id.signup_button);
        signup.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();

    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Failed to Create",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        { Log.d(TAG, "createUserWithEmail:success");
                            myRef.child("User").child(emid.getText().toString().trim()).child("Name").setValue(etname.getText().toString().trim());
                            myRef.child("User").child(emid.getText().toString().trim()).child("Mobile").setValue(etnumber.getText().toString().trim());
                            myRef.child("User").child(emid.getText().toString().trim()).child("MailID").setValue(emid.getText().toString().trim());
                            myRef.child("User").child(emid.getText().toString().trim()).child("Password").setValue(psw.getText().toString().trim());
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
            createAccount(emid.getText().toString(),psw.getText().toString());

        }
    }
}
