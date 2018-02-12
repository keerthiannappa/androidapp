package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText signreg;
    TextView required;
    Button go,returntosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signreg=(EditText)findViewById(R.id.signup_reg);
        required=(TextView)findViewById(R.id.required);
        returntosignin=(Button)findViewById(R.id.return2signin);
        go=(Button)findViewById(R.id.go);

        go.setOnClickListener(this);
        returntosignin.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(v==go)
        {
            String s=signreg.getText().toString();
            if (TextUtils.isEmpty(s)) {
                required.setError("Required.");
            }
            else {
                Intent i = new Intent(SignUp.this, SignUp2.class);
                i.putExtra("reg", signreg.getText().toString());
                startActivity(i);
            }
        }
        if(v==returntosignin)
        {
            Intent i = new Intent(SignUp.this, LoginActivity2.class);
            startActivity(i);
        }

    }
}
