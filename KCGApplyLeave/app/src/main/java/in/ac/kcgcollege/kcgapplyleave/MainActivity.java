package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView a,b;
    Button c;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();

        a=(TextView)findViewById(R.id.textView3);
        c=(Button)findViewById(R.id.button2);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified() != false)
        {
            a.setText("email verified");
        }

        c.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(v==c)
        {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity2.class);
            startActivity(intent);
            finish();
        }
    }
}
