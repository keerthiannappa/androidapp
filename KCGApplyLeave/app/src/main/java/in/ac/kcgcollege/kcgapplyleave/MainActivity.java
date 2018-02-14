package in.ac.kcgcollege.kcgapplyleave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();



        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified() != false)
        {
        }


    }
    public void onClick(View v)
    {

    }
    @Override
    public void onBackPressed()
    {

        // super.onBackPressed();
        // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
