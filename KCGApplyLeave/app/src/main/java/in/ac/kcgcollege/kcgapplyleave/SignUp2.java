package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SignUp2 extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        t=(TextView)findViewById(R.id.dummytext);
        Intent i=getIntent();

        String regno=i.getStringExtra("reg");
        t.setText(regno);
    }
}
