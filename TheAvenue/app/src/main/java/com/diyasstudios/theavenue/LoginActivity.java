package com.diyasstudios.theavenue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

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

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.parseColor;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    ScrollView scrollView;
    TranslateAnimation animation , animation2;
    ImageView theavenue;
    Button login , signup;
    EditText emailtext , passwordtext;
    TextView auth_failed , emailrequired , passwordrequired;
    View authview;
    ProgressBar progressBar;
    float x,y,a,b;

    public static String staticemail;

    private FirebaseAuth mAuth;
    public static final String TAG="EmailPassword";

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        //UI elements initalization
        theavenue=(ImageView) findViewById(R.id.theavenue);

        scrollView=(ScrollView) findViewById(R.id.login_scrollview);

        login=(Button) findViewById(R.id.login_button);
        signup=(Button) findViewById(R.id.login_button_signup);

        emailtext=(EditText) findViewById(R.id.email_edit_text);
        passwordtext=(EditText) findViewById(R.id.password_edit_text);

        auth_failed=(TextView) findViewById(R.id.authfailed);
        emailrequired=(TextView) findViewById(R.id.emailrequired);
        passwordrequired=(TextView) findViewById(R.id.passwordrequired);


        authview=(View) findViewById(R.id.viewauth);
        progressBar=(ProgressBar) findViewById(R.id.progressBar2);

        getWindow().setStatusBarColor(getResources().getColor(R.color.logindark));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.loginlight));

        //animation definition start
        x=theavenue.getLeft();a=scrollView.getLeft();
        y=theavenue.getTop(); b=scrollView.getRight();
        animation = new TranslateAnimation(x,x,y-500,y);
        animation.setDuration(1500);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {y += 500;
            }
        });
        animation2 = new TranslateAnimation(a,a,b+500,b);
        animation2.setDuration(1500);
        animation2.setFillAfter(true);
        animation2.setFillEnabled(true);
        animation2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {y -= 500;
            }
        });
        //animation definition end

        //start animation
        theavenue.startAnimation(animation);
        scrollView.startAnimation(animation2);


        //delay event
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


            }
        },1500);




        //button listeners
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    @Override
    public void onStop() {
        super.onStop();

    }



    //sign-in definition
    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);
        if (!validateForm())
        {
            progressBar.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            return;
        }


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.INVISIBLE);
                        login.setVisibility(View.VISIBLE);
                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                        else {
                            mRef = FirebaseDatabase.getInstance().getReference("User");
                             mRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                   String test1=dataSnapshot.child("8428421605").child("name").getValue(String.class);
                                    String test2=dataSnapshot.child("8428421605").child("mobile").getValue(String.class);
                                    //USE THE SAME CHILD PARAMETER TO RETRIVE
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            authview.setBackgroundColor(parseColor("#d50000"));
                            auth_failed.setText("Authentication Failed");
                        }

                        // [END_EXCLUDE]
                    }
                });

        // [END sign_in_with_email]
    }


    //check entered data
    private boolean validateForm() {
        boolean valid = true;

        String email = emailtext.getText().toString();
        if (TextUtils.isEmpty(email)) {
           emailrequired.setError("Required.");
            valid = false;
        } else {
            emailrequired.setError(null);
        }

        String password = passwordtext.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordrequired.setError("Required.");
            valid = false;
        } else {
            passwordrequired.setError(null);
        }

        return valid;
    }


    //button click definition
    @Override
    public void onClick(View v)
    {
        if(v==login)
        {
            staticemail=emailtext.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
            signIn(emailtext.getText().toString(),passwordtext.getText().toString());

        }
        if(v==signup)
        {
            startActivity(new Intent(LoginActivity.this,SignUp.class));
            finish();
        }
    }
}
